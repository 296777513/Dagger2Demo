package com.example.knight.dagger2.utils

import com.squareup.javapoet.AnnotationSpec
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.CodeBlock
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.ParameterSpec
import me.eugeniomarletti.kotlin.metadata.shadow.descriptors.Named
import java.lang.Exception
import javax.lang.model.element.AnnotationMirror
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.lang.model.type.ArrayType
import javax.lang.model.type.DeclaredType
import javax.lang.model.type.MirroredTypeException
import javax.lang.model.type.MirroredTypesException
import javax.lang.model.type.TypeMirror
import javax.lang.model.type.WildcardType
import javax.lang.model.util.Elements
import javax.lang.model.util.Types
import kotlin.reflect.KClass

/**
 * Helper method to construct an annotation collection i.e {@Annotation("hello"), @Annotation("world")}.
 */
fun Collection<CodeBlock>.constructAnnotationLiteral() =
    constructCollectionLiteral(CodeBlock.of("{\n"), this, CodeBlock.of("\n}"))

/**
 * Helper method to construct an inline collection i.e myArray = ["hello", "world"].
 */
fun constructCollectionLiteral(
    opening: CodeBlock,
    values: Collection<CodeBlock>,
    closing: CodeBlock,
    separator: CodeBlock = CodeBlock.of(",\n")
): CodeBlock {
    CodeBlock.builder().apply {
        add(opening)
        values.forEachIndexed { index, codeBlock ->
            add(codeBlock)
            if (index != values.size - 1) {
                add(separator)
            }
        }
        add(closing)
        return build()
    }
}


// See https://stackoverflow.com/questions/7687829/java-6-annotation-processing-getting-a-class-from-an-annotation
internal fun Types.typeElementFromAnnotationClass(propertyAccessor: () -> KClass<*>): TypeElement {
    try {
        // This should throw because accessing a class from another module is not allowed.
        propertyAccessor()
    } catch (mte: MirroredTypeException) {
        // But the error contains the type mirror we wanted...
        return asElement(mte.typeMirror) as TypeElement
    }

    throw IllegalStateException("This should never happen.")
}

/**
 * Sorts a class first by it's readable, simple name, and then by the full package name.
 */
@PublishedApi
internal fun classNameComparator(it: ClassName) = it.simpleName() + it.reflectionName()

inline fun List<ClassName>.toSortedCollection(): Collection<ClassName> {
    return this.sortedBy(::classNameComparator)
}
