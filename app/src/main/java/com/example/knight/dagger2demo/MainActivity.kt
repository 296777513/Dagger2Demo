package com.example.knight.dagger2demo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.knight.base.BaseActivity
import com.example.knight.base.account.AccountManager
import com.example.knight.deposit.DepositActivity
import com.example.knight.login.LoginActivity
import com.example.knight.withdraw.WithdrawActivity

class MainActivity : BaseActivity(), View.OnClickListener {
    companion object {
        val LOGIN_REQUEST = 10001
        val WITHDRAW_REQUEST = 10002
        val DEPOSIT_REQUEST = 10003
    }

    lateinit var loginButton: Button
    lateinit var withdrawButton: Button
    lateinit var depositButton: Button
    lateinit var description: TextView
    lateinit var logoutButton: Button

    private fun initView() {
        loginButton = findViewById(R.id.login)
        description = findViewById(R.id.description)
        withdrawButton = findViewById(R.id.withdraw)
        depositButton = findViewById(R.id.deposit)
        logoutButton = findViewById(R.id.logout)
    }

    private fun setClickEvent() {
        loginButton.setOnClickListener(this)
        depositButton.setOnClickListener(this)
        withdrawButton.setOnClickListener(this)
        logoutButton.setOnClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        setClickEvent();
    }

    override fun onClick(v: View?) {
        when (v) {
            loginButton -> {
                startActivityForResult(Intent(this, LoginActivity::class.java), LOGIN_REQUEST)
            }
            depositButton -> {
                startActivityForResult(
                    Intent(this, DepositActivity::class.java),
                    DEPOSIT_REQUEST
                )
            }
            withdrawButton -> {
                startActivityForResult(
                    Intent(this, WithdrawActivity::class.java),
                    WITHDRAW_REQUEST
                )
            }
            logoutButton -> {
                loginButton.visibility = View.VISIBLE
                withdrawButton.visibility = View.GONE
                depositButton.visibility = View.GONE
                logoutButton.visibility = View.GONE
                description.text = "Welcome to Dagger Bank, and chose your server"
                AccountManager.clearAccount()
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {

            if (AccountManager.getCurrentAccount() != null) {
                loginButton.visibility = View.GONE
                withdrawButton.visibility = View.VISIBLE
                depositButton.visibility = View.VISIBLE
                logoutButton.visibility = View.VISIBLE
                description.text =
                    "${AccountManager.getCurrentAccount()?.username}'s current balance is ${AccountManager.getCurrentAccount()?.balance}"
            } else {
                loginButton.visibility = View.VISIBLE
                withdrawButton.visibility = View.GONE
                depositButton.visibility = View.GONE
                logoutButton.visibility = View.GONE
                description.text = "Welcome to Dagger Bank, and chose your server"
            }
        }
    }
}
