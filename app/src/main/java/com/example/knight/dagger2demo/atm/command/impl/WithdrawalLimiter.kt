package com.example.knight.dagger2demo.atm.command.impl

import com.example.knight.dagger2demo.atm.component.UserCommandScope
import com.example.knight.dagger2demo.atm.module.MaximumWithdrawal
import java.math.BigDecimal
import javax.inject.Inject

@UserCommandScope
class WithdrawalLimiter @Inject constructor(
    @MaximumWithdrawal val maximumWithdrawal: BigDecimal
) {

    var remainingWithdrawalLimit: BigDecimal = maximumWithdrawal

    fun recordDeposit(amount: BigDecimal) {
        remainingWithdrawalLimit = remainingWithdrawalLimit.add(amount)
    }

    fun recordWithdrawal(amount: BigDecimal) {
        remainingWithdrawalLimit = remainingWithdrawalLimit.subtract(amount)
    }

}