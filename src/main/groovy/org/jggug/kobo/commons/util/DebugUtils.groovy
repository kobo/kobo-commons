package org.jggug.kobo.commons.util;

class DebugUtils {

    public static initialize() {
        ExpandoMetaClass.enableGlobally();
        Object.metaClass.tap = { Closure closure->
            closure.call(delegate)
            delegate
        }
    }

}
