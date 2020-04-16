package com.example.knight.dagger2

import me.eugeniomarletti.kotlin.metadata.shadow.utils.addToStdlib.safeAs
import javax.annotation.processing.Filer
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.util.Elements
import javax.lang.model.util.Types

interface CrossGuardProcessor {

    fun handle(chain: Chain): Boolean


    interface Chain {
        fun process(): Boolean
        fun request(): ProcessorData
    }

    data class ProcessorData(
        val roundEnv: RoundEnvironment,
        val elementUtils: Elements,
        val typeUtils: Types,
        val filer: Filer,
        var model: CrossGuardModel? = null
    )
}

inline fun <reified T : CrossGuardModel> CrossGuardModel?.safeCast(): T? = this as? T
interface CrossGuardModel

