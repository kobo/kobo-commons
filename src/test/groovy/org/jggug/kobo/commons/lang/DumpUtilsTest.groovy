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

class DumpUtilsTest extends GroovyTestCase {

    void setUp() {
        DumpUtils.extendMetaClass()
    }

    void test_dumpHex_primitiveByteArray_size32() {
        byte[] data = "0123456789abcdef0123456789ABCDEF".bytes
        assert DumpUtils.dumpHex(data) ==
            '''+0 +1 +2 +3 +4 +5 +6 +7 +8 +9 +A +B +C +D +E +F 0123456789ABCDEF
              |-----------+-----------+-----------+-----------+----------------
              |30 31 32 33 34 35 36 37 38 39 61 62 63 64 65 66 0123456789abcdef
              |30 31 32 33 34 35 36 37 38 39 41 42 43 44 45 46 0123456789ABCDEF
              |-----------+-----------+-----------+-----------+----------------'''.stripMargin()
    }

    void test_dumpHex_primitiveByteArray_size31() {
        byte[] data = "0123456789abcdef0123456789ABCDE".bytes
        assert DumpUtils.dumpHex(data) ==
            '''+0 +1 +2 +3 +4 +5 +6 +7 +8 +9 +A +B +C +D +E +F 0123456789ABCDEF
              |-----------+-----------+-----------+-----------+----------------
              |30 31 32 33 34 35 36 37 38 39 61 62 63 64 65 66 0123456789abcdef
              |30 31 32 33 34 35 36 37 38 39 41 42 43 44 45    0123456789ABCDE
              |-----------+-----------+-----------+-----------+----------------'''.stripMargin()
    }

    void test_dumpHex_primitiveByteArray_size17() {
        byte[] data = "0123456789abcdef0".bytes
        assert DumpUtils.dumpHex(data) ==
            '''+0 +1 +2 +3 +4 +5 +6 +7 +8 +9 +A +B +C +D +E +F 0123456789ABCDEF
              |-----------+-----------+-----------+-----------+----------------
              |30 31 32 33 34 35 36 37 38 39 61 62 63 64 65 66 0123456789abcdef
              |30                                              0
              |-----------+-----------+-----------+-----------+----------------'''.stripMargin()
    }

    void test_dumpHex_primitiveByteArray_size16() {
        byte[] data = "0123456789abcdef".bytes
        assert DumpUtils.dumpHex(data) ==
            '''+0 +1 +2 +3 +4 +5 +6 +7 +8 +9 +A +B +C +D +E +F 0123456789ABCDEF
              |-----------+-----------+-----------+-----------+----------------
              |30 31 32 33 34 35 36 37 38 39 61 62 63 64 65 66 0123456789abcdef
              |-----------+-----------+-----------+-----------+----------------'''.stripMargin()
    }

    void test_dumpHex_primitiveByteArray_size15() {
        byte[] data = "0123456789abcde".bytes
        assert DumpUtils.dumpHex(data) ==
            '''+0 +1 +2 +3 +4 +5 +6 +7 +8 +9 +A +B +C +D +E +F 0123456789ABCDEF
              |-----------+-----------+-----------+-----------+----------------
              |30 31 32 33 34 35 36 37 38 39 61 62 63 64 65    0123456789abcde
              |-----------+-----------+-----------+-----------+----------------'''.stripMargin()
    }

    void test_dumpHex_primitiveByteArray_size1() {
        byte[] data = "0".bytes
        assert DumpUtils.dumpHex(data) ==
            '''+0 +1 +2 +3 +4 +5 +6 +7 +8 +9 +A +B +C +D +E +F 0123456789ABCDEF
              |-----------+-----------+-----------+-----------+----------------
              |30                                              0
              |-----------+-----------+-----------+-----------+----------------'''.stripMargin()
    }

    void test_dumpHex_primitiveByteArray_size0() {
        byte[] data = "".bytes
        assert DumpUtils.dumpHex(data) ==
            '''+0 +1 +2 +3 +4 +5 +6 +7 +8 +9 +A +B +C +D +E +F 0123456789ABCDEF
              |-----------+-----------+-----------+-----------+----------------
              |-----------+-----------+-----------+-----------+----------------'''.stripMargin()
    }

//    void test_dumpHex_primitiveByteArray_viaExpandedMethod() {
//        byte[] data = "0123456789abcdef0123456789ABCDEF".bytes
//        assert data.dumpHex() ==
//            '''+0 +1 +2 +3 +4 +5 +6 +7 +8 +9 +A +B +C +D +E +F 0123456789ABCDEF
//              |-----------+-----------+-----------+-----------+----------------
//              |30 31 32 33 34 35 36 37 38 39 61 62 63 64 65 66 0123456789abcdef
//              |30 31 32 33 34 35 36 37 38 39 41 42 43 44 45 46 0123456789ABCDEF
//              |-----------+-----------+-----------+-----------+----------------'''.stripMargin()
//    }
}
