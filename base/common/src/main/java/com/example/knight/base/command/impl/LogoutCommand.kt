package com.example.knight.base.command.impl

import com.example.knight.base.Outputter
import com.example.knight.base.account.AccountManager
import com.example.knight.base.command.Command
import com.example.knight.base.command.Result
import javax.inject.Inject

class LogoutCommand @Inject constructor(
    private val outputter: Outputter
) : Command {

    override fun handleInput(input: List<String>): Result {
        outputter.output("logged out ${AccountManager.getCurrentAccount()?.username}")
        AccountManager.clearAccount()
        return Result.inputCompleted()
    }
}