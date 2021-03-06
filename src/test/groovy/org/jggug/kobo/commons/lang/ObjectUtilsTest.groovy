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

class ObjectUtilsTest extends GroovyTestCase {

    void setUp() {
        ObjectUtils.extendMetaClass()
    }

    void testTap_String() {
        assert "string" == ObjectUtils.tap("String".toUpperCase(), {
            assert it == "STRING"
        }).toLowerCase()
    }

    void testTap_List() {
        assert [2, 4, 6] == ObjectUtils.tap((1..6).grep { it % 2 == 0 }, {
            assert it == [2, 4, 6]
        })
    }

    void testExtendMetaClass_String() {
        assert "string" == "String".toUpperCase().tap {
            assert it == "STRING"
        }.toLowerCase().tap {
            assert it == "string"
        }
    }

    void testExtendMetaClass_List() {
        assert [12, 99] == (1..12).grep { it % 2 == 0 }.tap {
            assert it == [2, 4, 6, 8, 10, 12]
        }.grep {it % 3 == 0}.tap {
            assert it == [6, 12]
        }.grep {it == 12}.tap {
            assert it == [12]
        }.tap {
            it << 99  // it's possible to modify an object in a closure of tap
        }
    }

}
