package com.example.knight.deposit

import com.example.knight.base.command.Command
import com.example.knight.base.command.CommandRouter
import com.example.knight.base.dagger.BaseGraph
import com.example.knight.dagger.AppGraphDeclaration
import com.example.knight.dagger.AppModuleDeclaration
import com.example.knight.dagger.DaggerDeclaration
import com.example.knight.deposit.command.DepositCommand
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import javax.inject.Scope

@DaggerDeclaration
class DepositDagger {

    @Module
    interface DepositModule {

        @Binds
        @IntoMap
        @StringKey("deposit")
        fun depositCommand(command: DepositCommand): Command

        fun router(): CommandRouter
    }


    @Scope
    @Retention(AnnotationRetention.RUNTIME)
    internal annotation class DepositScope


    @Module
    @AppModuleDeclaration
    interface AppModule {
        @DepositScope
        @ContributesAndroidInjector(modules = [DepositModule::class])
        fun depositActivity(): DepositActivity
    }

    @AppGraphDeclaration
    interface AppGraph : BaseGraph
}

