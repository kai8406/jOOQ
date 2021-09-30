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
import static org.jooq.impl.Tools.DataExtendedKey.*;
import static org.jooq.impl.Tools.DataKey.*;
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
 * The <code>CONDITION</code> statement.
 */
@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
final class FieldCondition
extends
    AbstractCondition
implements
    MFieldCondition
{

    final Field<Boolean> field;

    FieldCondition(
        Field<Boolean> field
    ) {

        this.field = nullSafeNotNull(field, BOOLEAN);
    }

    // -------------------------------------------------------------------------
    // XXX: QueryPart API
    // -------------------------------------------------------------------------

    @Override
    public final void accept(Context<?> ctx) {
        switch (ctx.family()) {















            case CUBRID:
            case FIREBIRD:
                ctx.visit(field.eq(inline(true, field.getDataType())));
                break;

            default:
                ctx.visit(Tools.hasDefaultConverter(field) ? field : field.eq(inline(true, field.getDataType())));
                break;
        }
    }












    // -------------------------------------------------------------------------
    // XXX: Query Object Model
    // -------------------------------------------------------------------------

    @Override
    public final Field<Boolean> $field() {
        return field;
    }

    @Override
    public final MFieldCondition $field(MField<Boolean> newValue) {
        return new FieldCondition((Field<Boolean>) newValue);
    }

    public final Function1<? super MField<Boolean>, ? extends Condition> constructor() {
        return (a1) -> DSL.condition((Field<Boolean>) a1);
    }

    @Override
    public final MQueryPart replace(
        Predicate<? super MQueryPart> recurse,
        Function1<? super MQueryPart, ? extends MQueryPart> replacement
    ) {
        return QOM.replace(
            this,
            $field(),
            constructor()::apply,
            recurse,
            replacement
        );
    }

    @Override
    public final <R> R traverse(
        R init,
        Predicate<? super R> abort,
        Predicate<? super MQueryPart> recurse,
        BiFunction<? super R, ? super MQueryPart, ? extends R> accumulate
    ) {
        return QOM.traverse(
            init, abort, recurse, accumulate, this,
            $field()
        );
    }

    // -------------------------------------------------------------------------
    // XXX: The Object API
    // -------------------------------------------------------------------------

    @Override
    public boolean equals(Object that) {
        if (that instanceof FieldCondition) {
            return
                StringUtils.equals($field(), ((FieldCondition) that).$field())
            ;
        }
        else
            return super.equals(that);
    }
}
