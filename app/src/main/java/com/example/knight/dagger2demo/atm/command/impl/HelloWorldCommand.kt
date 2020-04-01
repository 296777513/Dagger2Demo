package com.example.knight.dagger2demo.atm.command.impl

import com.example.knight.dagger2demo.atm.Outputter
import com.example.knight.dagger2demo.atm.command.Command
import com.example.knight.dagger2demo.atm.command.Result
import javax.inject.Inject

class HelloWorldCommand @Inject constructor(private val outputter: Outputter) :
    Command {

    override fun handleInput(input: List<String>): Result {
        outputter.output("world!")
        return Result.handled()
    }
}