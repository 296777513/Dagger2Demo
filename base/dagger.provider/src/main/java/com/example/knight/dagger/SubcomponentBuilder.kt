package com.example.knight.dagger


interface SubcomponentBuilder<T : Graph> {
    fun build(): T
}