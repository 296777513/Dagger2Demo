package com.example.knight.dagger2demo.atm.command

import com.example.knight.dagger2demo.atm.router.CommandRouter
import java.util.*

/** Logic to process some user input.  */
interface Command {
    /**
     * String token that signifies this command should be selected (e.g.:
     * "deposit", "withdraw")
     */
    fun key(): String

    /** Process the rest of the command's words and do something.  */
    fun handleInput(input: List<String>): Result
}

enum class Status {
    INVALID,
    HANDLED,
    INPUT_COMPLETED
}

class Result(
    val status: Status,
    val nestedCommandRouter: Optional<CommandRouter>
) {

    companion object {
        fun invalid() = Result(
            Status.INVALID,
            Optional.empty()
        )

        fun handled() = Result(
            Status.HANDLED,
            Optional.empty()
        )

        fun inputCompleted() = Result(
            Status.INPUT_COMPLETED,
            Optional.empty()
        )

        fun enterNestedCommandSet(nestedCommandRouter: CommandRouter) =
            Result(
                Status.HANDLED,
                Optional.of(nestedCommandRouter)
            )
    }
}