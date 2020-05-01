package com.example.knight.dagger2demo

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.example.knight.base.application.BaseApplication
import com.facebook.buck.android.support.exopackage.ApplicationLike
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class DaggerApplication(application: Application) : BaseApplication(application), ApplicationLike {
    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        Log.i("liyachao","DaggerApplication")
    }

    override fun onCreate() {
        super.onCreate()
        Log.i("liyachao","DaggerApplication onCreate")
    }

    override fun createComponentFactory(): AndroidInjector<DaggerApplication> {
        return DaggerCrossGuardAppComponent.factory().create(this)
    }
}