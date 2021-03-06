package com.example.knight.dagger2

import com.example.knight.dagger.*
import com.example.knight.dagger2.componentprocessor.ComponentProcessor
import com.example.knight.dagger2.subcomponentprocessor.SubComponentProcessor
import com.google.auto.service.AutoService
import java.util.*
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.lang.model.util.Types

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor::class)
class DaggerProcessor : AbstractProcessor() {

    val options: Map<String, String> get() = processingEnv.options
    val messager: Messager get() = processingEnv.messager
    val filer: Filer get() = processingEnv.filer
    val elementUtils: Elements get() = processingEnv.elementUtils
    val typeUtils: Types get() = processingEnv.typeUtils
    val sourceVersion: SourceVersion get() = processingEnv.sourceVersion
    val locale: Locale get() = processingEnv.locale

    private val usdAnnotations: Set<Class<out Annotation>> = setOf(
        AppGraphDeclaration::class.java,
        AppModuleDeclaration::class.java,
        ComponentDeclaration::class.java,
        DaggerClassHolder::class.java,
        DaggerDeclaration::class.java,
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
