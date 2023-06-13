/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Other licenses:
 * -----------------------------------------------------------------------------
 * Commercial licenses for this work are available. These replace the above
 * ASL 2.0 and offer limited warranties, support, maintenance, and commercial
 * database integrations.
 *
 * For more information, please visit: https://www.jooq.org/legal/licensing
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package org.jooq.impl;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableCollection;
import static java.util.stream.Collectors.toList;
import static org.jooq.impl.Tools.map;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Stream;

import org.jooq.Commit;
import org.jooq.Commits;
import org.jooq.Configuration;
import org.jooq.ContentType;
import org.jooq.File;
import org.jooq.Tag;
import org.jooq.exception.DataMigrationValidationException;
import org.jooq.migrations.xml.jaxb.ChangeType;
import org.jooq.migrations.xml.jaxb.CommitType;
import org.jooq.migrations.xml.jaxb.FileType;
import org.jooq.migrations.xml.jaxb.MigrationsType;
import org.jooq.migrations.xml.jaxb.ParentType;
import org.jooq.migrations.xml.jaxb.TagType;

/**
 * @author Lukas Eder
 */
final class CommitsImpl implements Commits {

    final Configuration       configuration;
    final Commit              root;
    final Map<String, Commit> commitsById;
    final Map<String, Commit> commitsByTag;

    CommitsImpl(Configuration configuration, Commit root) {
        this.configuration = configuration;
        this.commitsById = new LinkedHashMap<>();
        this.commitsByTag = new LinkedHashMap<>();
        this.root = root;

        add(root);
    }

    @Override
    public void add(Commit commit) {
        Commit duplicate;

        if ((duplicate = commitsById.get(commit.id())) != null)
            throw new DataMigrationValidationException("Duplicate commit ID already present on commit: " + duplicate);

        for (Tag tag : commit.tags())
            if ((duplicate = commitsByTag.get(tag.id())) != null)
                throw new DataMigrationValidationException("Duplicate tag " + tag + " already present on commit: " + duplicate);

        commitsById.put(commit.id(), commit);

        for (Tag tag : commit.tags())
            commitsByTag.put(tag.id(), commit);
    }

    @Override
    public void addAll(Commit... c) {
        addAll(asList(c));
    }

    @Override
    public void addAll(Collection<? extends Commit> c) {
        for (Commit commit : c)
            add(commit);
    }

    @Override
    public final Commit root() {
        return root;
    }

    @Override
    public final Commit get(String id) {
        Commit result = commitsById.get(id);
        return result != null ? result : commitsByTag.get(id);
    }

    @Override
    public final Iterator<Commit> iterator() {
        return unmodifiableCollection(commitsById.values()).iterator();
    }

    // [#9506] TODO: Formalise this decoding, and make it part of the public API
    static final class FileData {
        final java.io.File file;
        final String       basename;
        final String       version;
        final String       message;
        final String       id;
        final List<String> parentIds;

        FileData(java.io.File file) {
            this.file = file;

            // [#9506] TODO: Other naming schemes?
            this.basename = file.getName().replace(".sql", "");

            /*
             * An example:
             * -----------
             * v1-a
             * v2-ab, v2-ac
             * v3-acd
             * v3-abc.v2-ab,v2-ac
             * v4-abcd.v3-abc,v3-acd
             */
            String[] idAndParentsArray = basename.split("\\.");
            this.id = idAndParentsArray[0];
            this.parentIds = idAndParentsArray.length > 1 ? asList(idAndParentsArray[1].split(",")) : asList();

            String[] idArray = this.id.split("-");
            this.version = idArray[0];
            this.message = idArray.length > 1 ? idArray[1] : null;
        }
    }

