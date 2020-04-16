package com.example.knight.dagger

interface TopLevelComponentProvider {
    /**
     * Provide access to a top level dagger component which implements [graphClass] interface.
     */
    fun <T : Graph> component(graphClass: Class<T>): T
}