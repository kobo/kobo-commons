package org.jggug.kobo.commons.lang

class EquivWithVariousTypesTest extends GroovyTestCase {

    static class Sample {
        static Object OBJECT = new Object()
        @Equiv String string = "DEFAULT"
        @Equiv Integer integer = 1234
        @Equiv Map map = [default:'DEFAULT', abc:'あいうえお']
        @Equiv List list = ['default', "あいうえお"]
        @Equiv Set set = ['default', "あいうえお"] as Set
        @Equiv Object object = OBJECT
        @Equiv def variable = OBJECT
    }

    def s0, s1, s2, s3, s4, s5, s6, s7, s8

    void setUp() {
        s0 = new Sample()
        s1 = new Sample()
        s2 = new Sample(string:'MODIFIED')
        s3 = new Sample(integer:777)
        s4 = new Sample(map:[mod:'MODIFIED'])
        s5 = new Sample(list:['MODIFIED'])
        s6 = new Sample(set:['MODIFIED'] as Set)
        s7 = new Sample(object:new Object())
        s8 = new Sample(object:new Object())
    }

    void testEqualsSameObject() {
        assert s1 == s1
        assert s1.equals(s1)
    }

    void testEqualsSameProperties() {
        assert s0 == s1
        assert s0.equals(s1)
        assert s1.equals(s0)
    }

    void testEqualsDifferentString() {
        assert s1 != s2
        assert !s1.equals(s2)
        assert !s2.equals(s1)
    }

    void testEqualsDifferentInteger() {
        assert s1 != s3
        assert !s1.equals(s3)
        assert !s3.equals(s1)
    }

    void testEqualsDifferentMap() {
        assert s1 != s4
        assert !s1.equals(s4)
        assert !s4.equals(s1)
    }

    void testEqualsDifferentList() {
        assert s1 != s5
        assert !s1.equals(s5)
        assert !s5.equals(s1)
    }

    void testEqualsDifferentSet() {
        assert s1 != s6
        assert !s1.equals(s6)
        assert !s6.equals(s1)
    }

    void testEqualsDifferentObject() {
        assert s1 != s7
        assert !s1.equals(s7)
        assert !s7.equals(s1)
    }

    void testEqualsDifferentVariable() {
        assert s1 != s8
        assert !s1.equals(s8)
        assert !s8.equals(s1)
    }

    void testHashCode() {
        assert s1.hashCode() == s0.hashCode()
        assert s1.hashCode() == s1.hashCode()
        assert s1.hashCode() != s2.hashCode()
        assert s1.hashCode() != s3.hashCode()
        assert s1.hashCode() != s4.hashCode()
        assert s1.hashCode() != s4.hashCode()
        assert s1.hashCode() != s5.hashCode()
        assert s1.hashCode() != s6.hashCode()
        assert s1.hashCode() != s7.hashCode()
        assert s1.hashCode() != s8.hashCode()
    }
}
