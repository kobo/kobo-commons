/*
 * Copyright 2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
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

class CollectionUtilsTest extends GroovyTestCase {

    def a1, a2, b1, b2, c1, c2, c3

    void setUp() {
        a1 = [key1:'a', key2:1]
        a2 = [key1:'a', key2:2]
        b1 = [key1:'b', key2:1]
        b2 = [key1:'b', key2:2]
        c1 = [key1:'c', key2:1]
        c2 = [key1:'c', key2:2]
        c3 = [key1:'c', key2:3]
        CollectionUtils.extendMetaClass()
println "-"*30
    }

    void testSort_List_singleCondition() {
        assert [1, 2, 3, 5] == CollectionUtils.sort([5, 3, 1, 2], {it})
        assert [5, 3, 2, 1] == CollectionUtils.sort([5, 3, 1, 2], {-it})
        assert [1, 2, 3, 5] == CollectionUtils.sort([5, 3, 1, 2], [{it}])
        assert [5, 3, 2, 1] == CollectionUtils.sort([5, 3, 1, 2], [{-it}])
    }

    void testSort_List_multiConditions() {
        assert [a1, a2, b1, b2, c1, c2, c3] == CollectionUtils.sort([c3, a1, b2, c1, b1, a2, c2], [{it.key1}, {it.key2}])
        assert [a2, a1, b2, b1, c3, c2, c1] == CollectionUtils.sort([c3, a1, b2, c1, b1, a2, c2], [{it.key1}, {-it.key2}])
        assert [a1, a2, b1, b2, c1, c2, c3] == CollectionUtils.sort([c3, a1, b2, c1, b1, a2, c2], {it.key1}, {it.key2})
        assert [a2, a1, b2, b1, c3, c2, c1] == CollectionUtils.sort([c3, a1, b2, c1, b1, a2, c2], {it.key1}, {-it.key2})
    }

    void testMOP_List_singleCondition() {
        // calling the original method
        assert [1, 2, 3, 5] == [5, 3, 1, 2].sort{it}
        assert [5, 3, 2, 1] == [5, 3, 1, 2].sort{-it}

        // calling kobo's methods
        assert [1, 2, 3, 5] == [5, 3, 1, 2].sort([{it}])
        assert [5, 3, 2, 1] == [5, 3, 1, 2].sort([{-it}])
    }

    void testMOP_List_multiConditions() {
        // calling the original method
        // the following codes works well because the value of hashCode() is composed by each elements of list.
        // these are the legacy codes now.
        assert [a1, a2, b1, b2, c1, c2, c3] == [c3, a1, b2, c1, b1, a2, c2].sort{[it.key1, it.key2]}
        assert [a2, a1, b2, b1, c3, c2, c1] == [c3, a1, b2, c1, b1, a2, c2].sort{[it.key1, -it.key2]}

        // calling kobo's methods
        assert [a1, a2, b1, b2, c1, c2, c3] == [c3, a1, b2, c1, b1, a2, c2].sort([{it.key1}, {it.key2}])
        assert [a2, a1, b2, b1, c3, c2, c1] == [c3, a1, b2, c1, b1, a2, c2].sort([{it.key1}, {-it.key2}])
        assert [a1, a2, b1, b2, c1, c2, c3] == [c3, a1, b2, c1, b1, a2, c2].sort({it.key1}, {it.key2})
        assert [a2, a1, b2, b1, c3, c2, c1] == [c3, a1, b2, c1, b1, a2, c2].sort({it.key1}, {-it.key2})
        assert [a1, a2, b1, b2, c1, c2, c3] == [c3, a1, b2, c1, b1, a2, c2].sort {it.key1} {it.key2}
        assert [a2, a1, b2, b1, c3, c2, c1] == [c3, a1, b2, c1, b1, a2, c2].sort {it.key1} {-it.key2}
    }

}
