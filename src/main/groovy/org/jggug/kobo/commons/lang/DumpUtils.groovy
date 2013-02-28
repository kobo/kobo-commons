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

class DumpUtils {

    private static final String RULER_LINE = "+0 +1 +2 +3 +4 +5 +6 +7 +8 +9 +A +B +C +D +E +F 0123456789ABCDEF"
    private static final String SEPARATOR_LINE = "-----------+-----------+-----------+-----------+----------------"
    private static final int NUMBER_PER_LINE = 16

    static void extendMetaClass() {
        ExpandoMetaClass.enableGlobally()
        Byte[].metaClass.dumpHex = { Closure closure ->
            return DumpUtils.dumpHex(delegate, closure)
        }
    }

    static String dumpHex(byte[] bytes) {
        doDumpHex(bytes)
    }

    static String dumpHex(Byte[] bytes) {
        doDumpHex(bytes.collect { (it == null) ? 0 : it })
    }

    private static doDumpHex(bytes) {
        def sw = new StringWriter()
        sw.withPrintWriter { pw ->
            pw.println RULER_LINE
            pw.println SEPARATOR_LINE
            for (int i = 0; i < bytes.size(); i += NUMBER_PER_LINE) {
                int endIndex = [i + NUMBER_PER_LINE, bytes.size()].min()
                def bytesAtLine = bytes[i..<endIndex]
                pw.println formatLine(bytesAtLine)
            }
            pw.print SEPARATOR_LINE
        }
        return sw.toString()
    }

    private static String formatLine(bytesAtLine) {
        bytesAtLine += [null] * (NUMBER_PER_LINE - bytesAtLine.size()) // padding at end of line
        def digitPart = bytesAtLine.collect { toDisplayDigit(it) }.join(" ")
        def asciiPart = bytesAtLine.collect { toDisplayAscii(it) }.join()
        return digitPart + " " + asciiPart.trim() // trimming tail spaces
    }

    private static toDisplayDigit(b) {
        if (b == null) return "  "
        return String.format("%02x", b)
    }

    private static toDisplayAscii(b) {
        if (b == null) return " "
        char c = (char) b
        return (c ==~ /\p{Print}/) ? c : "."
    }

}
