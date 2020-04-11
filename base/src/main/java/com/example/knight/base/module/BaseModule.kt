package com.example.knight.base.module

import android.content.Context
import android.widget.Toast
import com.example.knight.base.Outputter
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

    @Provides
    @Singleton
    fun toastOutputter(context: Context): Outputter {
        return object : Outputter {
            override fun output(output: String) {
                Toast.makeText(
                    context,
                    output,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}