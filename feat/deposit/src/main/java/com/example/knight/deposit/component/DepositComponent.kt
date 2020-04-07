package com.example.knight.deposit.component

import com.example.knight.base.command.CommandRouter
import com.example.knight.base.account.Account
import com.example.knight.base.module.LogoutModule
import com.example.knight.deposit.module.DepositModule
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Scope

@DepositScope
@Subcomponent(modules = [DepositModule::class, LogoutModule::class])
interface DepositComponent {
    fun router(): CommandRouter

    @Subcomponent.Factory
    interface Factory {
        fun create(): DepositComponent
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class DepositScope
