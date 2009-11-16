package org.jggug.kobo.commons.lang

class StringCategoryTest extends GroovyTestCase {

    void testTr() {
        use(StringCategory) {
            assert "abcdefghijklmn".tr("abc", "ABC") == "ABCdefghijklmn"
            assert "abcdefghijklmn".tr("a-n", "A-M") == "ABCDEFGHIJKLMM"
        }
    }
}
