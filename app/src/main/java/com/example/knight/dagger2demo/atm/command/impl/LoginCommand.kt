package com.example.knight.dagger2demo.atm.command.impl

import com.example.knight.dagger2demo.BaseApplication
import com.example.knight.dagger2demo.atm.Outputter
import com.example.knight.dagger2demo.atm.command.Command
import com.example.knight.dagger2demo.atm.command.Result
import com.example.knight.dagger2demo.atm.data.Account
import com.example.knight.dagger2demo.atm.data.Database
import com.example.knight.dagger2demo.atm.router.CommandRouter
import java.util.*
import javax.inject.Inject

class LoginCommand @Inject constructor(
    private val outputter: Outputter,
    private val database: Database,
    private val account: Optional<Account>
) : Command {
    override fun handleInput(input: List<String>): Result {
        if (account.isPresent) {
            return Result.handled()
        }
        input.takeIf {
            it.size == 1
        }?.get(0)?.let { name ->
            val account = database.getAccount(name)
            outputter.output("$name is logged in with balance ${account.balance}")
            return Result.enterNestedCommandSet(
                BaseApplication.appComponent?.userCommandComponent()?.create(account)?.router()
            )
        } ?: return Result.invalid()
    }
}