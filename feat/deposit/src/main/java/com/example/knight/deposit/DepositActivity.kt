package com.example.knight.deposit

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.knight.base.BaseActivity
import com.example.knight.base.command.CommandRouter
import com.example.knight.base.process.CommandProcessor
import javax.inject.Inject

class DepositActivity : BaseActivity() {
    @Inject
    lateinit var commandProcessor: CommandProcessor

    @Inject
    lateinit var router: CommandRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deposit)
        val click = findViewById<Button>(R.id.deposit)
        val input = findViewById<EditText>(R.id.deposit_input)
        commandProcessor.commandRouterStack.apply {
            clear()
            push(router)
        }
        click.setOnClickListener {
            commandProcessor.process("deposit " + input.text.toString())
            input.setText("")
            setResult(Activity.RESULT_OK)
            finish()
        }

        findViewById<Button>(R.id.logout).setOnClickListener {
            commandProcessor.process("logout ")
            setResult(Activity.RESULT_OK)
            finish()
        }
    }


}