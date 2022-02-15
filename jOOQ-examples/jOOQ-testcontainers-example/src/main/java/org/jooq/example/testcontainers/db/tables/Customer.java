/*
 * This file is generated by jOOQ.
 */
package org.jooq.example.testcontainers.db.tables;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.Row10;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableLike;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.example.testcontainers.db.Indexes;
import org.jooq.example.testcontainers.db.Keys;
import org.jooq.example.testcontainers.db.Public;
import org.jooq.example.testcontainers.db.tables.records.CustomerRecord;
import org.jooq.example.testcontainers.db.tables.records.PaymentP2007_01Record;
import org.jooq.example.testcontainers.db.tables.records.PaymentP2007_02Record;
import org.jooq.example.testcontainers.db.tables.records.PaymentP2007_03Record;
import org.jooq.example.testcontainers.db.tables.records.PaymentP2007_04Record;
import org.jooq.example.testcontainers.db.tables.records.PaymentP2007_05Record;
import org.jooq.example.testcontainers.db.tables.records.PaymentP2007_06Record;
import org.jooq.example.testcontainers.db.tables.records.PaymentRecord;
import org.jooq.example.testcontainers.db.tables.records.RentalRecord;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Customer extends TableImpl<CustomerRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.customer</code>
     */
    public static final Customer CUSTOMER = new Customer();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CustomerRecord> getRecordType() {
        return CustomerRecord.class;
    }

    /**
     * The column <code>public.customer.customer_id</code>.
     */
    public final TableField<CustomerRecord, Long> CUSTOMER_ID = createField(DSL.name("customer_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.customer.store_id</code>.
     */
    public final TableField<CustomerRecord, Long> STORE_ID = createField(DSL.name("store_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.customer.first_name</code>.
     */
    public final TableField<CustomerRecord, String> FIRST_NAME = createField(DSL.name("first_name"), SQLDataType.VARCHAR(45).nullable(false), this, "");

    /**
     * The column <code>public.customer.last_name</code>.
     */
    public final TableField<CustomerRecord, String> LAST_NAME = createField(DSL.name("last_name"), SQLDataType.VARCHAR(45).nullable(false), this, "");

    /**
     * The column <code>public.customer.email</code>.
     */
    public final TableField<CustomerRecord, String> EMAIL = createField(DSL.name("email"), SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>public.customer.address_id</code>.
     */
    public final TableField<CustomerRecord, Long> ADDRESS_ID = createField(DSL.name("address_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.customer.activebool</code>.
     */
    public final TableField<CustomerRecord, Boolean> ACTIVEBOOL = createField(DSL.name("activebool"), SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("true", SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>public.customer.create_date</code>.
     */
    public final TableField<CustomerRecord, LocalDate> CREATE_DATE = createField(DSL.name("create_date"), SQLDataType.LOCALDATE.nullable(false).defaultValue(DSL.field("('now'::text)::date", SQLDataType.LOCALDATE)), this, "");

    /**
     * The column <code>public.customer.last_update</code>.
     */
    public final TableField<CustomerRecord, LocalDateTime> LAST_UPDATE = createField(DSL.name("last_update"), SQLDataType.LOCALDATETIME(6).defaultValue(DSL.field("now()", SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>public.customer.active</code>.
     */
    public final TableField<CustomerRecord, Integer> ACTIVE = createField(DSL.name("active"), SQLDataType.INTEGER, this, "");

    private Customer(Name alias, Table<CustomerRecord> aliased) {
        this(alias, aliased, null);
    }

    private Customer(Name alias, Table<CustomerRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.customer</code> table reference
     */
    public Customer(String alias) {
        this(DSL.name(alias), CUSTOMER);
    }

    /**
     * Create an aliased <code>public.customer</code> table reference
     */
    public Customer(Name alias) {
        this(alias, CUSTOMER);
    }

    /**
     * Create a <code>public.customer</code> table reference
     */
    public Customer() {
        this(DSL.name("customer"), null);
    }

    public <O extends Record> Customer(Table<O> child, ForeignKey<O, CustomerRecord> key) {
        super(child, key, CUSTOMER);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.IDX_FK_ADDRESS_ID, Indexes.IDX_FK_STORE_ID, Indexes.IDX_LAST_NAME);
    }

    @Override
    public Identity<CustomerRecord, Long> getIdentity() {
        return (Identity<CustomerRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<CustomerRecord> getPrimaryKey() {
        return Keys.CUSTOMER_PKEY;
    }

    @Override
    public List<ForeignKey<CustomerRecord, ?>> getReferences() {
        return Arrays.asList(Keys.CUSTOMER__CUSTOMER_STORE_ID_FKEY, Keys.CUSTOMER__CUSTOMER_ADDRESS_ID_FKEY);
    }

    private transient Store _store;
    private transient Address _address;

    /**
     * Get the implicit join path to the <code>public.store</code> table.
     */
    public Store store() {
        if (_store == null)
            _store = new Store(this, Keys.CUSTOMER__CUSTOMER_STORE_ID_FKEY);

        return _store;
    }

    /**
     * Get the implicit join path to the <code>public.address</code> table.
     */
    public Address address() {
        if (_address == null)
            _address = new Address(this, Keys.CUSTOMER__CUSTOMER_ADDRESS_ID_FKEY);

        return _address;
    }

    /**
     * A convenience constructor for correlated <code>MULTISET</code>s
     * expressions to the <code>public.payment</code> one-to-many child table.
     */
    public Field<Result<PaymentRecord>> paymentMultiset() {
        return paymentMultiset(Function.identity());
    }

    /**
     * A convenience constructor for correlated <code>MULTISET</code>s
     * expressions to the <code>public.payment</code> one-to-many child table.
     */
    public <O extends Record> Field<Result<O>> paymentMultiset(Function<? super Payment, ? extends TableLike<O>> subquery) {
        return oneToManyMultiset(Keys.PAYMENT__PAYMENT_CUSTOMER_ID_FKEY, t -> subquery.apply((Payment) t));
    }

    /**
     * A convenience constructor for correlated <code>MULTISET</code>s
     * expressions to the <code>public.payment_p2007_01</code> one-to-many child
     * table.
     */
    public Field<Result<PaymentP2007_01Record>> paymentP2007_01Multiset() {
        return paymentP2007_01Multiset(Function.identity());
    }

    /**
     * A convenience constructor for correlated <code>MULTISET</code>s
     * expressions to the <code>public.payment_p2007_01</code> one-to-many child
     * table.
     */
    public <O extends Record> Field<Result<O>> paymentP2007_01Multiset(Function<? super PaymentP2007_01, ? extends TableLike<O>> subquery) {
        return oneToManyMultiset(Keys.PAYMENT_P2007_01__PAYMENT_P2007_01_CUSTOMER_ID_FKEY, t -> subquery.apply((PaymentP2007_01) t));
    }

    /**
     * A convenience constructor for correlated <code>MULTISET</code>s
     * expressions to the <code>public.payment_p2007_02</code> one-to-many child
     * table.
     */
    public Field<Result<PaymentP2007_02Record>> paymentP2007_02Multiset() {
        return paymentP2007_02Multiset(Function.identity());
    }

    /**
     * A convenience constructor for correlated <code>MULTISET</code>s
     * expressions to the <code>public.payment_p2007_02</code> one-to-many child
     * table.
     */
    public <O extends Record> Field<Result<O>> paymentP2007_02Multiset(Function<? super PaymentP2007_02, ? extends TableLike<O>> subquery) {
        return oneToManyMultiset(Keys.PAYMENT_P2007_02__PAYMENT_P2007_02_CUSTOMER_ID_FKEY, t -> subquery.apply((PaymentP2007_02) t));
    }

    /**
     * A convenience constructor for correlated <code>MULTISET</code>s
     * expressions to the <code>public.payment_p2007_03</code> one-to-many child
     * table.
     */
    public Field<Result<PaymentP2007_03Record>> paymentP2007_03Multiset() {
        return paymentP2007_03Multiset(Function.identity());
    }

    /**
     * A convenience constructor for correlated <code>MULTISET</code>s
     * expressions to the <code>public.payment_p2007_03</code> one-to-many child
     * table.
     */
    public <O extends Record> Field<Result<O>> paymentP2007_03Multiset(Function<? super PaymentP2007_03, ? extends TableLike<O>> subquery) {
        return oneToManyMultiset(Keys.PAYMENT_P2007_03__PAYMENT_P2007_03_CUSTOMER_ID_FKEY, t -> subquery.apply((PaymentP2007_03) t));
    }

    /**
     * A convenience constructor for correlated <code>MULTISET</code>s
     * expressions to the <code>public.payment_p2007_04</code> one-to-many child
     * table.
     */
    public Field<Result<PaymentP2007_04Record>> paymentP2007_04Multiset() {
        return paymentP2007_04Multiset(Function.identity());
    }

    /**
     * A convenience constructor for correlated <code>MULTISET</code>s
     * expressions to the <code>public.payment_p2007_04</code> one-to-many child
     * table.
     */
    public <O extends Record> Field<Result<O>> paymentP2007_04Multiset(Function<? super PaymentP2007_04, ? extends TableLike<O>> subquery) {
        return oneToManyMultiset(Keys.PAYMENT_P2007_04__PAYMENT_P2007_04_CUSTOMER_ID_FKEY, t -> subquery.apply((PaymentP2007_04) t));
    }

    /**
     * A convenience constructor for correlated <code>MULTISET</code>s
     * expressions to the <code>public.payment_p2007_05</code> one-to-many child
     * table.
     */
    public Field<Result<PaymentP2007_05Record>> paymentP2007_05Multiset() {
        return paymentP2007_05Multiset(Function.identity());
    }

    /**
     * A convenience constructor for correlated <code>MULTISET</code>s
     * expressions to the <code>public.payment_p2007_05</code> one-to-many child
     * table.
     */
    public <O extends Record> Field<Result<O>> paymentP2007_05Multiset(Function<? super PaymentP2007_05, ? extends TableLike<O>> subquery) {
        return oneToManyMultiset(Keys.PAYMENT_P2007_05__PAYMENT_P2007_05_CUSTOMER_ID_FKEY, t -> subquery.apply((PaymentP2007_05) t));
    }

    /**
     * A convenience constructor for correlated <code>MULTISET</code>s
     * expressions to the <code>public.payment_p2007_06</code> one-to-many child
     * table.
     */
    public Field<Result<PaymentP2007_06Record>> paymentP2007_06Multiset() {
        return paymentP2007_06Multiset(Function.identity());
    }

    /**
     * A convenience constructor for correlated <code>MULTISET</code>s
     * expressions to the <code>public.payment_p2007_06</code> one-to-many child
     * table.
     */
    public <O extends Record> Field<Result<O>> paymentP2007_06Multiset(Function<? super PaymentP2007_06, ? extends TableLike<O>> subquery) {
        return oneToManyMultiset(Keys.PAYMENT_P2007_06__PAYMENT_P2007_06_CUSTOMER_ID_FKEY, t -> subquery.apply((PaymentP2007_06) t));
    }

    /**
     * A convenience constructor for correlated <code>MULTISET</code>s
     * expressions to the <code>public.rental</code> one-to-many child table.
     */
    public Field<Result<RentalRecord>> rentalMultiset() {
        return rentalMultiset(Function.identity());
    }

    /**
     * A convenience constructor for correlated <code>MULTISET</code>s
     * expressions to the <code>public.rental</code> one-to-many child table.
     */
    public <O extends Record> Field<Result<O>> rentalMultiset(Function<? super Rental, ? extends TableLike<O>> subquery) {
        return oneToManyMultiset(Keys.RENTAL__RENTAL_CUSTOMER_ID_FKEY, t -> subquery.apply((Rental) t));
    }

    @Override
    public Customer as(String alias) {
        return new Customer(DSL.name(alias), this);
    }

    @Override
    public Customer as(Name alias) {
        return new Customer(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Customer rename(String name) {
        return new Customer(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Customer rename(Name name) {
        return new Customer(name, null);
    }

    // -------------------------------------------------------------------------
    // Row10 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row10<Long, Long, String, String, String, Long, Boolean, LocalDate, LocalDateTime, Integer> fieldsRow() {
        return (Row10) super.fieldsRow();
    }
}
