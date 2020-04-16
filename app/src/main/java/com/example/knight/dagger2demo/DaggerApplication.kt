package com.example.knight.dagger2demo

import com.example.knight.base.application.BaseApplication
import com.example.knight.dagger2demo.atm.component.DaggerCrossGuardAppComponent

class DaggerApplication : BaseApplication() {
    override fun createComponentBuilder(): Any? {
        return DaggerCrossGuardAppComponent.builder()
    }
}