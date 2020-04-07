package com.example.knight.login.module

import com.example.knight.base.command.Command
import com.example.knight.base.account.Account
import com.example.knight.login.command.LoginCommand
import dagger.Binds
import dagger.BindsOptionalOf
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
interface LoginModule {

    @Binds
    @IntoMap
    @StringKey("login")
    fun loginCommand(command: LoginCommand): Command

    @BindsOptionalOf
    fun optionalAccount(): Account
}