package com.example.knight.dagger2demo.atm.command.impl

import com.example.knight.dagger2demo.atm.Outputter
import com.example.knight.dagger2demo.atm.command.BigDecimalCommand
import com.example.knight.dagger2demo.atm.data.Account
import com.example.knight.dagger2demo.atm.module.MinimumBalance
import java.math.BigDecimal
import javax.inject.Inject

class WithdrawCommand @Inject constructor(
    private val outputter: Outputter,
    private val account: Account,
    @MinimumBalance val minimumBalance: BigDecimal,
    private val withdrawalLimiter: WithdrawalLimiter
) : BigDecimalCommand(outputter) {

    override fun key() = "withdraw"

    override fun handleAmount(amount: BigDecimal) {
        if (amount > withdrawalLimiter.remainingWithdrawalLimit) {
            outputter.output("withdraw don't bigger than ${withdrawalLimiter.remainingWithdrawalLimit} in a single transaction")
            return
        }

        val newBalance = account.balance.subtract(amount)
        if (newBalance < minimumBalance) {
            outputter.output("your balance is not enough, if you want to withdraw $amount")
        } else {
            account.balance -= amount
            withdrawalLimiter.recordWithdrawal(amount)
            outputter.output("your new balance is ${account.balance}")
        }
    }
}