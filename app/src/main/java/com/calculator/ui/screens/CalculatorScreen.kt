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

data class ButtonInfo(
    val text: String,
    val background: Color = DigitButtonColor,
    val textColor: Color = Color.Black,
    val modifier: Modifier = Modifier,
    val onClick: () -> Unit = {
        var displayText by Calculator.input
        displayText += text
    }
)

@Preview
@Composable
fun CalculatorScreen() {
    var displayText by Calculator.input

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row { CalculatorDisplay(displayText) }

        val buttonsRows = listOf(
            listOf(
                ButtonInfo("C", background = EraseButtonColor, textColor = Color.White),
                ButtonInfo(" ", onClick = {}),
                ButtonInfo(" ", onClick = {}),
                ButtonInfo("/", OperatorButtonColor, textColor = Color.White)
            ),
            listOf(
                ButtonInfo("7"),
                ButtonInfo("8"),
                ButtonInfo("9"),
                ButtonInfo("x", OperatorButtonColor, textColor = Color.White)
            ),
            listOf(
                ButtonInfo("4"),
                ButtonInfo("5"),
                ButtonInfo("6"),
                ButtonInfo("+", OperatorButtonColor, textColor = Color.White)
            ),
            listOf(
                ButtonInfo("1"),
                ButtonInfo("2"),
                ButtonInfo("3"),
                ButtonInfo("-", OperatorButtonColor, textColor = Color.White)
            ),
            listOf(
                ButtonInfo("."),
                ButtonInfo("0"),
                ButtonInfo(" ", onClick = {}),
                ButtonInfo("=", EvalButtonColor, textColor = Color.White, onClick = { displayText = eval(parse(displayText)).toString() })
            ),
        )

        buttonsRows.forEach { CalculatorRow(it) }
    }
}

@Composable
fun CalculatorRow(buttons: List<ButtonInfo>) {
    Row(
        modifier = Modifier.padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        buttons.forEach { button ->
            CalculatorButton(button.text, background = button.background, textColor = button.textColor) { button.onClick() }
        }
    }
}

