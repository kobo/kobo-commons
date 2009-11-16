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

    void testTr_Unmatch() {
        try {
            StringUtils.tr("abcdefghijklmn", "ace", "ACEX")
            fail()
        } catch (IllegalArgumentException e) {
        }
    }
}
