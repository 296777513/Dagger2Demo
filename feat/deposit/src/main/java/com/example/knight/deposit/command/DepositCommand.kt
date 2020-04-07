package com.example.knight.deposit.command

import com.example.knight.base.Outputter
import com.example.knight.base.command.impl.BigDecimalCommand
import com.example.knight.base.account.Account
import com.example.knight.base.application.BaseApplication
import com.example.knight.base.dagger.BaseGraph
import java.math.BigDecimal
import javax.inject.Inject

class DepositCommand @Inject constructor(
    private val outputter: Outputter
) : BigDecimalCommand(outputter) {
    private val account: Account? =
        BaseApplication.instance.component<BaseGraph>().accountManager().getCurrentAccount()

    override fun handleAmount(amount: BigDecimal) {
        account?.also {
            it.balance += amount
            outputter.output("${it.username} now has ${it.balance}")
        } ?: outputter.output("You are not login")
    }
}