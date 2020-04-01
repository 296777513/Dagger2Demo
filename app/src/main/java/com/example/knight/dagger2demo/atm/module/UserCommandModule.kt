package com.example.knight.dagger2demo.atm.module

import com.example.knight.dagger2demo.atm.command.Command
import com.example.knight.dagger2demo.atm.command.impl.DepositCommand
import com.example.knight.dagger2demo.atm.command.impl.LogoutCommand
import com.example.knight.dagger2demo.atm.command.impl.WithdrawCommand
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
abstract class UserCommandModule {

    @Binds
    @IntoMap
    @StringKey("deposit")
    abstract fun depositCommand(command: DepositCommand): Command

    @Binds
    @IntoMap
    @StringKey("withdraw")
    abstract fun withdrawCommand(command: WithdrawCommand): Command

    @Binds
    @IntoMap
    @StringKey("logout")
    abstract fun logoutCommand(command: LogoutCommand): Command

}