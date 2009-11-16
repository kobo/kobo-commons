package org.jggug.kobo.commons.lang

class StringUtils {

    static String tr(String self, String targets, String replacements) {
        if (targets.size() != replacements.size()) {
            throw new IllegalArgumentException('Unmatch size of targets and replacements')
        }
        return self.collect { original ->
            println "original:"+original
            for (int i = 0; i < targets.size(); i++) {
                println "target:"+targets[i]
                if (original == targets[i]) {
                    println "target==original->"+ replacements[i]
                    return replacements[i]
                }
            }
            return original
        }.join('')
    }
}
