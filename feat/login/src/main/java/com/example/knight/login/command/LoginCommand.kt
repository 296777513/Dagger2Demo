package com.example.knight.login.command

import com.example.knight.base.Outputter
import com.example.knight.base.account.AccountManager
import com.example.knight.base.command.Command
import com.example.knight.base.command.Result
import javax.inject.Inject

class LoginCommand @Inject constructor(
    private val outputter: Outputter
) : Command {

    override fun handleInput(input: List<String>): Result {
        if (AccountManager.getCurrentAccount() != null) {
            return Result.handled()
        }
        input.takeIf {
            it.size == 1
        }?.get(0)?.let { name ->
            val account = AccountManager.createAccount(name)
            outputter.output("$name is logged in with balance ${account.balance}")
            return Result.inputCompleted(name)
        } ?: return Result.invalid()
    }
}