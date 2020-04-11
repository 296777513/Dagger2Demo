package com.example.knight.dagger2demo.atm.component

import android.content.Context
import android.widget.Toast
import com.example.knight.base.Outputter
import com.example.knight.base.dagger.BaseGraph
import com.example.knight.base.module.BaseModule
import com.example.knight.base.process.CommandProcessor
import com.example.knight.deposit.component.DepositComponent
import com.example.knight.login.component.LoginComponent
import com.example.knight.withdraw.component.WithdrawComponent
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : BaseGraph {
    fun LoginComponent(): LoginComponent.Builder
    fun depositComponent(): DepositComponent.Factory
    fun withdrawComponent(): WithdrawComponent.Factory
}


@Module(subcomponents = [LoginComponent::class, DepositComponent::class, WithdrawComponent::class])
class AppModule(private val mContext: Context) : BaseModule() {
    @Provides
    @Singleton
    fun provideContext(): Context {
        return mContext
    }
}