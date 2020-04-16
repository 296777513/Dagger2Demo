package com.example.knight.dagger2.subcomponentprocessor

import com.example.knight.dagger.SubModule
import com.example.knight.dagger2.CrossGuardModel
import com.example.knight.dagger2.utils.typeElementFromAnnotationClass
import com.squareup.javapoet.ClassName
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Types

const val DAGGER_SUB_MODULE_PACKAGE = "com.example.crossguard.submodules"

class SubComponentModel(
    val daggerDeclaration: TypeElement,
    val appGraphs: Collection<ClassName>,
    val appModules: Collection<ClassName>
) : CrossGuardModel {

    val generatedClassName: ClassName
        get() = ClassName.get(
            DAGGER_SUB_MODULE_PACKAGE,
            "${daggerDeclaration.qualifiedName.toString().substringAfterLast('.')}Module"
        )
}

fun SubModule.toModel(types: Types) = SubComponentModel(
    daggerDeclaration = types.typeElementFromAnnotationClass { daggerDeclaration },
    appGraphs = appGraphs.map { ClassName.get(types.typeElementFromAnnotationClass { it.value }) },
    appModules = appModules.map { ClassName.get(types.typeElementFromAnnotationClass { it.value }) }
)