package com.example.knight.dagger2demo

import com.example.knight.dagger.AppGraphDeclaration
import com.example.knight.dagger.AppModuleDeclaration
import com.example.knight.dagger.ComponentDeclaration
import com.example.knight.dagger.DaggerDeclaration
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

@DaggerDeclaration
class AppDagger {

    @AppGraphDeclaration
    interface AppGraph

    @Module(includes = [AndroidInjectionModule::class])
    @AppModuleDeclaration
    interface AppModule {
        @ContributesAndroidInjector
        fun contributeMainActivity(): MainActivity
    }
}

@ComponentDeclaration
interface AppComponent