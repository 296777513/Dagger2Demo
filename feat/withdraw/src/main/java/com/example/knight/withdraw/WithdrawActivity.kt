package com.example.knight.withdraw

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.knight.base.application.BaseApplication
import com.example.knight.base.command.CommandRouter
import com.example.knight.base.process.CommandProcessor
import com.example.knight.dagger.Dagger2ComponentFactory
import javax.inject.Inject

class WithdrawActivity : AppCompatActivity() {


    @Inject
    lateinit var commandProcessor: CommandProcessor

    @Inject
    lateinit var router: CommandRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        BaseApplication.component<WithdrawDagger.AppGraph>().withdrawBuilder().build()
            .injectWithdrawActivity(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_withdraw)
        val click = findViewById<Button>(R.id.withdraw)
        val input = findViewById<EditText>(R.id.withdraw_input)
        commandProcessor.commandRouterStack.apply {
            clear()
            push(router)
        }
        click.setOnClickListener {
            commandProcessor.process("withdraw " + input.text.toString())
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