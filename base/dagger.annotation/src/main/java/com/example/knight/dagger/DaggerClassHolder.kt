package com.example.knight.dagger

import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
annotation class DaggerClassHolder(val value: KClass<*>)