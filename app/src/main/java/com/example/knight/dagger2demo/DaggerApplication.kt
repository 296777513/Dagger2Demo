package com.example.knight.dagger2demo

import android.util.Log
import com.example.knight.base.application.BaseApplication
import com.example.knight.dagger2demo.atm.component.AppModule
import com.example.knight.dagger2demo.atm.component.DaggerAppComponent

class DaggerApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        Log.i("liyachao", "waste time: ${System.currentTimeMillis() - start}")
    }

    override fun createComponentBuilder(): Any? {
        return DaggerAppComponent.builder().appModule(AppModule(this))
    }
}