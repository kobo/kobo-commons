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

import org.jggug.kobo.commons.util.DebugUtils;

class DebugUtilsTest extends GroovyTestCase {

    void setUp() {
        DebugUtils.initialize();
    }

    void testUpperLowerCase() throws Exception {
        assert "string" == "String".toUpperCase().tap{
            assert it == "STRING"
        }.toLowerCase().tap {
            assert it == "string"
        }
    }

    void testGrep() throws Exception {
        def list = [1,2,3,4,5,6,7,8,9,10,11,12];
        assert [12] == list.grep { it % 2 == 0 }.tap {
            assert it == [2,4,6,8,10,12]
        }.grep {it % 3 == 0}.tap {
            assert it == [6,12]
        }.grep {it == 12}
    }

}
