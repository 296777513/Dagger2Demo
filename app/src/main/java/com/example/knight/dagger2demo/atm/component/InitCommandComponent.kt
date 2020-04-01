package com.example.knight.dagger2demo.atm.component

import com.example.knight.dagger2demo.atm.module.HelloWorldModule
import com.example.knight.dagger2demo.atm.module.LoginModule
import com.example.knight.dagger2demo.atm.router.CommandRouter
import dagger.Subcomponent
import javax.inject.Scope

@InitCommandScope
@Subcomponent(modules = [HelloWorldModule::class, LoginModule::class])
interface InitCommandComponent {
    fun router(): CommandRouter

    @Subcomponent.Builder
    interface Builder {
        fun build(): InitCommandComponent
    }
}


@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class InitCommandScope
