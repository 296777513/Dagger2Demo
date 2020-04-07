package com.example.knight.deposit

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.knight.base.BaseActivity
import com.example.knight.base.application.BaseApplication
import com.example.knight.base.dagger.BaseGraph

class DepositActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deposit)
        val click = findViewById<Button>(R.id.deposit)
        val input = findViewById<EditText>(R.id.deposit_input)
        val commandProcessor =
            BaseApplication.instance.component<BaseGraph>().commandProcessor()
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