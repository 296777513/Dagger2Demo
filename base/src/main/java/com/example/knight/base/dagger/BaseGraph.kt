package com.example.knight.base.dagger

import com.example.knight.base.account.AccountManager
import com.example.knight.base.process.CommandProcessor

interface BaseGraph : Graph {
    fun accountManager(): AccountManager
    fun commandProcessor(): CommandProcessor
}