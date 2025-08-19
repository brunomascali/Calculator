package com.calculator

import org.junit.Test
import org.junit.Assert.*

class ParserTests {
    @Test
    fun parseFloat() {
        val input = "42.69"
        val tokens = parse(input)
        assertEquals(tokens.first(), Token(TokenType.Number, 42.69))
    }
}