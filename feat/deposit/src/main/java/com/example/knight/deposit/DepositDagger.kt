package com.example.knight.deposit

import com.example.knight.base.command.Command
import com.example.knight.base.command.CommandRouter
import com.example.knight.base.dagger.BaseGraph
import com.example.knight.base.module.LogoutModule
import com.example.knight.dagger.AppGraphDeclaration
import com.example.knight.dagger.AppModuleDeclaration
import com.example.knight.dagger.DaggerDeclaration
import com.example.knight.dagger.SubcomponentBuilder
import com.example.knight.deposit.command.DepositCommand
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import javax.inject.Scope

@DaggerDeclaration
class DepositDagger {

    @DepositScope
    @Subcomponent(modules = [DepositModule::class, LogoutModule::class])
    interface DepositComponent : BaseGraph {
        fun router(): CommandRouter

        @Subcomponent.Builder
        interface Builder : SubcomponentBuilder<DepositComponent> {
            override fun build(): DepositComponent
        }
    }

    @Module
    interface DepositModule {

        @Binds
        @IntoMap
        @StringKey("deposit")
        fun depositCommand(command: DepositCommand): Command
    }


    @Scope
    @Retention(AnnotationRetention.RUNTIME)
    internal annotation class DepositScope


    @Module
    @AppModuleDeclaration
    abstract class AppModule {

    }

    @AppGraphDeclaration
    interface AppGraph : BaseGraph {
        fun depositBuilder(): DepositComponent.Builder
    }
}

