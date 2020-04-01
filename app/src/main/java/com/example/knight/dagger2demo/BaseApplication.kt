package com.example.knight.dagger2demo

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.knight.dagger2demo.atm.component.AppComponent
import com.example.knight.dagger2demo.atm.component.AppModule
import com.example.knight.dagger2demo.atm.component.DaggerAppComponent

class BaseApplication : Application() {
    companion object {
        var appComponent: AppComponent? = null
    }

    var start: Long = 0

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        start = System.currentTimeMillis()
    }


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        Log.i("liyachao", "waste time: ${System.currentTimeMillis() - start}")
    }
}