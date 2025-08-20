package com.calculator

enum class TokenType {
    Number,
    Plus, Minus,
    Multiply, Divide,
    Expo, Sqrt
}

data class Token(val type: TokenType, val value: Double? = null)
