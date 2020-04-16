package com.example.knight.deposit.command

import com.example.knight.base.Outputter
import com.example.knight.base.account.AccountManager
import com.example.knight.base.command.impl.BigDecimalCommand
import java.math.BigDecimal
import javax.inject.Inject

class DepositCommand @Inject constructor(
    private val outputter: Outputter
) : BigDecimalCommand(outputter) {
    override fun handleAmount(amount: BigDecimal) {
        AccountManager.getCurrentAccount()?.also {
            it.balance += amount
            outputter.output("${it.username} now has ${it.balance}")
        } ?: outputter.output("You are not login")
    }
}