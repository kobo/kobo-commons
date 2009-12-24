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
        extend(List)
    }

    private static extend(classes) {
        classes.each { clazz ->
            clazz.metaClass.sort << { Closure... closures ->
                return CollectionUtils.sort(delegate, closures)
            }
            clazz.metaClass.sort << { List<Closure> closures ->
                return CollectionUtils.sort(delegate, closures)
            }
        }
    }

    static Collection sort(def self, Closure... closures) {
        return sort(self, closures as List)
    }

    static Collection sort(def self, List<Closure> closures) {
        Collections.sort(self, new OrderBy(closures))
        return self
    }

}

