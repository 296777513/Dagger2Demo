package com.example.knight.base.application

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.knight.base.dagger.BaseGraph
import com.example.knight.base.dagger.Graph

abstract class BaseApplication : Application() {
    companion object {
        var appGraph: Graph? = null
        lateinit var instance: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appGraph = buildComponent()
    }

    // Through the reflect, creat the instance of Application(this class is in app module), and other feat modules can take the reference of BaseGraph.
    open fun buildComponent(): Graph {
        val componentBuilder = createComponentBuilder()
        checkNotNull(componentBuilder) { "Default build component requires you to override createComponentBuilder." }
        val klass = componentBuilder.javaClass
        val build = klass.getDeclaredMethod("build")
        build.isAccessible = true
        return build.invoke(componentBuilder) as Graph
    }

    inline fun <reified T : Graph> component(): T {
        return appGraph as T
    }

    open fun createComponentBuilder(): Any? = null
}