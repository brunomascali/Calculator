package com.calculator

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.util.Stack

object Calculator {
    var input: MutableState<String> = mutableStateOf("")

    private fun op(opType: TokenType, a: Int, b: Int): Int {
        if (opType == TokenType.Plus) return a + b
        if (opType == TokenType.Minus) return a - b
        if (opType == TokenType.Multiply) return a * b

        return 0
    }

    fun precedence(operator: TokenType): Int {
        return when (operator) {
            TokenType.Plus -> 1
            TokenType.Minus -> 1
            TokenType.Multiply -> 2
            TokenType.Number -> TODO()
        }
    }

    fun eval(expr: List<Token>): Int {
        val numbers = Stack<Int>()
        val operations = Stack<TokenType>()

        for (token in expr) {
            if (token.type == TokenType.Number) {
                numbers.push(token.value)
            }
            else {
                val operator = token.type
                while (operations.isNotEmpty() && precedence(operations.first()) > precedence(operator)) {
                    val operation = operations.pop()
                    val b = numbers.pop()
                    val a = numbers.pop()

                    numbers.push(op(operation, a, b))
                }
                operations.push(token.type)
            }
        }

        while (operations.isNotEmpty()) {
            val operation = operations.pop()
            val b = numbers.pop()
            val a = numbers.pop()

            numbers.push(op(operation, a, b))
        }

        return numbers.first()
    }
}