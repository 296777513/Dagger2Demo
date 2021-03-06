package com.example.knight.dagger2.componentprocessor

import com.example.knight.dagger2.utils.constructAnnotationLiteral
import com.squareup.javapoet.*
import dagger.Component
import javax.annotation.processing.Filer
import javax.inject.Singleton
import javax.lang.model.element.Modifier
import javax.lang.model.util.Elements

class ComponentWriter(private val filer: Filer, private val elementUtils: Elements) {


    fun writeComponents(componentModel: ComponentModel) {
        val generatedClassName = componentModel.getGeneratedScabbardClassName(elementUtils)
        val moduleCodeBlock = componentModel.appModules.map { CodeBlock.of("\$T.class", it) }
            .constructAnnotationLiteral()
        val componentAnnotation = (AnnotationSpec.builder(Component::class.java))
            .addMember("modules", moduleCodeBlock)
            .build()
        val singleScopeAnnotation = AnnotationSpec.builder(Singleton::class.java).build()
        val componentBuilderInterface = with(TypeSpec.interfaceBuilder("Builder")) {
            addAnnotation(Component.Builder::class.java)
            addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            addMethod(
                MethodSpec
                    .methodBuilder("builder")
                    .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                    .returns(
                        ClassName.get(
                            generatedClassName.packageName(),
                            generatedClassName.simpleName()
                        )
                    )
                    .build()
            )
            build()
        }

        val componentFactoryInterface = with(TypeSpec.interfaceBuilder("Factory")) {
            addAnnotation(Component.Factory::class.java)
            addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            addSuperinterface(
                ClassName.get(
                    "",
                    "dagger.android.AndroidInjector.Factory<dagger.android.DaggerApplication>"
                )
            )
            build()
        }

        val componentClassBuilder = TypeSpec.interfaceBuilder(generatedClassName.simpleName())
            .addModifiers(Modifier.PUBLIC)
            .addAnnotation(componentAnnotation)
            .addAnnotation(singleScopeAnnotation)
//            .addType(componentBuilderInterface)
            .addType(componentFactoryInterface)

        componentModel.appGraphs.forEach {
            println("super interface: $it")
            componentClassBuilder.addSuperinterface(it)
        }
        componentClassBuilder.addSuperinterface(
            ClassName.get(
                "",
                "dagger.android.AndroidInjector<dagger.android.DaggerApplication>"
            )
        )
        JavaFile.builder(generatedClassName.packageName(), componentClassBuilder.build())
            .build()
            .writeTo(filer)

    }
}