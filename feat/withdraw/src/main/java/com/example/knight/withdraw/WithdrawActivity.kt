package com.example.knight.withdraw

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.knight.base.BaseActivity
import com.example.knight.base.application.BaseApplication
import com.example.knight.base.dagger.BaseGraph

class WithdrawActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_withdraw)
        val click = findViewById<Button>(R.id.withdraw)
        val input = findViewById<EditText>(R.id.withdraw_input)
        val commandProcessor =
            BaseApplication.instance.component<BaseGraph>().commandProcessor()
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