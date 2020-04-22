package com.example.knight.login

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.knight.base.BaseActivity
import com.example.knight.base.Outputter
import com.example.knight.base.application.BaseApplication
import com.example.knight.base.command.CommandRouter
import com.example.knight.base.dagger.BaseGraph
import com.example.knight.base.process.CommandProcessor
import com.example.knight.dagger.Dagger2ComponentFactory
import javax.inject.Inject

class LoginActivity : BaseActivity() {

    @Inject
    lateinit var outputter: Outputter

    @Inject
    lateinit var hints: String

    @Inject
    lateinit var commandProcessor: CommandProcessor

    @Inject
    lateinit var router: CommandRouter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        outputter.output(hints)
        commandProcessor.commandRouterStack.apply {
            clear()
            push(router)
        }
        val click = findViewById<Button>(R.id.login)
        val input = findViewById<EditText>(R.id.user_name_input)
        click.setOnClickListener {
            commandProcessor.process("login " + input.text.toString())
            input.setText("")
            setResult(Activity.RESULT_OK)
            finish()
        }
    }


}