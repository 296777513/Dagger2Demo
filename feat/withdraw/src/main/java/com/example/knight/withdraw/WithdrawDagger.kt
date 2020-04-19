package com.example.knight.withdraw

import com.example.knight.base.command.Command
import com.example.knight.base.command.CommandRouter
import com.example.knight.base.dagger.BaseGraph
import com.example.knight.base.module.AmountsModule
import com.example.knight.base.module.LogoutModule
import com.example.knight.dagger.AppGraphDeclaration
import com.example.knight.dagger.AppModuleDeclaration
import com.example.knight.dagger.DaggerDeclaration
import com.example.knight.dagger.SubcomponentBuilder
import com.example.knight.withdraw.command.WithdrawCommand
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import javax.inject.Scope

@DaggerDeclaration
class WithdrawDagger {
    @WithdrawScope
    @Subcomponent(modules = [WithdrawModule::class, AmountsModule::class, LogoutModule::class])
    interface WithdrawComponent : BaseGraph {
        fun router(): CommandRouter

        @Subcomponent.Builder
        interface Builder : SubcomponentBuilder<WithdrawComponent> {
            override fun build(): WithdrawComponent
        }
    }

    @Module
    interface WithdrawModule {

        @Binds
        @IntoMap
        @StringKey("withdraw")
        fun withDrawCommand(command: WithdrawCommand): Command
    }

    @Scope
    @Retention(AnnotationRetention.RUNTIME)
    internal annotation class WithdrawScope


    @Module
    @AppModuleDeclaration
    abstract class AppModule {

    }

    @AppGraphDeclaration
    interface AppGraph : BaseGraph {
        fun withdrawBuilder(): WithdrawComponent.Builder
    }
}

