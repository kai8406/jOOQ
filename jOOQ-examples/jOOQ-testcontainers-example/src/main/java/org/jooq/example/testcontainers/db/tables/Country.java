/*
 * This file is generated by jOOQ.
 */
package org.jooq.example.testcontainers.db.tables;


import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.example.testcontainers.db.Keys;
import org.jooq.example.testcontainers.db.Public;
import org.jooq.example.testcontainers.db.tables.records.CountryRecord;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Country extends TableImpl<CountryRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.country</code>
     */
    public static final Country COUNTRY = new Country();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CountryRecord> getRecordType() {
        return CountryRecord.class;
    }

    /**
     * The column <code>public.country.country_id</code>.
     */
    public final TableField<CountryRecord, Long> COUNTRY_ID = createField(DSL.name("country_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.country.country</code>.
     */
    public final TableField<CountryRecord, String> COUNTRY_ = createField(DSL.name("country"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>public.country.last_update</code>.
     */
    public final TableField<CountryRecord, LocalDateTime> LAST_UPDATE = createField(DSL.name("last_update"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field("now()", SQLDataType.LOCALDATETIME)), this, "");

    private Country(Name alias, Table<CountryRecord> aliased) {
        this(alias, aliased, null);
    }

    private Country(Name alias, Table<CountryRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.country</code> table reference
     */
    public Country(String alias) {
        this(DSL.name(alias), COUNTRY);
    }

    /**
     * Create an aliased <code>public.country</code> table reference
     */
    public Country(Name alias) {
        this(alias, COUNTRY);
    }

    /**
     * Create a <code>public.country</code> table reference
     */
    public Country() {
        this(DSL.name("country"), null);
    }

    public <O extends Record> Country(Table<O> child, ForeignKey<O, CountryRecord> key) {
        super(child, key, COUNTRY);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<CountryRecord, Long> getIdentity() {
        return (Identity<CountryRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<CountryRecord> getPrimaryKey() {
        return Keys.COUNTRY_PKEY;
    }

    @Override
    public Country as(String alias) {
        return new Country(DSL.name(alias), this);
    }

    @Override
    public Country as(Name alias) {
        return new Country(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Country rename(String name) {
        return new Country(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Country rename(Name name) {
        return new Country(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, String, LocalDateTime> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}