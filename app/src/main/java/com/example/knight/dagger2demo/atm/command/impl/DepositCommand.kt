package com.example.knight.dagger2demo.atm.command.impl

import com.example.knight.dagger2demo.atm.Outputter
import com.example.knight.dagger2demo.atm.command.BigDecimalCommand
import com.example.knight.dagger2demo.atm.data.Account
import java.math.BigDecimal
import javax.inject.Inject

class DepositCommand @Inject constructor(
    private val outputter: Outputter,
    private val account: Account,
    private val withdrawalLimiter: WithdrawalLimiter
) : BigDecimalCommand(outputter) {

    override fun handleAmount(amount: BigDecimal) {
        account.balance += amount
        withdrawalLimiter.recordDeposit(amount)
        outputter.output("${account.username} now has ${account.balance}")
    }
}