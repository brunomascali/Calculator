package com.calculator

enum class TokenType {
    Number,
    Plus, Minus,
    Multiply, Divide
}

data class Token(val type: TokenType, val value: Double? = null)
