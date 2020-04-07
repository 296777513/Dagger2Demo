package com.example.knight.withdraw.module

import com.example.knight.base.command.Command
import com.example.knight.base.account.Account
import com.example.knight.base.command.impl.LogoutCommand
import com.example.knight.withdraw.command.WithdrawCommand
import dagger.Binds
import dagger.BindsOptionalOf
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
interface WithdrawModule {

    @Binds
    @IntoMap
    @StringKey("withdraw")
    fun withDrawCommand(command: WithdrawCommand): Command
}