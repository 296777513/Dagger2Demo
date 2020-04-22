package com.example.knight.login

import com.example.knight.base.command.Command
import com.example.knight.base.command.CommandRouter
import com.example.knight.base.dagger.BaseGraph
import com.example.knight.dagger.AppGraphDeclaration
import com.example.knight.dagger.AppModuleDeclaration
import com.example.knight.dagger.DaggerDeclaration
import com.example.knight.login.command.LoginCommand
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import javax.inject.Scope

@DaggerDeclaration
class LoginDagger {

    @Scope
    @Retention(AnnotationRetention.RUNTIME)
    internal annotation class LoginScope

    @Module
    interface LoginModule {
        @Binds
        @IntoMap
        @StringKey("login")
        fun loginCommand(command: LoginCommand): Command

        fun router(): CommandRouter

        companion object {
            @Provides
            fun hints() = "I am injected data"
        }
    }

    @Module
    @AppModuleDeclaration
    interface AppModule {

        @LoginScope
        @ContributesAndroidInjector(modules = [LoginModule::class])
        fun loginActivity(): LoginActivity
    }

    @AppGraphDeclaration
    interface AppGraph : BaseGraph
}

