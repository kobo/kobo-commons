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

class EquivWithNestedClassTest extends GroovyTestCase {

    static class A {
        static class B {
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
        }
    }

    def s1, s2, s3, s4

    void setUp() {
        s1 = new A.B.Sample(name:'John', value:'Good', ignored:'Garbage')
        s2 = new A.B.Sample(name:'John', value:'Good', ignored:'Gold')
        s3 = new A.B.Sample(name:'John', value:'Bad',  ignored:'Garbage')
        s4 = new A.B.Sample(name:'Mike', value:'Good', ignored:'Garbage')
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
