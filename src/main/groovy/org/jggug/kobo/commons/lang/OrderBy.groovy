/*
 * Copyright 2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jggug.kobo.commons.lang

import org.codehaus.groovy.runtime.NumberAwareComparator
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation

/**
 * @see groovy.util.OrderBy
 */
public class OrderBy<T> implements Comparator<T> {

    private static final String KEY_DESC_ORDERED = OrderBy.class.name + "_descOrdered"

    private final List<Closure> closures
    private final NumberAwareComparator<Object> numberAwareComparator = new NumberAwareComparator<Object>()

    public OrderBy() {
        this.closures = new ArrayList<Closure>()
    }

    public OrderBy(Closure closure) {
        this()
        closures.add(closure)
    }

    public OrderBy(List<Closure> closures) {
        this.closures = closures
    }

    public void add(Closure closure) {
        closures.add(closure)
    }

    public int compare(T object1, T object2) {
        for (Closure closure : closures) {
            Object value1 = closure.call(object1)
            Object value2 = closure.call(object2)
            int result = numberAwareComparator.compare(value1, value2)
            if (result == 0) continue

            // if specified a desc order, reverse the result.
            if (closure.metaClass.properties.name.contains(KEY_DESC_ORDERED)) {
                return result * (-1)
            }

            return result
        }
        return 0
    }

    static Closure desc(Closure closure) {
        closure.metaClass[KEY_DESC_ORDERED] = true
        closure
    }

    static Closure asc(Closure closure) {
        closure // through (as dummy)
    }
 
}
