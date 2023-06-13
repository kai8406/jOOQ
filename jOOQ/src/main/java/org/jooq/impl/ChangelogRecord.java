/*
 * This file is generated by jOOQ.
 */
package org.jooq.impl;


import java.sql.Timestamp;

import org.jooq.Record1;
import org.jooq.impl.MigrationImpl.Status;


/**
 * The migration log of jOOQ Migrations.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
class ChangelogRecord extends UpdatableRecordImpl<ChangelogRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>JOOQ_MIGRATIONS_CHANGELOG.ID</code>. The database
     * version ID.
     */
    ChangelogRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>JOOQ_MIGRATIONS_CHANGELOG.ID</code>. The database
     * version ID.
     */
    Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>JOOQ_MIGRATIONS_CHANGELOG.MIGRATED_FROM</code>. The
     * previous database version ID.
     */
    ChangelogRecord setMigratedFrom(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>JOOQ_MIGRATIONS_CHANGELOG.MIGRATED_FROM</code>. The
     * previous database version ID.
     */
    String getMigratedFrom() {
        return (String) get(1);
    }

    /**
     * Setter for <code>JOOQ_MIGRATIONS_CHANGELOG.MIGRATED_TO</code>.
     */
    ChangelogRecord setMigratedTo(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>JOOQ_MIGRATIONS_CHANGELOG.MIGRATED_TO</code>.
     */
    String getMigratedTo() {
        return (String) get(2);
    }

    /**
     * Setter for <code>JOOQ_MIGRATIONS_CHANGELOG.MIGRATED_AT</code>. The
     * date/time when the database version was migrated to.
     */
    ChangelogRecord setMigratedAt(Timestamp value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>JOOQ_MIGRATIONS_CHANGELOG.MIGRATED_AT</code>. The
     * date/time when the database version was migrated to.
     */
    Timestamp getMigratedAt() {
        return (Timestamp) get(3);
    }

    /**
     * Setter for <code>JOOQ_MIGRATIONS_CHANGELOG.MIGRATION_TIME</code>. The
     * time in milliseconds it took to migrate to this database version.
     */
    ChangelogRecord setMigrationTime(Long value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>JOOQ_MIGRATIONS_CHANGELOG.MIGRATION_TIME</code>. The
     * time in milliseconds it took to migrate to this database version.
     */
    Long getMigrationTime() {
        return (Long) get(4);
    }

    /**
     * Setter for <code>JOOQ_MIGRATIONS_CHANGELOG.JOOQ_VERSION</code>. The jOOQ
     * version used to migrate to this database version.
     */
    ChangelogRecord setJooqVersion(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>JOOQ_MIGRATIONS_CHANGELOG.JOOQ_VERSION</code>. The jOOQ
     * version used to migrate to this database version.
     */
    String getJooqVersion() {
        return (String) get(5);
    }

    /**
     * Setter for <code>JOOQ_MIGRATIONS_CHANGELOG.SQL</code>. The SQL statements
     * that were run to install this database version.
     */
    ChangelogRecord setSql(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>JOOQ_MIGRATIONS_CHANGELOG.SQL</code>. The SQL statements
     * that were run to install this database version.
     */
    String getSql() {
        return (String) get(6);
    }

    /**
     * Setter for <code>JOOQ_MIGRATIONS_CHANGELOG.SQL_COUNT</code>. The number
     * of SQL statements that were run to install this database version.
     */
    ChangelogRecord setSqlCount(Integer value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>JOOQ_MIGRATIONS_CHANGELOG.SQL_COUNT</code>. The number
     * of SQL statements that were run to install this database version.
     */
    Integer getSqlCount() {
        return (Integer) get(7);
    }

    /**
     * Setter for <code>JOOQ_MIGRATIONS_CHANGELOG.STATUS</code>. The database
     * version installation status.
     */
    ChangelogRecord setStatus(Status value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>JOOQ_MIGRATIONS_CHANGELOG.STATUS</code>. The database
     * version installation status.
     */
    Status getStatus() {
        return (Status) get(8);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ChangelogRecord
     */
    ChangelogRecord() {
        super(Changelog.CHANGELOG);
    }

    /**
     * Create a detached, initialised ChangelogRecord
     */
    ChangelogRecord(Integer id, String migratedFrom, String migratedTo, Timestamp migratedAt, Long migrationTime, String jooqVersion, String sql, Integer sqlCount, Status status) {
        super(Changelog.CHANGELOG);

        setId(id);
        setMigratedFrom(migratedFrom);
        setMigratedTo(migratedTo);
        setMigratedAt(migratedAt);
        setMigrationTime(migrationTime);
        setJooqVersion(jooqVersion);
        setSql(sql);
        setSqlCount(sqlCount);
        setStatus(status);
        resetChangedOnNotNull();
    }
}