    @Override
    public final Commits load(java.io.File directory) throws IOException {

        // [#9506] TODO: Turning a directory into a MigrationsType (and various other conversion)
        //               could be made reusable. This is certainly very useful for testing and interop,
        //               e.g. also to support other formats (Flyway, Liquibase) as source
        TreeMap<String, List<String>> versionToId = new TreeMap<>();
        Map<String, CommitType> idToCommit = new HashMap<>();

        // [#9506] TODO: Offer this also using a different File abstraction, for better testing, etc. (?)
        // [#9506] TODO: Recurse into subdirectories?
        // [#9506] TODO: Other suffixes than .sql?
        java.io.File[] files = directory.listFiles(f -> f.getName().endsWith(".sql"));

        if (files != null) {
            List<FileData> list = Stream.of(files).map(x -> {
                return new FileData(x);
            }).collect(toList());

            /*
             * An example:
             * -----------
             * v1-a
             * v2-ab, v2-ac
             * v3-acd
             * v3-abc.v2-ab,v2-ac
             * v4-abcd.v3-abc,v3-acd
             */
            for (FileData f : list)
                versionToId.computeIfAbsent(f.version, k -> new ArrayList<>()).add(f.id);

            for (FileData f : list)
                idToCommit.put(f.id, new CommitType().withId(f.id));

            for (FileData f : list) {
                CommitType commit = idToCommit.get(f.id);

                // Parents are implicit
                // [#9506] TODO: What cases of implicit parents are possible. What edge cases aren't?
                if (f.parentIds.isEmpty()) {
                    Entry<String, List<String>> e = versionToId.lowerEntry(f.version);

                    if (e != null) {
                        if (e.getValue().size() > 1)
                            throw new DataMigrationValidationException("Multiple predecessors for " + e.getKey() + ". Implicit parent cannot be detected: " + e.getValue());
                        else
                            commit.setParents(asList(new ParentType().withId(e.getValue().get(0))));
                    }

                }

                // Parents are explicit
                else {
                    for (String parent : f.parentIds)
                        if (idToCommit.containsKey(parent))
                            commit.getParents().add(new ParentType().withId(parent));
                        else
                            throw new DataMigrationValidationException("Parent " + parent + " is not defined");
                }

                commit
                    .withMessage(f.message)

                    // [#9506] TODO: Better define paths, relative paths, etc.
                    // [#9506] TOOD: Support other ContentType values than INCREMENT
                    .withFiles(asList(new FileType()
                        .withPath(f.basename)
                        .withContentType(ContentType.INCREMENT)
                        .withContent(Files.readString(f.file.toPath()))
                    ));
            }
        }

        return load(new MigrationsType().withCommits(idToCommit.values()));
    }

    @Override
    public final Commits load(MigrationsType migrations) {
        Map<String, CommitType> map = new HashMap<>();

        for (CommitType commit : migrations.getCommits())
            map.put(commit.getId(), commit);

        for (CommitType commit : migrations.getCommits())
            load(map, commit);

        return this;
    }

    private final Commit load(Map<String, CommitType> map, CommitType commit) {
        Commit result = commitsById.get(commit.getId());

        if (result != null)
            return result;

        Commit p1 = root;
        Commit p2 = null;

        List<ParentType> parents = commit.getParents();
        int size = parents.size();
        if (size > 0) {
            CommitType c1 = map.get(parents.get(0).getId());

            if (c1 == null)
                throw new DataMigrationValidationException("Parent not found: " + parents.get(0).getId());

            p1 = load(map, c1);
            if (size == 2) {
                CommitType c2 = map.get(parents.get(1).getId());

                if (c2 == null)
                    throw new DataMigrationValidationException("Parent not found: " + parents.get(0).getId());

                p2 = load(map, c2);
            }
            else if (size > 2)
                throw new DataMigrationValidationException("Merging more than two parents not yet supported");
        }

        result = p2 == null
            ? p1.commit(commit.getId(), commit.getMessage(), files(commit))
            : p1.merge(commit.getId(), commit.getMessage(), p2, files(commit));

        for (TagType tag : commit.getTags())
            result = result.tag(tag.getId(), tag.getMessage());

        add(result);
        return result;
    }

    private final List<File> files(CommitType commit) {
        return map(commit.getFiles(), f -> Migrations.file(f.getPath(), f.getChange() == ChangeType.DELETE ? null : f.getContent(), f.getContentType()));
    }

    @Override
    public final MigrationsType export() {
        return new MigrationsType().withCommits(map(this, commit -> new CommitType()
            .withId(commit.id())
            .withMessage(commit.message())
            .withParents(map(commit.parents(), parent -> new ParentType().withId(parent.id())))
            .withTags(map(commit.tags(), tag -> new TagType().withId(tag.id()).withMessage(tag.message())))
            .withFiles(map(commit.files(), file -> new FileType()
                .withPath(file.path())
                .withContent(file.content())
                .withContentType(file.type())
                .withChange(file.content() == null ? ChangeType.DELETE : ChangeType.MODIFY)
            ))
        ));
    }

    @Override
    public String toString() {
        return "" + commitsById.values();
    }
}
