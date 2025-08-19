package com.calculator

fun parse(input: String): List<Token> {
    val tokens = mutableListOf<Token>()

    val operators = mapOf(
        '+' to Token(TokenType.Plus),
        '-' to Token(TokenType.Minus),
        '*' to Token(TokenType.Multiply),
    )

    var i = 0
    while (i < input.length) {
        var current = input[i]
        if (input[i] in operators) {
            tokens.add(operators.getValue(current))
            i += 1
        }
        else {
            val str = StringBuilder()
            while (current.isDigit()) {
                str.append(current)
                i += 1
                if (i >= input.length) break
                else current = input[i]
            }
            tokens.add(Token(TokenType.Number, str.toString().toInt()))
        }
    }

    return tokens
}