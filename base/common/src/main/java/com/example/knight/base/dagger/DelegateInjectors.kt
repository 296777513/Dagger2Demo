package com.example.knight.base.dagger

import com.example.knight.base.application.BaseApplication
import com.example.knight.dagger.Graph


inline fun <T : Any?, reified G : Graph> inject(crossinline m: G.() -> T): Lazy<T> =
    lazy { m.invoke(BaseApplication.component()) }