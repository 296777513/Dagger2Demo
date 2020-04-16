package com.example.knight.dagger2demo.atm.component

import com.example.knight.base.dagger.BaseGraph
import com.example.knight.base.module.BaseModule
import com.example.knight.dagger.ComponentDeclaration
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
@ComponentDeclaration
interface AppComponent : BaseGraph {

}

@Module(includes = [BaseModule::class])
class AppModule