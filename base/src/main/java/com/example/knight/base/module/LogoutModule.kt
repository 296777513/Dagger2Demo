package com.example.knight.base.module

import com.example.knight.base.command.Command
import com.example.knight.base.command.impl.LogoutCommand
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey


@Module
interface LogoutModule {
    @Binds
    @IntoMap
    @StringKey("logout")
    fun depositCommand(command: LogoutCommand): Command
}