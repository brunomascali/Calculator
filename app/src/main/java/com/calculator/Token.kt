package com.calculator

enum class TokenType {
    Number,
    Plus, Minus,
    Multiply
}

data class Token(val type: TokenType, val value: Int? = null)
