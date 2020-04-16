package com.example.knight.dagger2

import com.example.knight.dagger.*
import com.example.knight.dagger2.componentprocessor.ComponentProcessor
import com.example.knight.dagger2.componentprocessor.ComponentWriter
import com.example.knight.dagger2.subcomponentprocessor.SubComponentModel
import com.example.knight.dagger2.subcomponentprocessor.SubComponentProcessor
import com.example.knight.dagger2.subcomponentprocessor.SubComponentWriter
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
import me.eugeniomarletti.kotlin.processing.KotlinAbstractProcessor
import java.io.IOException
import java.util.*
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement

@SupportedSourceVersion(SourceVersion.RELEASE_8)
class DaggerProcessor : KotlinAbstractProcessor() {


    private val usdAnnotations: Set<Class<out Annotation>> = setOf(
        AppGraphDeclaration::class.java,
        AppModuleDeclaration::class.java,
        ComponentDeclaration::class.java,
        DaggerClassHolder::class.java,
        SubComponentDeclaration::class.java,
        SubModule::class.java
    )
    private val crossGuardProcessors = listOf(
        SubComponentProcessor(),
        ComponentProcessor()
    )

    override fun getSupportedAnnotationTypes(): Set<String> {
        return usdAnnotations.map { it.canonicalName }.toSet()
    }

    override fun process(annotations: Set<TypeElement>, roundEnv: RoundEnvironment): Boolean {
        println("CrossGuard Processor======>start")
        CrossGuardRealChain(
            0,
            CrossGuardProcessor.ProcessorData(roundEnv, elementUtils, typeUtils, filer),
            crossGuardProcessors
        ).process()
        println("CrossGuard Processor=======>end")
        return false
    }
}
