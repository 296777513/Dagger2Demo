package com.example.knight.withdraw.command

import com.example.knight.base.Outputter
import com.example.knight.base.command.impl.BigDecimalCommand
import com.example.knight.base.account.Account
import com.example.knight.base.module.MinimumBalance
import java.math.BigDecimal
import javax.inject.Inject

class WithdrawCommand @Inject constructor(
    private val outputter: Outputter,
    @MinimumBalance val minimumBalance: BigDecimal
) : BigDecimalCommand(outputter) {
    private val account: Account? =
        com.example.knight.base.application.BaseApplication.instance.component<com.example.knight.base.dagger.BaseGraph>()
            .accountManager().getCurrentAccount()

    override fun handleAmount(amount: BigDecimal) {
        account?.also {
            if (amount > account.balance) {
                outputter.output("withdraw don't bigger than ${account.balance} in a single transaction")
                return
            }

            val newBalance = account.balance.subtract(amount)
            if (newBalance < minimumBalance) {
                outputter.output("your balance is not enough, if you want to withdraw $amount")
            } else {
                account.balance -= amount
                outputter.output("your new balance is ${account.balance}")
            }
        } ?: outputter.output("You are not login")
    }
}