package com.example.knight.deposit.module

import com.example.knight.base.command.Command
import com.example.knight.base.account.Account
import com.example.knight.base.command.impl.LogoutCommand
import com.example.knight.deposit.command.DepositCommand
import dagger.Binds
import dagger.BindsOptionalOf
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
interface DepositModule {

    @Binds
    @IntoMap
    @StringKey("deposit")
    fun depositCommand(command: DepositCommand): Command
}