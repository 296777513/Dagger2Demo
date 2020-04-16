package com.example.knight.base.process

import com.example.knight.base.command.CommandRouter
import com.example.knight.base.command.Status
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommandProcessor @Inject constructor() {

    val commandRouterStack = ArrayDeque<CommandRouter>()

    /**
     * enterNestedCommandSet(
    BaseApplication.appComponent?.userCommandComponent()?.create(account)?.router()
    )
     */
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