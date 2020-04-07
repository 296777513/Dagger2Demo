package com.example.knight.base.account

import java.math.BigDecimal

class AccountManager {
    private var account: Account? = null

    fun createAccount(username: String) = Account(username).apply {
        account = this
    }

    fun getAccount(username: String) = account ?: Account(username)

    fun getCurrentAccount() = account
    fun clearAccount() {
        account = null
    }
}

data class Account(val username: String, var balance: BigDecimal = BigDecimal.ZERO)
