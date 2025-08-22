package com.calculator

enum class TokenType {
    Number,
    Plus, Minus,
    Multiply, Divide,
    Expo, Sqrt,
    ParenStart, ParenEnd
}

enum class Associativity {
    Left, Right
}

data class Token(val type: TokenType, val value: Double? = null) {
    fun precedence(): Int {
        return when (type) {
            TokenType.Plus -> 1
            TokenType.Minus -> 1
            TokenType.Multiply -> 2
            TokenType.Divide -> 2
            TokenType.Expo -> 3
            TokenType.Sqrt -> 3
            else -> TODO()
        }
    }

    fun isUnop(): Boolean {
        return when (type) {
            TokenType.Sqrt -> true
            else -> false
        }
    }

    fun isBinop(): Boolean {
        return when (type) {
            TokenType.Plus -> true
            TokenType.Minus -> true
            TokenType.Multiply -> true
            TokenType.Divide -> true
            TokenType.Expo -> true
            else -> false
        }
    }

    fun associativity(): Associativity {
        return when (type) {
            TokenType.Expo -> Associativity.Left
            else -> Associativity.Right
        }
    }
}
