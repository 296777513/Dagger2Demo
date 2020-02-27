package com.example.knight.dagger2demo.atm

import com.example.knight.dagger2demo.atm.command.Status
import com.example.knight.dagger2demo.atm.router.CommandRouter
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommandProcessor @Inject constructor(firstCommandRouter: CommandRouter) {

    private val commandRouterStack = ArrayDeque<CommandRouter>()

    init {
        commandRouterStack.push(firstCommandRouter)
    }

    fun process(input: String): Status {
        commandRouterStack.peek()?.route(input)?.let { result ->
            result.takeIf {
                it.status.equals(Status.INPUT_COMPLETED)
            }?.let {
                commandRouterStack.pop()
                return if (commandRouterStack.isEmpty()) Status.INPUT_COMPLETED else Status.HANDLED
            } ?: result.nestedCommandRouter.ifPresent(commandRouterStack::push)

            return result.status
        }
        return Status.INVALID
    }
}