package com.calculator

import org.junit.Test
import org.junit.Assert.*

class CalculatorTests {
    @Test
    fun simple_add() {
        val input = "1+2"
        assertEquals(Calculator.eval(parse(input)), 3)
    }

    @Test
    fun simple_sub() {
        val input = "1-2"
        assertEquals(Calculator.eval(parse(input)), -1)
    }

    @Test
    fun simple_mul() {
        val input = "2*3"
        assertEquals(Calculator.eval(parse(input)), 6)
    }

    @Test
    fun precedence() {
        var input = "1+2*3"
        assertEquals(Calculator.eval(parse(input)), 7)
        input = "1*2+3"
        assertEquals(Calculator.eval(parse(input)), 5)
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
}