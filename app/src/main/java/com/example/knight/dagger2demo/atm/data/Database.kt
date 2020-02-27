package com.example.knight.dagger2demo.atm.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.math.BigDecimal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Database @Inject constructor() {

    private val accounts = HashMap<String, Account>()

    @RequiresApi(Build.VERSION_CODES.N)
    fun getAccount(username: String) = accounts.computeIfAbsent(username) {
        Account(it)
    }


}

data class Account(val username: String, var balance: BigDecimal = BigDecimal.ZERO)
