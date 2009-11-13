package org.jggug.kobo.commons.lang

class EquivTest extends GroovyTestCase {

    static class Sample {
        @Equiv
        String name

        @Equiv
        String value

        String ignored

        public String toString() {
            "name=${name}, value=${value}, ignored=${ignored}"
        }
    }

    def s1, s2, s3, s4

    void setUp() {
        s1 = new Sample(name:'John', value:'Good', ignored:'Garbage')
        s2 = new Sample(name:'John', value:'Good', ignored:'Gold')
        s3 = new Sample(name:'John', value:'Bad',  ignored:'Garbage')
        s4 = new Sample(name:'Mike', value:'Good', ignored:'Garbage')
    }

    void testEqualsSameObject() {
        assert s1 == s1
        assert s1.equals(s1)
    }

    void testEqualsDifferentIgnored() {
        assert s1 == s2
        assert s1.equals(s2)
        assert s2.equals(s1)
    }

    void testEqualsDifferentValue() {
        assert s1 != s3
        assert !s1.equals(s3)
        assert !s3.equals(s1)
    }

    void testEqualsDifferentName() {
        assert s1 != s4
        assert !s1.equals(s4)
        assert !s4.equals(s1)
    }

    void testHashCode() {
        assert s1.hashCode() == s1.hashCode()
        assert s1.hashCode() == s2.hashCode()
        assert s1.hashCode() != s3.hashCode()
        assert s1.hashCode() != s4.hashCode()
    }
}
