package com.calculator.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
import com.calculator.ui.theme.EraseButtonColor
import com.calculator.ui.theme.EvalButtonColor
import com.calculator.ui.theme.OperatorButtonColor
import com.calculator.Calculator
import com.calculator.Calculator.eval
import com.calculator.parse
import com.calculator.ui.theme.DefaultButtonBackground

data class ButtonInfo(
    val text: String,
    val background: Color = DefaultButtonBackground,
    val textColor: Color = Color.Black,
    val modifier: Modifier = Modifier,
    val onClick: () -> Unit = {
        var displayText by Calculator.input
        displayText += text
    },
    val onLongClick: () -> Unit = {}
)

@Preview
@Composable
fun CalculatorScreen() {
    var displayText by Calculator.input

    MaterialTheme() {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                CalculatorDisplay(
                    text = displayText, modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth()
                        .height(128.dp)
                )
            }

            val buttonsRows = listOf(
                listOf(
                    ButtonInfo(
                        "C",
                        background = EraseButtonColor,
                        textColor = Color.White,
                        onClick = { displayText = displayText.dropLast(1) },
                        onLongClick = {
                            Calculator.input.value = ""
                        }),
                    ButtonInfo("√", OperatorButtonColor, textColor = Color.White),
                    ButtonInfo("^", OperatorButtonColor, textColor = Color.White),
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
                    ButtonInfo("=", EvalButtonColor, textColor = Color.White, onClick = {
                        val tokens = parse(displayText)
                        val result = Calculator.formatDouble(eval(tokens))
                        displayText = result
                    })
                ),
            )

            buttonsRows.forEach { CalculatorRow(it) }
        }
    }
}

@Composable
fun CalculatorRow(buttons: List<ButtonInfo>) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(start = 4.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        buttons.forEach { button ->
            CalculatorButton(
                button.text,
                backgroundColor = button.background,
                fontColor = button.textColor,
                onClick = button.onClick,
                onLongClick = button.onLongClick,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
            )
        }
    }
}

