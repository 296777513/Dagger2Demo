package com.example.knight.dagger2demo

import com.example.knight.dagger.AppGraphDeclaration
import com.example.knight.dagger.AppModuleDeclaration
import com.example.knight.dagger.ComponentDeclaration
import com.example.knight.dagger.DaggerDeclaration
import dagger.Module

@DaggerDeclaration
class AppDagger {

    @AppGraphDeclaration
    interface AppGraph

    @Module
    @AppModuleDeclaration
    interface AppModule
}

@ComponentDeclaration
interface AppComponent