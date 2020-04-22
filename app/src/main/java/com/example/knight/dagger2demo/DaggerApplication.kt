package com.example.knight.dagger2demo

import com.example.knight.base.application.BaseApplication
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class DaggerApplication : BaseApplication() {
    override fun createComponentFactory(): AndroidInjector<DaggerApplication> {
        return DaggerCrossGuardAppComponent.factory().create(this)
    }
}