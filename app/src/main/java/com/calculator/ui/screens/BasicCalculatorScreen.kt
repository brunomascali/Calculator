package com.calculator.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.calculator.Calculator
import com.calculator.Calculator.eval
import com.calculator.parse
import com.calculator.ui.components.CalculatorDisplay
import com.calculator.ui.theme.EraseButtonColor
import com.calculator.ui.theme.EvalButtonColor
import com.calculator.ui.theme.OperatorButtonColor

@Preview
@Composable
fun BasicScreen() {
    var currentExpressionText by remember { mutableStateOf("") }
    val previousExpressions = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        val buttonsRows = listOf(
            listOf(
                ButtonInfo(
                    "C",
                    background = EraseButtonColor,
                    textColor = Color.White,
                    onClick = {
                        currentExpressionText = currentExpressionText.dropLast(1)
                    },
                    onLongClick = {
                        currentExpressionText = ""
                        previousExpressions.clear()
                    }),
                ButtonInfo("(", OperatorButtonColor, textColor = Color.White),
                ButtonInfo(")", OperatorButtonColor, textColor = Color.White),
                ButtonInfo(
                    "/", OperatorButtonColor, textColor = Color.White
                )
            ),
            listOf(
                ButtonInfo("7"),
                ButtonInfo("8"),
                ButtonInfo("9"),
                ButtonInfo("*", OperatorButtonColor, textColor = Color.White)
            ),
            listOf(
                ButtonInfo("4"),
                ButtonInfo("5"),
                ButtonInfo("6"),
                ButtonInfo("-", OperatorButtonColor, textColor = Color.White)

            ),
            listOf(
                ButtonInfo("1"),
                ButtonInfo("2"),
                ButtonInfo("3"),
                ButtonInfo("+", OperatorButtonColor, textColor = Color.White)
            ),
            listOf(
                ButtonInfo("."),
                ButtonInfo("0"),
                ButtonInfo(
                    "=",
                    EvalButtonColor,
                    textColor = Color.White,
                    onClick = {
                        val tokens = parse(currentExpressionText)
                        val result = Calculator.formatDouble(eval(tokens))
                        previousExpressions.add(currentExpressionText)
                        if (previousExpressions.size > 20) {
                            previousExpressions.removeAt(0)
                        }
                        currentExpressionText = result
                    }),
            ),
        )

        val defaultOnClick: (String) -> Unit = { text ->
            currentExpressionText += text
        }

        CalculatorDisplay(
            currentExpression = currentExpressionText,
            previousExpressions = previousExpressions,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        )

        buttonsRows.forEach { row ->
            CalculatorRow(
                row.map { button ->
                    button.copy(
                        onClick = button.onClick ?: { defaultOnClick(button.text) }
                    )
                }
            )
        }

        // Padding
        Row(modifier = Modifier.padding(top = 12.dp)) {}
    }
}
