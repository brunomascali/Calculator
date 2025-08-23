package com.calculator

import java.util.Locale
import java.util.Stack
import kotlin.math.pow
import kotlin.math.sqrt

object Calculator {
    fun eval(expr: List<Token>): Double {
        val numbers = Stack<Double>()
        val operations = Stack<Token>()

        fun popAndEval(): Double {
            val operation = operations.pop()
            val b = numbers.pop()

            if (operation.isUnop()) {
                return unop(operation, b)
            } else {
                val a = numbers.pop()
                return binop(operation, a, b)
            }
        }

        for (token in expr) {
            when (token.type) {
                TokenType.Number -> numbers.push(token.value)

                TokenType.ParenStart -> operations.push(token)

                TokenType.ParenEnd -> {
                    while (operations.peek().type != TokenType.ParenStart) {
                        val result = popAndEval()
                        numbers.push(result)
                    }
                    operations.pop()
                }

                else -> {
                    val op = token
                    while (operations.isNotEmpty()
                        && operations.peek().type != TokenType.ParenStart
                        && (operations.peek().precedence() > op.precedence()
                                || (operations.peek()
                            .precedence() == op.precedence() && op.associativity() == Associativity.Left))
                    ) {
                        val result = popAndEval()
                        numbers.push(result)
                    }
                    operations.push(op)
                }
            }
        }

        while (operations.isNotEmpty()) {
            val result = popAndEval()
            numbers.push(result)
        }

        return numbers.peek()
    }

    private fun binop(token: Token, a: Double, b: Double): Double {
        return when (token.type) {
            TokenType.Plus -> a + b
            TokenType.Minus -> a - b
            TokenType.Multiply -> a * b
            TokenType.Divide -> a / b
            TokenType.Expo -> a.pow(b)
            else -> TODO("Unreachable at binop")
        }
    }

    private fun unop(token: Token, a: Double): Double {
        return when (token.type) {
            TokenType.Sqrt -> sqrt(a)
            else -> TODO("Unreachable at unop")
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