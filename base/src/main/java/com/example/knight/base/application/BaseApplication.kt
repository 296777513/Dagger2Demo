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

    var start: Long = 0

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        start = System.currentTimeMillis()
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        appGraph = buildComponent()
        Log.i("liyachao", "waste time: ${System.currentTimeMillis() - start}")
    }

    open fun buildComponent(): Graph {
        val componentBuilder = createComponentBuilder()
        checkNotNull(componentBuilder) { "Default build component requires you to override createComponentBuilder." }
        val klass = componentBuilder.javaClass
        val build = klass.getDeclaredMethod("build")
        build.isAccessible = true
        return build.invoke(componentBuilder) as BaseGraph
    }

    inline fun <reified T : Graph> component(): T {
        return appGraph as T
    }

    open fun createComponentBuilder(): Any? = null
}