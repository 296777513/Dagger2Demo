package com.example.knight.dagger2.componentprocessor

import com.example.knight.dagger2.CrossGuardModel
import com.squareup.javapoet.ClassName
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements

class ComponentModel(
    val componentDeclaration: TypeElement,
    val appGraphs: Collection<ClassName>,
    val appModules: Collection<ClassName>
) : CrossGuardModel {

    fun getGeneratedScabbardClassName(elementUtils: Elements): ClassName {
        return ClassName.get(
            elementUtils.getPackageOf(componentDeclaration).toString(),
            "CrossGuard${componentDeclaration.simpleName}"
        )
    }
}