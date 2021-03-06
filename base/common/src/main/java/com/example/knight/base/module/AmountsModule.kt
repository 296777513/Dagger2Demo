package com.example.knight.base.module

import dagger.Module
import dagger.Provides
import java.math.BigDecimal
import javax.inject.Qualifier

@Module
interface AmountsModule {
    companion object {
        @Provides
        @MinimumBalance
        fun minimumBalance() = BigDecimal.ZERO

        @Provides
        @MaximumWithdrawal
        fun maximumWithdrawal() = BigDecimal(1000)

    }
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MinimumBalance

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MaximumWithdrawal