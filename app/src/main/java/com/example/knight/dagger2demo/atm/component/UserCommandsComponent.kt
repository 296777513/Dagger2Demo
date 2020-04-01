package com.example.knight.dagger2demo.atm.component

import com.example.knight.dagger2demo.atm.data.Account
import com.example.knight.dagger2demo.atm.module.AmountsModule
import com.example.knight.dagger2demo.atm.module.UserCommandModule
import com.example.knight.dagger2demo.atm.router.CommandRouter
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Scope

@UserCommandScope
@Subcomponent(modules = [UserCommandModule::class, AmountsModule::class])
interface UserCommandsComponent {
    fun router(): CommandRouter

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance account: Account): UserCommandsComponent
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class UserCommandScope
