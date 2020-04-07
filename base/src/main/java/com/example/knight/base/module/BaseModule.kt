package com.example.knight.base.module

import com.example.knight.base.account.AccountManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class BaseModule {

    @Provides
    @Singleton
    fun provideAccountManager(): AccountManager {
        return AccountManager()
    }
}