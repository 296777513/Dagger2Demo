package com.example.knight.dagger2demo.atm.component

import com.example.knight.dagger2demo.atm.CommandProcessor
import com.example.knight.dagger2demo.atm.module.AmountsModule
import com.example.knight.dagger2demo.atm.module.HelloWorldModule
import com.example.knight.dagger2demo.atm.module.LoginModule
import com.example.knight.dagger2demo.atm.module.ToastOutModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [HelloWorldModule::class, LoginModule::class, ToastOutModule::class, UserCommandsComponent.InstallationModule::class, AmountsModule::class])
interface CommandProcessorFactory {
    fun processor(): CommandProcessor
}