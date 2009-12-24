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

        // because there isn't sort(Comparator) only for Map
        Map.metaClass.sort << { Comparator comparator ->
            return CollectionUtils.sort(delegate, comparator)
        }
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

    static sort(def self, Closure... closures) {
        return sort(self, closures as List)
    }

    static sort(def self, List<Closure> closures) {
        return self.sort(new OrderBy(closures))
    }

    // why isn't there this method in org.codehaus.groovy.runtime.DefaultGroovyMethods ??
    static Map sort(Map self, Comparator comparator) {
        Map result = new LinkedHashMap()
        self.entrySet().asList().sort(comparator).each { entry ->
            result.put(entry.key, entry.value)
        }
        return result
    }
}

