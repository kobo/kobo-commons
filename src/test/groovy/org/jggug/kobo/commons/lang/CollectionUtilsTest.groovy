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

    static class Entry {
        def key, value
        String toString() { key + value }
    }

    def aa1, aa2, b1, b2, c1, c2, c3

    void setUp() {
        aa1 = new Entry(key:'aa', value:1)
        aa2 = new Entry(key:'aa', value:2)
        b1  = new Entry(key:'b',  value:1)
        b2  = new Entry(key:'b',  value:2)
        c1  = new Entry(key:'c',  value:1)
        c2  = new Entry(key:'c',  value:2)
        c3  = new Entry(key:'c',  value:3)

        CollectionUtils.extendMetaClass()
    }

    // Util ----------------------------------------------------

    void testSort_Util_List_singleCondition() {
        def data = [5, 3, 1, 2]
        assert [1, 2, 3, 5] == CollectionUtils.sort(data, {it})
        assert [5, 3, 2, 1] == CollectionUtils.sort(data, {-it})
        assert [1, 2, 3, 5] == CollectionUtils.sort(data, [{it}])
        assert [5, 3, 2, 1] == CollectionUtils.sort(data, [{-it}])
        assert CollectionUtils.sort(data, {it}) in List
        assert CollectionUtils.sort(data, [{it}]) in List
    }

    void testSort_Util_List_multiConditions() {
        def data = [c3, aa1, b2, c1, b1, aa2, c2]
        assert [aa1, aa2, b1, b2, c1, c2, c3] == CollectionUtils.sort(data, [{it.key}, {it.value}])
        assert [aa2, aa1, b2, b1, c3, c2, c1] == CollectionUtils.sort(data, [{it.key}, {-it.value}])
        assert [aa1, b1, c1, aa2, b2, c2, c3] == CollectionUtils.sort(data, [{it.value}, {it.key}])
        assert CollectionUtils.sort(data, [{it.key}, {it.value}]) in List
    }

    void testSort_Util_Array() {
        def data = [c3, aa1, b2, c1, b1, aa2, c2]
        assert [aa1, aa2, b1, b2, c1, c2, c3] == CollectionUtils.sort(data.toArray(), [{it.key}, {it.value}])
        assert [aa2, aa1, b2, b1, c3, c2, c1] == CollectionUtils.sort(data.toArray(), [{it.key}, {-it.value}])
        assert [aa1, b1, c1, aa2, b2, c2, c3] == CollectionUtils.sort(data.toArray(), [{it.value}, {it.key}])
        assert CollectionUtils.sort(data.toArray(), [{it.key}, {it.value}]) in Object[]
    }

    void testSort_Util_Iterator() {
        def data = [c3, aa1, b2, c1, b1, aa2, c2]
        assert [aa1, aa2, b1, b2, c1, c2, c3] == CollectionUtils.sort(data.iterator(), [{it.key}, {it.value}]).collect{it}
        assert [aa2, aa1, b2, b1, c3, c2, c1] == CollectionUtils.sort(data.iterator(), [{it.key}, {-it.value}]).collect{it}
        assert [aa1, b1, c1, aa2, b2, c2, c3] == CollectionUtils.sort(data.iterator(), [{it.value}, {it.key}]).collect{it}
        assert CollectionUtils.sort(data.iterator(), [{it.key}, {it.value}]) in Iterator
    }

    void testSort_Util_Map() {
        def data = [d:2, e:1,  b:1, aa:2, c:2]
        assert "[aa:2, b:1, c:2, d:2, e:1]" == CollectionUtils.sort(data, [{it.key}, {it.value}]).toString()
        assert "[b:1, e:1, aa:2, c:2, d:2]" == CollectionUtils.sort(data, [{it.value}, {it.key}]).toString()
        assert "[b:1, e:1, aa:2, c:2, d:2]" == CollectionUtils.sort(data, new OrderBy([{it.value}, {it.key}])).toString()
        assert CollectionUtils.sort(data, [{it.key}, {it.value}]) in Map
    }

    void testSort_Util_aVarietyOfNotifications() {
        def data = [c3, aa1, b2, c1, b1, aa2, c2]
        assert [aa1, aa2, b1, b2, c1, c2, c3] == CollectionUtils.sort(data, [{it.key}, {it.value}])
        assert [aa1, aa2, b1, b2, c1, c2, c3] == CollectionUtils.sort(data, {it.key}, {it.value})
    }

    // MOP ----------------------------------------------------

    void testSort_MOP_List_singleCondition() {
        def data = [5, 3, 1, 2]
        assert [1, 2, 3, 5] == data.sort({it})
        assert [5, 3, 2, 1] == data.sort({-it})
        assert [1, 2, 3, 5] == data.sort([{it}])
        assert [5, 3, 2, 1] == data.sort([{-it}])
        assert data.sort({it}) in List
        assert data.sort([{it}]) in List
    }

    void testSort_MOP_List_multiConditions() {
        def data = [c3, aa1, b2, c1, b1, aa2, c2]
        assert [aa1, aa2, b1, b2, c1, c2, c3] == data.sort([{it.key}, {it.value}])
        assert [aa2, aa1, b2, b1, c3, c2, c1] == data.sort([{it.key}, {-it.value}])
        assert [aa1, b1, c1, aa2, b2, c2, c3] == data.sort([{it.value}, {it.key}])
        assert data.sort([{it.key}, {it.value}]) in List
    }

    void testSort_MOP_Array() {
        def data = [c3, aa1, b2, c1, b1, aa2, c2]
        assert [aa1, aa2, b1, b2, c1, c2, c3] == data.toArray().sort([{it.key}, {it.value}])
        assert [aa2, aa1, b2, b1, c3, c2, c1] == data.toArray().sort([{it.key}, {-it.value}])
        assert [aa1, b1, c1, aa2, b2, c2, c3] == data.toArray().sort([{it.value}, {it.key}])
        assert data.toArray().sort([{it.key}, {it.value}]) in Object[]
    }

    void testSort_MOP_Iterator() {
        def data = [c3, aa1, b2, c1, b1, aa2, c2]
        assert [aa1, aa2, b1, b2, c1, c2, c3] == data.iterator().sort([{it.key}, {it.value}]).collect{it}
        assert [aa2, aa1, b2, b1, c3, c2, c1] == data.iterator().sort([{it.key}, {-it.value}]).collect{it}
        assert [aa1, b1, c1, aa2, b2, c2, c3] == data.iterator().sort([{it.value}, {it.key}]).collect{it}
        assert data.iterator().sort([{it.key}, {it.value}]) in Iterator
    }

    void testSort_MOP_Map() {
        def data = [d:2, e:1,  b:1, aa:2, c:2]
        assert "[aa:2, b:1, c:2, d:2, e:1]" == data.sort([{it.key}, {it.value}]).toString()
        assert "[b:1, e:1, aa:2, c:2, d:2]" == data.sort([{it.value}, {it.key}]).toString()
        assert data.sort([{it.key}, {it.value}]) in Map
    }

    void testSort_MOP_aVarietyOfNotifications() {
        def data = [c3, aa1, b2, c1, b1, aa2, c2]
        assert [aa1, aa2, b1, b2, c1, c2, c3] == data.sort([{it.key}, {it.value}])
        assert [aa1, aa2, b1, b2, c1, c2, c3] == data.sort({it.key}, {it.value}) // recommended
        assert [aa1, aa2, b1, b2, c1, c2, c3] == data.sort {it.key}, {it.value}
        assert [aa1, aa2, b1, b2, c1, c2, c3] == data.sort {it.key} {it.value}
    }

}
