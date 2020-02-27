package com.example.knight.dagger2demo.atm.command.impl

import com.example.knight.dagger2demo.atm.Outputter
import com.example.knight.dagger2demo.atm.command.Command
import com.example.knight.dagger2demo.atm.command.Result
import com.example.knight.dagger2demo.atm.data.Database
import com.example.knight.dagger2demo.atm.component.UserCommandsComponent
import com.example.knight.dagger2demo.atm.data.Account
import java.util.Optional
import javax.inject.Inject

class LoginCommand @Inject constructor(
    private val outputter: Outputter,
    private val database: Database,
    private val userCommandsRouterFactory: UserCommandsComponent.Factory,
    private val account: Optional<Account>
) : Command {
    override fun key() = "login"

    override fun handleInput(input: List<String>): Result {
        if (account.isPresent) {
            return Result.handled()
        }
        input.takeIf {
            it.size == 1
        }?.get(0)?.let { name ->
            val account = database.getAccount(name)
            outputter.output("$name is logged in with balance ${account.balance}")
            return Result.enterNestedCommandSet(userCommandsRouterFactory.create(account).router())
        } ?: return Result.invalid()
    }
}