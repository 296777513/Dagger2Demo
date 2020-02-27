package com.example.knight.dagger2demo.atm.module

import com.example.knight.dagger2demo.atm.command.Command
import com.example.knight.dagger2demo.atm.command.impl.HelloWorldCommand
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
abstract class HelloWorldModule {
    @Binds
    @IntoMap
    @StringKey("Hello")
    abstract fun helloWorldCommand(command: HelloWorldCommand): Command
}