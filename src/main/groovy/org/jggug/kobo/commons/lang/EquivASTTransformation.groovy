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

import java.util.concurrent.atomic.*
import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.expr.*
import org.codehaus.groovy.ast.stmt.*
import org.codehaus.groovy.ast.builder.*
import org.codehaus.groovy.control.*
import org.codehaus.groovy.transform.*
import org.objectweb.asm.Opcodes
import static org.objectweb.asm.Opcodes.*

@GroovyASTTransformation(phase = CompilePhase.CANONICALIZATION)
public class EquivASTTransformation extends ClassCodeVisitorSupport implements ASTTransformation {

    SourceUnit sourceUnit   // forced to override by super class
    final static Set<Class> visitedClass = new HashSet<Class>().asSynchronized()

    public void visit(ASTNode[] nodes, SourceUnit source) {
        AnnotatedNode classNode = (AnnotatedNode) nodes[1]
        AnnotationNode includeAnnotation = (AnnotationNode) nodes[0]
        def targetClass = classNode.owner
        if (visitedClass.contains(targetClass)) return

        List<ASTNode> astNodes = new AstBuilder().buildFromSpec {
            method('equals', Opcodes.ACC_PUBLIC, boolean) {
                parameters {
                    parameter 'obj': Object.class
                }
                exceptions {}
                block {
                    owner.expression.addAll(new AstBuilder().buildFromCode {
                        def targetObj = obj
                        if (targetObj == null) return false
                        if (this.getClass() != targetObj.getClass()) return false
                        return this.class.declaredFields.findAll{ it.getAnnotation(org.jggug.kobo.commons.lang.Equiv.class) }.every { field ->
                            def name = field.name
                            def thisValue = this."$name"
                            def targetObjValue = targetObj."$name"
                            thisValue == targetObjValue
                        }
                    })
                }
                annotations {}
            }
            method('hashCode', Opcodes.ACC_PUBLIC, int) {
                parameters {}
                exceptions {}
                block {
                    owner.expression.addAll(new AstBuilder().buildFromCode {
                        int prime = "31" as int
                        int result = "1" as int
                        this.class.declaredFields.findAll{ it.getAnnotation(org.jggug.kobo.commons.lang.Equiv.class) }.each { field ->
                            def name = field.name
                            def value = this."$name"
                            if (value) {
                                result = prime * result + value.hashCode()
                            }
                        }
                        return result
                    })
                }
                annotations {}
            }
        }

        // This code assumes the first one of cns is the parent class of annotated class.
        // This behavior might not be assured, and it might be changed in future.
        // but I cannot find  other way to do this.
        targetClass.addMethod(astNodes[0])
        targetClass.addMethod(astNodes[1])

        visitedClass.add(targetClass)
    }

}
