package com.example.knight.base.command

import android.content.Context
import android.widget.Toast
import javax.inject.Inject

class CommandRouter @Inject constructor(
    private val commands: Map<String, @JvmSuppressWildcards Command>,
    private val context: Context
) {

    fun route(input: String): Result {
        val splitInput = input.split(" ")
        if (splitInput.isEmpty()) {
            return invalidCommand(input)
        }

        val commandKey = splitInput.get(0)
        val command = commands.get(commandKey) ?: return invalidCommand(input)

        val status = command.handleInput(splitInput.subList(1, splitInput.size))
        if (status.status === Status.INVALID) {
            Toast.makeText(
                context,
                "$commandKey: invalid arguments",
                Toast.LENGTH_LONG
            ).show()
        }
        return status
    }

    fun invalidCommand(input: String): Result {
        Toast.makeText(
            context,
            String.format("couldn't understand \"%s\". please try again.", input),
            Toast.LENGTH_LONG
        ).show()
        return Result.invalid()
    }

}