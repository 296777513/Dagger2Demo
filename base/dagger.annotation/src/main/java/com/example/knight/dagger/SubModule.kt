package com.example.knight.dagger

import kotlin.reflect.KClass


@Retention(AnnotationRetention.RUNTIME)
annotation class SubModule(
    val daggerDeclaration: KClass<*>,
    val appModules: Array<DaggerClassHolder>,
    val appGraphs: Array<DaggerClassHolder>
)