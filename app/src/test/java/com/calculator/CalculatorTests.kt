package com.calculator

import org.junit.Test
import org.junit.Assert.*

class CalculatorTests {
    @Test
    fun simple_add() {
        val input = "1+2"
        assertEquals(Calculator.eval(parse(input)), 3.0, 1e-10)
    }

    @Test
    fun simple_sub() {
        val input = "1-2"
        assertEquals(Calculator.eval(parse(input)), -1.0, 1e-10)
    }

    @Test
    fun simple_mul() {
        val input = "2*3"
        assertEquals(Calculator.eval(parse(input)), 6.0, 1e-10)
    }

    @Test
    fun precedence() {
        val input = "1+2*3"
        assertEquals(Calculator.eval(parse(input)), 7.0, 1e-10)

    }

    @Test
    fun precedence2() {
        val input = "1*2+3"
        assertEquals(Calculator.eval(parse(input)), 5.0, 1e-10)
    }

    @Test
    fun power() {
        val input = "2^3"
        assertEquals(Calculator.eval(parse(input)), 8.0, 1e-10)
    }

    @Test
    fun sqrt() {
        val input = "√4"
        assertEquals(Calculator.eval(parse(input)), 2.0, 1e-10)
    }

    @Test
    fun parenthesis() {
        val input = "2*(3+7)"
        assertEquals(Calculator.eval(parse(input)), 20.0, 1e-10)
    }
}