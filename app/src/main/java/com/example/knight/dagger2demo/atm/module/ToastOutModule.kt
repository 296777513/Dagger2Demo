package com.example.knight.dagger2demo.atm.module

import android.widget.Toast
import com.example.knight.dagger2demo.BaseApplication
import com.example.knight.dagger2demo.atm.Outputter
import dagger.Module
import dagger.Provides

@Module
abstract class ToastOutModule {
    companion object {
        @Provides
        fun testOutputter(): Outputter {
            return object : Outputter {
                override fun output(output: String) {
                    Toast.makeText(
                        BaseApplication.mContext,
                        output,
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
        }
    }
}