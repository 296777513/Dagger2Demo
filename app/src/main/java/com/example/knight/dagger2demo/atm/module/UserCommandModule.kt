package com.example.knight.dagger2demo.atm.module

import com.example.knight.dagger2demo.atm.command.Command
import com.example.knight.dagger2demo.atm.command.impl.DepositCommand
import com.example.knight.dagger2demo.atm.command.impl.LogoutCommand
import com.example.knight.dagger2demo.atm.command.impl.WithdrawCommand
import com.example.knight.dagger2demo.atm.command.impl.WithdrawalLimiter
import com.example.knight.dagger2demo.atm.data.Account
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import java.math.BigDecimal

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