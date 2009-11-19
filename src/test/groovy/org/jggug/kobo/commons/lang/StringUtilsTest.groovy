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

class StringUtilsTest extends GroovyTestCase {

    void testTr() {
        assert StringUtils.tr("abcdefghijklmn", "abcdefghijklmn", "ABCDEFGHIJKLMN") == "ABCDEFGHIJKLMN"
        assert StringUtils.tr("abcdefghijklmn", "abc", "ABC") == "ABCdefghijklmn"
        assert StringUtils.tr("abcdefghijklmn", "ace", "ACE") == "AbCdEfghijklmn"
        assert StringUtils.tr("abcdefghijklmn", "afghn", "AFGHN") == "AbcdeFGHijklmN"
        assert StringUtils.tr("abcdefghijklmn", "xyz", "XYZ") == "abcdefghijklmn"
        assert StringUtils.tr("abcdefghijklmn", "", "") == "abcdefghijklmn"
    }

    void testTr_Expand() {
        assert StringUtils.tr("abcdefghijklmn", "a-n", "ABCDEFGHIJKLMN") == "ABCDEFGHIJKLMN"
        assert StringUtils.tr("abcdefghijklmn", "abcdefghijklmn", "A-N") == "ABCDEFGHIJKLMN"
        assert StringUtils.tr("abcdefghijklmn", "a-n", "A-N") == "ABCDEFGHIJKLMN"
        assert StringUtils.tr("abcdefghijklmn", "a-c", "A-C") == "ABCdefghijklmn"
        assert StringUtils.tr("abcdefghijklmn", "af-hn", "AF-HN") == "AbcdeFGHijklmN"
        assert StringUtils.tr("abcdefghijklmn", "x-z", "X-Z") == "abcdefghijklmn"
        assert StringUtils.tr("abcdefghijklmn", "a-cfj-l", "A-CFJ-L") == "ABCdeFghiJKLmn"
        assert StringUtils.tr("-abc-", "-", "*") == "*abc*"
        assert StringUtils.tr("-abcdef--ghijklmn-", "-cfj-l", "*CFJ-L") == "*abCdeF**ghiJKLmn*"
        assert StringUtils.tr("abcdefghijklmn", "a-n", "A-C") == "ABCCCCCCCCCCCC"
        assert StringUtils.tr("abcdefghijklmn", "a-c", "A-N") == "ABCdefghijklmn"
    }

    void tearDown() {
        StringUtils.revertMetaClass()
    }

    void testExtendMetaClass() {
        StringUtils.extendMetaClass()
        assert "abcdefghijklmn".tr("abc", "ABC") == "ABCdefghijklmn"
        assert "abcdefghijklmn".tr("a-n", "A-M") == "ABCDEFGHIJKLMM"
    }

    void testRevertMetaClass() {
        try {
            "abcdefghijklmn".tr("abc", "ABC")
            fail()
        } catch (MissingMethodException e) {
        }
    }
}
