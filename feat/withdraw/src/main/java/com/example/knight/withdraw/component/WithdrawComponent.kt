package com.example.knight.withdraw.component

import com.example.knight.base.command.CommandRouter
import com.example.knight.base.account.Account
import com.example.knight.base.module.AmountsModule
import com.example.knight.base.module.LogoutModule
import com.example.knight.withdraw.module.WithdrawModule
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Scope

@DepositScope
@Subcomponent(modules = [WithdrawModule::class, AmountsModule::class, LogoutModule::class])
interface WithdrawComponent {
    fun router(): CommandRouter

    @Subcomponent.Factory
    interface Factory {
        fun create(): WithdrawComponent
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class DepositScope
