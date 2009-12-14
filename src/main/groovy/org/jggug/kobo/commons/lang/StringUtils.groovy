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

class StringUtils {

    static void extendMetaClass() {
        ExpandoMetaClass.enableGlobally()
        String.metaClass.tr = { String source, String replacement ->
            return StringUtils.tr(delegate, source, replacement)
        }
    }

    static void revertMetaClass() {
        String.metaClass = null
    }

    static String tr(String text, String source, String replacement) {
        if (!text || !source) { return text }
        source = expandTr(source)
        replacement = expandTr(replacement)

        // padding replacement with a last character, if necessary
        replacement = replacement.padRight(source.size(), replacement[replacement.size()-1])

        return text.collect { original ->
            if (source.contains(original)) {
                replacement[source.indexOf(original)]
            } else {
                original
            }
        }.join()
    }

    private static String expandTr(String text) {
        if (!text.contains('-')) { return text }
        return text.replaceAll(/(\S)-(\S)/, { all, begin, end -> (begin..end).join() })
    }

}
