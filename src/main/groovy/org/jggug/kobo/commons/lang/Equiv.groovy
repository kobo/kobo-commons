package org.jggug.kobo.commons.lang

import org.codehaus.groovy.transform.GroovyASTTransformationClass
import java.lang.annotation.*

@GroovyASTTransformationClass("org.jggug.kobo.commons.lang.EquivASTTransformation")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Equiv {
}
