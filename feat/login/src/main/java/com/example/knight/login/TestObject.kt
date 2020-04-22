package com.example.knight.login

import com.example.knight.base.Outputter
import javax.inject.Inject

class TestObject {

    @Inject
    lateinit var outputter: Outputter

    @Inject
    lateinit var hints: String
    fun test() {

    }
}