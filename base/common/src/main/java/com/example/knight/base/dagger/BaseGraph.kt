package com.example.knight.base.dagger

import com.example.knight.base.process.CommandProcessor
import com.example.knight.dagger.Graph

interface BaseGraph : Graph {
    fun commandProcessor(): CommandProcessor
}