package com.example.knight.dagger2demo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.knight.dagger2demo.atm.component.DaggerCommandProcessorFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val click = findViewById<Button>(R.id.atm_click)
        val input = findViewById<EditText>(R.id.atm_input)
        val process = DaggerCommandProcessorFactory.create().processor()
        click.setOnClickListener {
            process.process(input.text.toString())
            input.setText("")
        }
    }
}
