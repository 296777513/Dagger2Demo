package com.example.knight.login

import com.example.knight.base.command.Command
import com.example.knight.base.command.CommandRouter
import com.example.knight.base.dagger.BaseGraph
import com.example.knight.dagger.AppGraphDeclaration
import com.example.knight.dagger.AppModuleDeclaration
import com.example.knight.dagger.SubComponentDeclaration
import com.example.knight.dagger.SubcomponentBuilder
import com.example.knight.login.command.LoginCommand
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import javax.inject.Scope

@SubComponentDeclaration
class LoginDagger {
    @LoginScope
    @Subcomponent(modules = [LoginModule::class])
    interface LoginComponent : BaseGraph {
        fun router(): CommandRouter

        @Subcomponent.Builder
        interface Builder : SubcomponentBuilder<LoginComponent> {
            override fun build(): LoginComponent
        }
    }

    @Module
    interface LoginModule {

        @Binds
        @IntoMap
        @StringKey("login")
        fun loginCommand(command: LoginCommand): Command
    }


    @Scope
    @Retention(AnnotationRetention.RUNTIME)
    internal annotation class LoginScope


    @Module
    @AppModuleDeclaration
    abstract class AppModule {

    }

    @AppGraphDeclaration
    interface AppGraph : BaseGraph {
        fun loginBuilder(): LoginComponent.Builder
    }
}

