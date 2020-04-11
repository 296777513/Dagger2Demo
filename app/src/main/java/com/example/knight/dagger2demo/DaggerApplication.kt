package com.example.knight.dagger2demo

import com.example.knight.base.application.BaseApplication
import com.example.knight.dagger2demo.atm.component.AppModule
import com.example.knight.dagger2demo.atm.component.DaggerAppComponent

class DaggerApplication : BaseApplication() {


    override fun createComponentBuilder(): Any? {
        return DaggerAppComponent.builder().appModule(AppModule(this))
    }
}