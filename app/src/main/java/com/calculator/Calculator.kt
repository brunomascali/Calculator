package com.calculator

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.util.Locale
import java.util.Stack
import kotlin.math.pow
import kotlin.math.sqrt

object Calculator {
    var input: MutableState<String> = mutableStateOf("")

    fun eval(expr: List<Token>): Double {
        val numbers = Stack<Double>()
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
                    if (operation == TokenType.Sqrt) {
                        numbers.push(unop(operation, b))
                    } else {
                        val a = numbers.pop()
                        numbers.push(binop(operation, a, b))
                    }
                }
                operations.push(token.type)
            }
        }

        while (operations.isNotEmpty()) {
            val operation = operations.pop()

            val b = numbers.pop()
            if (operation == TokenType.Sqrt) {
                numbers.push(unop(operation, b))
            } else {
                val a = numbers.pop()
                numbers.push(binop(operation, a, b))
            }
        }

        return numbers.first()
    }

    private fun binop(opType: TokenType, a: Double, b: Double): Double {
        return when (opType) {
            TokenType.Plus -> a + b
            TokenType.Minus -> a - b
            TokenType.Multiply -> return a * b
            TokenType.Divide -> a / b
            TokenType.Expo ->  a.pow(b)
            else -> -42.0
        }
    }

    private fun unop(opType: TokenType, a: Double): Double {
        return when (opType) {
            TokenType.Sqrt -> sqrt(a)
            else -> -42.0
        }
    }

    private fun precedence(operator: TokenType): Int {
        return when (operator) {
            TokenType.Plus -> 1
            TokenType.Minus -> 1
            TokenType.Multiply -> 2
            TokenType.Divide -> 2
            TokenType.Expo -> 3
            TokenType.Sqrt -> 3
            TokenType.Number -> TODO()
        }
    }

    fun formatDouble(value: Double): String {
        return if (value % 1.0 == 0.0) {
            value.toInt().toString()
        } else {
            String.format(locale = Locale.US, "%.8f", value).trimEnd('0').trimEnd('.')
        }
    }

}