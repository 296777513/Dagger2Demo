package com.example.knight.base.command

import java.util.*

/** Logic to process some user input.  */
interface Command {
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
    val nestedCommandRouter: Optional<CommandRouter>,
    val name: String = ""
) {

    companion object {
        fun invalid() = Result(
            Status.INVALID,
            Optional.empty()
        )

        fun handled(name: String = "") = Result(
            Status.HANDLED,
            Optional.empty(),
            name
        )

        fun inputCompleted(name: String = "") = Result(
            Status.INPUT_COMPLETED,
            Optional.empty(),
            name
        )

        fun enterNestedCommandSet(nestedCommandRouter: CommandRouter) =
            Result(
                Status.HANDLED,
                Optional.of(nestedCommandRouter)
            )
    }
}