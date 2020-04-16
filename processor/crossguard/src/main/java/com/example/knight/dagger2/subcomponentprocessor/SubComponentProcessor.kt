package com.example.knight.dagger2.subcomponentprocessor

import com.example.knight.dagger.AppGraphDeclaration
import com.example.knight.dagger.AppModuleDeclaration
import com.example.knight.dagger.SubComponentDeclaration
import com.example.knight.dagger2.CrossGuardProcessor
import com.example.knight.dagger2.utils.toSortedCollection
import com.squareup.javapoet.ClassName
import java.lang.Exception
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Types

class SubComponentProcessor : CrossGuardProcessor {

    override fun handle(chain: CrossGuardProcessor.Chain): Boolean {
        val roundEnv = chain.request().roundEnv

        @Suppress("UNCHECKED_CAST")
        val appModules = (roundEnv.getElementsAnnotatedWith(
            AppModuleDeclaration::class.java
        ) as Set<TypeElement>).map(ClassName::get)

        @Suppress("UNCHECKED_CAST")
        val appGraphs = (roundEnv.getElementsAnnotatedWith(
            AppGraphDeclaration::class.java
        ) as Set<TypeElement>).map(ClassName::get)

        @Suppress("UNCHECKED_CAST")
        val declarationElements =
            roundEnv.getElementsAnnotatedWith(SubComponentDeclaration::class.java) as Set<TypeElement>
        val declarationElement = when {
            declarationElements.isEmpty() -> {
                val noAnnotationsPresent = appModules.isEmpty() && appGraphs.isEmpty()
                if (noAnnotationsPresent) {
                    return chain.process()
                } else {
                    throw Exception("A SubComponentDeclaration is required in a module that has AppModules or AppGraphs.")
                }
            }
            declarationElements.size == 1 -> {
                declarationElements.first()
            }
            else -> {
                val declarations = declarationElements.sortedBy { it.qualifiedName.toString() }
                    .joinToString { it.qualifiedName }
                throw Exception("A single SubComponentDeclaration is allowed per module. Found $declarations}")
            }
        }
        val subComponentModel = SubComponentModel(
            declarationElement,
            appGraphs.toSortedCollection(),
            appModules.toSortedCollection()
        )
        SubComponentWriter(filer = chain.request().filer).writeSubComponentFile(subComponentModel)
        chain.request().model = subComponentModel
        return chain.process()
    }
}

