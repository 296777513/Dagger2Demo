package com.example.knight.dagger2demo.atm.command.impl

import com.example.knight.dagger2demo.atm.Outputter
import com.example.knight.dagger2demo.atm.command.Command
import com.example.knight.dagger2demo.atm.command.Result
import com.example.knight.dagger2demo.atm.data.Account
import javax.inject.Inject

class LogoutCommand @Inject constructor(
    private val outputter: Outputter,
    private val account: Account
) : Command {
    override fun handleInput(input: List<String>): Result {
        input.takeIf {
            it.isEmpty()
        }?.let {
            outputter.output("logged out ${account.username}")
            return Result.inputCompleted()
        }
        return Result.invalid()
    }
}