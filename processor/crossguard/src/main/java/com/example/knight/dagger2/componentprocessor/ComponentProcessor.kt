package com.example.knight.dagger2.componentprocessor

import com.example.knight.dagger.ComponentDeclaration
import com.example.knight.dagger.SubModule
import com.example.knight.dagger2.CrossGuardProcessor
import com.example.knight.dagger2.safeCast
import com.example.knight.dagger2.subcomponentprocessor.DAGGER_SUB_MODULE_PACKAGE
import com.example.knight.dagger2.subcomponentprocessor.SubComponentModel
import com.example.knight.dagger2.subcomponentprocessor.toModel
import com.example.knight.dagger2.utils.toSortedCollection
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.lang.model.util.Types

class ComponentProcessor : CrossGuardProcessor {

    private fun getSubComponentModelOnClassPath(
        elementUtils: Elements,
        typeUtils: Types
    ): ArrayList<SubComponentModel> {
        val daggerModulesPackageElement = elementUtils.getPackageElement(DAGGER_SUB_MODULE_PACKAGE)
        return ArrayList(daggerModulesPackageElement.enclosedElements.mapNotNull { module ->
            module.getAnnotation(
                SubModule::class.java
            )
        }.map { it.toModel(typeUtils) })
    }

    override fun handle(chain: CrossGuardProcessor.Chain): Boolean {
        val roundEnv = chain.request().roundEnv
        val elementUtils = chain.request().elementUtils
        val typeUtils = chain.request().typeUtils
        val filer = chain.request().filer
        val subComponentModel = chain.request().model.safeCast<SubComponentModel>()

        @Suppress("UNCHECKED_CAST")
        val componentDeclarationElements =
            roundEnv.getElementsAnnotatedWith(ComponentDeclaration::class.java) as Set<TypeElement>
        val componentDeclarationElement = when {
            componentDeclarationElements.isEmpty() -> {
                return false
            }
            componentDeclarationElements.size == 1 -> {
                componentDeclarationElements.first()
            }
            else -> {
                val declarations =
                    componentDeclarationElements.sortedBy { it.qualifiedName.toString() }
                        .joinToString { it.qualifiedName }
                throw Exception("A single componentDeclaration is allowed per Application. Found $declarations}")
            }
        }
        componentDeclarationElement as TypeElement
        subComponentModel?.appGraphs?.forEach {
            System.out.println("graph: " + it.simpleName())
        }
        val allComponentModels =
            getSubComponentModelOnClassPath(elementUtils, typeUtils).apply {
                subComponentModel?.let(this::add)
            }
        println("sub models size: ${allComponentModels.size}")
        println("subComponentModel: ${subComponentModel == null}")
        val appGraphs = allComponentModels.flatMap {
            it.appGraphs
        }.toSortedCollection()
        val appModules = allComponentModels.flatMap {
            it.appModules
        }.toSortedCollection()

        appGraphs.forEach {
            println("final graph: $it")
        }
        appModules.forEach {
            println("final module: $it")
        }
        val componentModel = ComponentModel(
            componentDeclarationElement,
            appGraphs,
            appModules
        )
        ComponentWriter(filer, elementUtils).writeComponents(componentModel)
        chain.request().model = componentModel

        return chain.process()
    }
}