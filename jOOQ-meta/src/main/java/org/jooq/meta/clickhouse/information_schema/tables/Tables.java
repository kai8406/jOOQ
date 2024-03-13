/*
 * This file is generated by jOOQ.
 */
package org.jooq.meta.clickhouse.information_schema.tables;


import java.util.Arrays;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.jooq.meta.clickhouse.information_schema.InformationSchema;
import org.jooq.meta.clickhouse.information_schema.Keys;
import org.jooq.types.ULong;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Tables extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>information_schema.tables</code>
     */
    public static final Tables TABLES = new Tables();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>information_schema.tables.table_catalog</code>.
     */
    public final TableField<Record, String> TABLE_CATALOG = createField(DSL.name("table_catalog"), SQLDataType.CHAR.nullable(false).defaultValue(DSL.field(DSL.raw(""), SQLDataType.CHAR)), this, "");

    /**
     * The column <code>information_schema.tables.table_schema</code>.
     */
    public final TableField<Record, String> TABLE_SCHEMA = createField(DSL.name("table_schema"), SQLDataType.CHAR.nullable(false).defaultValue(DSL.field(DSL.raw(""), SQLDataType.CHAR)), this, "");

    /**
     * The column <code>information_schema.tables.table_name</code>.
     */
    public final TableField<Record, String> TABLE_NAME = createField(DSL.name("table_name"), SQLDataType.CHAR.nullable(false).defaultValue(DSL.field(DSL.raw(""), SQLDataType.CHAR)), this, "");

    /**
     * The column <code>information_schema.tables.table_type</code>.
     */
    public final TableField<Record, String> TABLE_TYPE = createField(DSL.name("table_type"), SQLDataType.CHAR.nullable(false).defaultValue(DSL.field(DSL.raw(""), SQLDataType.CHAR)), this, "");

    /**
     * The column <code>information_schema.tables.table_rows</code>.
     */
    public final TableField<Record, ULong> TABLE_ROWS = createField(DSL.name("table_rows"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw(""), SQLDataType.BIGINTUNSIGNED)), this, "");

    /**
     * The column <code>information_schema.tables.data_length</code>.
     */
    public final TableField<Record, ULong> DATA_LENGTH = createField(DSL.name("data_length"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw(""), SQLDataType.BIGINTUNSIGNED)), this, "");

    /**
     * The column <code>information_schema.tables.table_collation</code>.
     */
    public final TableField<Record, String> TABLE_COLLATION = createField(DSL.name("table_collation"), SQLDataType.CHAR.defaultValue(DSL.field(DSL.raw(""), SQLDataType.CHAR)), this, "");

    /**
     * The column <code>information_schema.tables.table_comment</code>.
     */
    public final TableField<Record, String> TABLE_COMMENT = createField(DSL.name("table_comment"), SQLDataType.CHAR.defaultValue(DSL.field(DSL.raw(""), SQLDataType.CHAR)), this, "");

    private Tables(Name alias, Table<Record> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Tables(Name alias, Table<Record> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.view(), where);
    }

    /**
     * Create an aliased <code>information_schema.tables</code> table reference
     */
    public Tables(String alias) {
        this(DSL.name(alias), TABLES);
    }

    /**
     * Create an aliased <code>information_schema.tables</code> table reference
     */
    public Tables(Name alias) {
        this(alias, TABLES);
    }

    /**
     * Create a <code>information_schema.tables</code> table reference
     */
    public Tables() {
        this(DSL.name("tables"), null);
    }

    public <O extends Record> Tables(Table<O> path, ForeignKey<O, Record> childPath, InverseForeignKey<O, Record> parentPath) {
        super(path, childPath, parentPath, TABLES);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public UniqueKey<Record> getPrimaryKey() {
        return Keys.SYNTHETIC_PK_TABLES;
    }

    @Override
    public List<ForeignKey<Record, ?>> getReferences() {
        return Arrays.asList(Keys.SYNTHETIC_FK_TABLES__SYNTHETIC_PK_SCHEMATA);
    }

    private transient Schemata _schemata;

    /**
     * Get the implicit join path to the
     * <code>information_schema.schemata</code> table.
     */
    public Schemata schemata() {
        if (_schemata == null)
            _schemata = new Schemata(this, Keys.SYNTHETIC_FK_TABLES__SYNTHETIC_PK_SCHEMATA, null);

        return _schemata;
    }

    private transient Columns _columns;

    /**
     * Get the implicit to-many join path to the
     * <code>information_schema.columns</code> table
     */
    public Columns columns() {
        if (_columns == null)
            _columns = new Columns(this, null, Keys.SYNTHETIC_FK_COLUMNS__SYNTHETIC_PK_TABLES.getInverseKey());

        return _columns;
    }

    @Override
    public Tables as(String alias) {
        return new Tables(DSL.name(alias), this);
    }

    @Override
    public Tables as(Name alias) {
        return new Tables(alias, this);
    }

    @Override
    public Tables as(Table<?> alias) {
        return new Tables(alias.getQualifiedName(), this);
    }
}