package com.example.knight.login.component

import com.example.knight.base.command.CommandRouter
import com.example.knight.login.module.LoginModule
import dagger.Subcomponent
import javax.inject.Scope

@LoginScope
@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {
    fun router(): CommandRouter

    @Subcomponent.Builder
    interface Builder {
        fun build(): LoginComponent
    }
}


@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class LoginScope
