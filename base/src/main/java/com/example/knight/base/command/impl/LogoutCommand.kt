package com.example.knight.base.command.impl

import com.example.knight.base.Outputter
import com.example.knight.base.command.Command
import com.example.knight.base.command.Result
import com.example.knight.base.account.AccountManager
import com.example.knight.base.application.BaseApplication
import javax.inject.Inject

class LogoutCommand @Inject constructor(
    private val outputter: Outputter
) : Command {
    private val accountManager: AccountManager =
        BaseApplication.instance.component<com.example.knight.base.dagger.BaseGraph>()
            .accountManager()

    override fun handleInput(input: List<String>): Result {
        outputter.output("logged out ${accountManager.getCurrentAccount()?.username}")
        accountManager.clearAccount()
        return Result.inputCompleted()
    }
}