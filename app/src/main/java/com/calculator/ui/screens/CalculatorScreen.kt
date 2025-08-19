package com.calculator.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.calculator.ui.components.CalculatorButton
import com.calculator.ui.components.CalculatorDisplay
import com.calculator.ui.theme.DigitButtonColor
import com.calculator.ui.theme.EraseButtonColor
import com.calculator.ui.theme.EvalButtonColor
import com.calculator.ui.theme.OperatorButtonColor
import com.calculator.Calculator
import com.calculator.Calculator.eval
import com.calculator.parse

@Preview
@Composable
fun CalculatorScreen() {
    var displayText by Calculator.input

    Column(
        modifier = Modifier.fillMaxSize().background(Color.DarkGray).padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            CalculatorDisplay(displayText)
        }
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            listOf('7', '8', '9', '*').map {
                CalculatorButton(it.toString(), color = if (it == '*') OperatorButtonColor else DigitButtonColor) { displayText += it }
            }
        }
        Row(
            modifier = Modifier.padding(bottom=8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            listOf('4', '5', '6', '-').map {
                CalculatorButton(it.toString(), color = if (it == '-') OperatorButtonColor else DigitButtonColor) { displayText += it }
            }
        }
        Row(
            modifier = Modifier.padding(bottom=8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf('1', '2', '3', '+').map {
                CalculatorButton(it.toString(), color = if (it == '+') OperatorButtonColor else DigitButtonColor) { displayText += it }
            }

        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            listOf('0', ',').map {
                CalculatorButton(it.toString(), color = if (it == '+') OperatorButtonColor else DigitButtonColor) { displayText += it }
            }
            CalculatorButton("x", color = EraseButtonColor) { displayText = displayText.dropLast(1) }
            CalculatorButton("=", color = EvalButtonColor) { displayText = eval(parse(displayText)).toString() }
        }
    }

}

