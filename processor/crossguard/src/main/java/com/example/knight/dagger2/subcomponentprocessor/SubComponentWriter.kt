package com.example.knight.dagger2.subcomponentprocessor

import com.example.knight.dagger.DaggerClassHolder
import com.example.knight.dagger.SubModule
import com.example.knight.dagger2.utils.constructAnnotationLiteral
import com.squareup.javapoet.AnnotationSpec
import com.squareup.javapoet.CodeBlock
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.TypeSpec
import javax.annotation.processing.Filer
import javax.lang.model.element.Modifier

class SubComponentWriter(private val filer: Filer) {


    fun writeSubComponentFile(subComponentModel: SubComponentModel) {
        val appModuleCodeBlock =
            subComponentModel.appModules.map {
                CodeBlock.of(
                    "@\$T(\$T.class)",
                    DaggerClassHolder::class.java,
                    it
                )
            }.constructAnnotationLiteral()


        val appGraphCodeBlock =
            subComponentModel.appGraphs.map {
                CodeBlock.of(
                    "@\$T(\$T.class)",
                    DaggerClassHolder::class.java,
                    it
                )
            }.constructAnnotationLiteral()
        val daggerPluginAnnotation = AnnotationSpec.builder(SubModule::class.java)
            .addMember("daggerDeclaration", "\$L.class", subComponentModel.daggerDeclaration)
            .addMember("appModules", "\$L", appModuleCodeBlock)
            .addMember("appGraphs", "\$L", appGraphCodeBlock)
            .build()
        val pluginClass = TypeSpec.classBuilder(subComponentModel.generatedClassName)
            .addModifiers(Modifier.PUBLIC)
            .addAnnotation(daggerPluginAnnotation)
            .build()

        JavaFile.builder(DAGGER_SUB_MODULE_PACKAGE, pluginClass).build().writeTo(filer)
    }
}