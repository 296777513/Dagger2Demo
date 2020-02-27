package com.example.knight.dagger2demo.atm.module

import com.example.knight.dagger2demo.atm.command.Command
import com.example.knight.dagger2demo.atm.command.impl.LoginCommand
import com.example.knight.dagger2demo.atm.data.Account
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