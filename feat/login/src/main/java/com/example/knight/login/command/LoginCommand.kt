package com.example.knight.login.command

import com.example.knight.base.Outputter
import com.example.knight.base.command.Command
import com.example.knight.base.command.Result
import com.example.knight.base.account.Account
import com.example.knight.base.account.AccountManager
import com.example.knight.base.application.BaseApplication
import javax.inject.Inject

class LoginCommand @Inject constructor(
    private val outputter: Outputter,
    private val database: AccountManager
) : Command {
    private val account: Account? =
        BaseApplication.instance.component<com.example.knight.base.dagger.BaseGraph>()
            .accountManager().getCurrentAccount()

    override fun handleInput(input: List<String>): Result {
        if (account != null) {
            return Result.handled()
        }
        input.takeIf {
            it.size == 1
        }?.get(0)?.let { name ->
            val account = database.createAccount(name)
            outputter.output("$name is logged in with balance ${account.balance}")
            return Result.inputCompleted(name)
        } ?: return Result.invalid()
    }
}