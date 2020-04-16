package com.example.knight.base.application

import android.app.Application
import com.example.knight.dagger.Dagger2ComponentFactory
import com.example.knight.dagger.Graph
import com.example.knight.dagger.TopLevelComponentProvider

abstract class BaseApplication : Application(), TopLevelComponentProvider {

    private lateinit var topLevelComponent: Graph

    companion object {
        lateinit var instance: BaseApplication
    }

    init {
        Dagger2ComponentFactory.init(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        topLevelComponent = buildComponent()
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
        return component(T::class.java)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Graph> component(graphClass: Class<T>): T {
        return topLevelComponent as T
    }

    open fun createComponentBuilder(): Any? = null
}