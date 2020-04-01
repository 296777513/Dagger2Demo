package com.example.knight.dagger2demo.atm.component

import android.content.Context
import android.widget.Toast
import com.example.knight.dagger2demo.atm.CommandProcessor
import com.example.knight.dagger2demo.atm.Outputter
import com.example.knight.dagger2demo.atm.data.Database
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, DataBaseModule::class])
interface AppComponent {
    fun commandProcessor(): CommandProcessor
    fun InitCommandComponent(): InitCommandComponent.Builder
    fun userCommandComponent(): UserCommandsComponent.Factory
}


@Module(subcomponents = [InitCommandComponent::class, UserCommandsComponent::class])
class AppModule(private val mContext: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return mContext
    }
}

@Module
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context) = Database(context)

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