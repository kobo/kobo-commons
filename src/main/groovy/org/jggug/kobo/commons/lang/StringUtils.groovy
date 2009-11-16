package org.jggug.kobo.commons.lang

class StringUtils {

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
