/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
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
 * For more information, please visit: http://www.jooq.org/licenses
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

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.Internal.*;
import static org.jooq.impl.Keywords.*;
import static org.jooq.impl.Names.*;
import static org.jooq.impl.SQLDataType.*;
import static org.jooq.impl.Tools.*;
import static org.jooq.impl.Tools.BooleanDataKey.*;
import static org.jooq.impl.Tools.ExtendedDataKey.*;
import static org.jooq.impl.Tools.SimpleDataKey.*;
import static org.jooq.SQLDialect.*;

import org.jooq.*;
import org.jooq.Function1;
import org.jooq.Record;
import org.jooq.conf.*;
import org.jooq.impl.*;
import org.jooq.impl.QOM.*;
import org.jooq.tools.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;


/**
 * The <code>JSON GET ATTRIBUTE</code> statement.
 */
@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
final class JSONGetAttribute
extends
    AbstractField<JSON>
implements
    QOM.JSONGetAttribute
{

    final Field<JSON>   field;
    final Field<String> attribute;

    JSONGetAttribute(
        Field<JSON> field,
        Field<String> attribute
    ) {
        super(
            N_JSON_GET_ATTRIBUTE,
            allNotNull(JSON, field, attribute)
        );

        this.field = nullSafeNotNull(field, JSON);
        this.attribute = nullSafeNotNull(attribute, VARCHAR);
    }

    // -------------------------------------------------------------------------
    // XXX: QueryPart API
    // -------------------------------------------------------------------------

    @Override
    public final void accept(Context<?> ctx) {
        switch (ctx.family()) {




























            case MARIADB:
            case MYSQL:
            case SQLITE:
                ctx.visit(function(N_JSON_EXTRACT, JSON, field, inline("$.").concat(attribute)));
                break;

            default:
                ctx.sql('(').visit(field).sql("->").visit(attribute).sql(')');
                break;
        }
    }














    // -------------------------------------------------------------------------
    // XXX: Query Object Model
    // -------------------------------------------------------------------------

    @Override
    public final Field<JSON> $field() {
        return field;
    }

    @Override
    public final Field<String> $attribute() {
        return attribute;
    }

    @Override
    public final QOM.JSONGetAttribute $field(Field<JSON> newValue) {
        return $constructor().apply(newValue, $attribute());
    }

    @Override
    public final QOM.JSONGetAttribute $attribute(Field<String> newValue) {
        return $constructor().apply($field(), newValue);
    }

    public final Function2<? super Field<JSON>, ? super Field<String>, ? extends QOM.JSONGetAttribute> $constructor() {
        return (a1, a2) -> new JSONGetAttribute(a1, a2);
    }
























    // -------------------------------------------------------------------------
    // XXX: The Object API
    // -------------------------------------------------------------------------

    @Override
    public boolean equals(Object that) {
        if (that instanceof QOM.JSONGetAttribute o) {
            return
                StringUtils.equals($field(), o.$field()) &&
                StringUtils.equals($attribute(), o.$attribute())
            ;
        }
        else
            return super.equals(that);
    }
}