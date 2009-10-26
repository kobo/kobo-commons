package org.jggug.kobo.commons.lang

import org.jggug.kobo.commons.lang.Equiv

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

    void testEqualsSameObject() throws Exception {
        println "s1: " + s1
        println s1.class
        println s1.class.declaredFields*.annotations
        println "s1 == s1 -> " + (s1 == s1)
        println "s1.equals(s1) -> " + s1.equals(s1)
        assert s1 == s1
        assert s1.equals(s1)
    }

    void testEqualsDifferentIgnored() throws Exception {
        println "-"*20
        println "s1: " + s1
        println "s2: " + s2
        println "s1 == s2 -> " + (s1 == s2)
        println "s1.equals(s2) ->" + s1.equals(s2)
        println "s2.equals(s1) ->" + s2.equals(s1)
        assert s1 == s2
        assert s1.equals(s2)
        assert s2.equals(s1)
    }

    void testEqualsDifferentValue() throws Exception {
        println "-"*20
        println "s1: " + s1
        println "s3: " + s3
        println "s1 == s3 -> " + (s1 == s3)
        println "s1.equals(s3) ->" + s1.equals(s3)
        println "s3.equals(s1) ->" + s3.equals(s1)
        assert s1 != s3
        assert !s1.equals(s3)
        assert !s3.equals(s1)
    }

    void testEqualsDifferentName() throws Exception {
        println "-"*20
        println "s1: " + s1
        println "s4: " + s4
        println "s1 == s4 -> " + (s1 == s4)
        println "s1.equals(s4) ->" + s1.equals(s4)
        println "s4.equals(s1) ->" + s4.equals(s1)
        assert s1 != s4
        assert !s1.equals(s4)
        assert !s4.equals(s1)
    }

    void testHashCode() throws Exception {

        println "-"*20
        println "s1 -> " + s1.hashCode()
        println "s2 -> " + s2.hashCode()
        println "s3 -> " + s3.hashCode()
        println "s4 -> " + s4.hashCode()
        assert s1.hashCode() == s2.hashCode()
        assert s1.hashCode() != s3.hashCode()
        assert s1.hashCode() != s4.hashCode()
    }
}
