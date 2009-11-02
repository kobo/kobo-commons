package org.jggug.kobo.commons.lang

import org.jggug.kobo.commons.util.DebugUtils;

class EquivTest extends GroovyTestCase {

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
