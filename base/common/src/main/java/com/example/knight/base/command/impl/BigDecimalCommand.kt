package com.example.knight.base.command.impl

import com.example.knight.base.Outputter
import com.example.knight.base.command.Command
import com.example.knight.base.command.Result
import java.math.BigDecimal

abstract class BigDecimalCommand(
    private val outputter: Outputter
) : Command {

    companion object {
        fun tryParse(arg: String): BigDecimal {
            return try {
                BigDecimal(arg)
            } catch (e: NumberFormatException) {
                BigDecimal.ZERO
            }

        }
    }

    override fun handleInput(input: List<String>): Result {
        val arg = input[0]
        val amount = tryParse(arg)
        when {
            amount == BigDecimal.ZERO -> outputter.output("$arg is not a valid number")
            amount.signum() <= 0 -> outputter.output("amount must be positive")
            else -> handleAmount(amount)
        }
        return Result.handled()
    }

    protected abstract fun handleAmount(amount: BigDecimal)

}