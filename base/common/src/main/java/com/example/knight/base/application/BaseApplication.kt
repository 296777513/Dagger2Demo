package com.example.knight.base.application

import com.example.knight.dagger.Dagger2ComponentFactory
import com.example.knight.dagger.Graph
import com.example.knight.dagger.TopLevelComponentProvider
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

abstract class BaseApplication : DaggerApplication(), TopLevelComponentProvider {

    private lateinit var topLevelComponent: Graph
    private lateinit var androidInjector: AndroidInjector<DaggerApplication>

    companion object {
        lateinit var instance: BaseApplication

        inline fun <reified T : Graph> component(): T {
            return instance.component(T::class.java)
        }
    }

    init {
        Dagger2ComponentFactory.init(this)
    }

    override fun onCreate() {
        androidInjector = createComponentFactory()
        super.onCreate()
        instance = this
        topLevelComponent = androidInjector as Graph
    }

    override fun applicationInjector() = androidInjector

    // Through the reflect, creat the instance of Application(this class is in app module), and other feat modules can take the reference of BaseGraph.
//    open fun buildComponent(): Graph {
//        checkNotNull(androidInjector) { "Default build component requires you to override createComponentBuilder." }
//        val klass = androidInjector.javaClass
//        val build = klass.getDeclaredMethod("create")
//        build.isAccessible = true
//        return build.invoke(androidInjector) as Graph
//    }


    @Suppress("UNCHECKED_CAST")
    override fun <T : Graph> component(graphClass: Class<T>): T {
        return topLevelComponent as T
    }

    abstract fun createComponentFactory(): AndroidInjector<DaggerApplication>
}