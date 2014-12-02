/*
 * Copyright 2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
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

class CollectionUtils {

    static void extendMetaClass() {
        ExpandoMetaClass.enableGlobally()
        extend(List, Object[], Iterator, Map)
    }

    private static extend(Class... classes) {
        classes.each { clazz ->
            clazz.metaClass.sort << { Closure... closures ->
                return CollectionUtils.sort(delegate, closures)
            }
            clazz.metaClass.sort << { List<Closure> closures ->
                return CollectionUtils.sort(delegate, closures)
            }
        }
    }

    static sort(Object self, Closure... closures) {
        //println "Object#sort(self, closures as Array): ${self.class}: ${self}"
        return CollectionUtils.sort(self, closures.toList())
    }

    static sort(Object self, List<Closure> closures) {
        //println "Object#sort(closures as List): ${self.class}: ${self}"
        if (self instanceof Map) {
            return self.sort(new OrderBy(closures))
        }
        if (self instanceof Iterator) {
            return self.sort(new OrderBy(closures))
        }
        return self.sort(false, new OrderBy(closures))
    }
}

