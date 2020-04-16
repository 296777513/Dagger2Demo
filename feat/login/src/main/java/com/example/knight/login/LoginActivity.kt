package com.example.knight.login

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.knight.base.BaseActivity
import com.example.knight.base.application.BaseApplication
import com.example.knight.base.dagger.BaseGraph
import com.example.knight.dagger.Dagger2ComponentFactory

class LoginActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val commandProcessor =
            BaseApplication.instance.component<BaseGraph>().commandProcessor()
        commandProcessor.commandRouterStack.apply {
            clear()
            push(
                Dagger2ComponentFactory.create(
                    LoginDagger.AppGraph::class.java,
                    LoginDagger.AppGraph::loginBuilder
                ).router()
            )
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