package com.example.knight.withdraw.command

import com.example.knight.base.Outputter
import com.example.knight.base.account.AccountManager
import com.example.knight.base.command.impl.BigDecimalCommand
import com.example.knight.base.module.MinimumBalance
import java.math.BigDecimal
import javax.inject.Inject

class WithdrawCommand @Inject constructor(
    private val outputter: Outputter,
    @MinimumBalance val minimumBalance: BigDecimal
) : BigDecimalCommand(outputter) {
    override fun handleAmount(amount: BigDecimal) {
        AccountManager.getCurrentAccount()?.also {
            if (amount > it.balance) {
                outputter.output("withdraw don't bigger than ${it.balance} in a single transaction")
                return
            }

            val newBalance = it.balance.subtract(amount)
            if (newBalance < minimumBalance) {
                outputter.output("your balance is not enough, if you want to withdraw $amount")
            } else {
                it.balance -= amount
                outputter.output("your new balance is ${it.balance}")
            }
        } ?: outputter.output("You are not login")
    }
}