package com.example.knight.dagger2demo.atm.component

import com.example.knight.dagger2demo.atm.data.Account
import com.example.knight.dagger2demo.atm.module.UserCommandModule
import com.example.knight.dagger2demo.atm.router.CommandRouter
import dagger.BindsInstance
import dagger.Module
import dagger.Subcomponent
import javax.inject.Scope

@PerSession
@Subcomponent(modules = [UserCommandModule::class])
interface UserCommandsComponent {
    fun router(): CommandRouter

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance account: Account): UserCommandsComponent
    }

    @Module(subcomponents = [UserCommandsComponent::class])
    interface InstallationModule
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class PerSession
