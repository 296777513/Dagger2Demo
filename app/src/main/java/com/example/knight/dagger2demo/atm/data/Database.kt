package com.example.knight.dagger2demo.atm.data

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import java.math.BigDecimal

class Database(context: Context) {
    private val accounts = HashMap<String, Account>()
    @RequiresApi(Build.VERSION_CODES.N)
    fun getAccount(username: String) = accounts.computeIfAbsent(username) {
        Account(it)
    }


}

data class Account(val username: String, var balance: BigDecimal = BigDecimal.ZERO)
