package com.example.knight.login

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.knight.base.BaseActivity
import com.example.knight.base.application.BaseApplication
import com.example.knight.base.dagger.BaseGraph

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val click = findViewById<Button>(R.id.login)
        val input = findViewById<EditText>(R.id.user_name_input)
        click.setOnClickListener {
            val commandProcessor =
                BaseApplication.instance.component<BaseGraph>().commandProcessor()
            commandProcessor.process("login " + input.text.toString())
            input.setText("")
            setResult(Activity.RESULT_OK)
            finish()
        }
    }


}