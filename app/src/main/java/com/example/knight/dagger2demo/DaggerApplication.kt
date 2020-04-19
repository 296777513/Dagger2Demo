package com.example.knight.dagger2demo

import com.example.knight.base.application.BaseApplication

class DaggerApplication : BaseApplication() {
    override fun createComponentBuilder(): Any? {
        return DaggerCrossGuardAppComponent.builder()
    }
}