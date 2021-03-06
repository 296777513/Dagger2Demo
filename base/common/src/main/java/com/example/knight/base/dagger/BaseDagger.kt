package com.example.knight.base.dagger

import android.content.Context
import android.widget.Toast
import com.example.knight.base.Outputter
import com.example.knight.base.application.BaseApplication
import com.example.knight.base.command.CommandRouter
import com.example.knight.dagger.AppGraphDeclaration
import com.example.knight.dagger.AppModuleDeclaration
import com.example.knight.dagger.DaggerDeclaration
import com.example.knight.dagger.SubcomponentBuilder
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@DaggerDeclaration
interface BaseDagger {

    @Singleton
    @Subcomponent(modules = [LoginModule::class])
    interface BaseComponent : BaseGraph {
        fun router(): CommandRouter

        @Subcomponent.Builder
        interface Builder : SubcomponentBuilder<BaseComponent> {
            override fun build(): BaseComponent
        }
    }

    @Module
    abstract class LoginModule {
    }


    @Module
    @AppModuleDeclaration
    abstract class AppModule {
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

    @AppGraphDeclaration
    interface AppGraph : BaseGraph {
    }
}