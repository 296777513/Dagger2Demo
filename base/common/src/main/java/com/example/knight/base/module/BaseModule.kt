package com.example.knight.base.module

import android.content.Context
import android.widget.Toast
import com.example.knight.base.Outputter
import com.example.knight.base.application.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
abstract class BaseModule {
    companion object {
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

        @Provides
        @Singleton
        fun providerContext(): Context {
            return BaseApplication.instance
        }

    }

}