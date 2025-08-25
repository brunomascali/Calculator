package com.calculator.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.sp
import com.calculator.Calculator
import com.calculator.Calculator.eval
import com.calculator.parse
import com.calculator.ui.components.CalculatorDisplay
import com.calculator.ui.theme.DefaultButtonBackground
import com.calculator.ui.theme.EraseButtonColor
import com.calculator.ui.theme.EvalButtonColor
import com.calculator.ui.theme.OperationButtonColor

enum class ButtonKind {
    Number, Operation, Clear, Erase, Eval, Inactive
}

enum class ProgrammerButton(
    val text: String,
    val kind: ButtonKind = ButtonKind.Number
) {
    A("A"),
    SHL("<<", kind = ButtonKind.Operation),
    SHR(">>", kind = ButtonKind.Operation),
    CLEAR("CE", kind = ButtonKind.Clear),
    ERASE("C", kind = ButtonKind.Clear),

    B("B"),
    PAREN_START("(", kind = ButtonKind.Operation),
    PAREN_END(")", kind = ButtonKind.Operation),
    MOD("%", kind = ButtonKind.Operation),
    DIV("/", kind = ButtonKind.Operation),

    C("C"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TIMES("*", kind = ButtonKind.Operation),

    D("D"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    MINUS("-", kind = ButtonKind.Operation),

    E("E"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    PLUS("+", kind = ButtonKind.Operation),

    F("F"),
    FLIP_SIGNAL("+/-", kind = ButtonKind.Operation),
    ZERO("0"),
    SPACE(" "),
    EQUAL("=", kind = ButtonKind.Eval)
}

data class ProgrammerButtonProps(val props: ProgrammerButton) {
    fun getColor(): Color {
        return when (props.kind) {
            ButtonKind.Number -> DefaultButtonBackground
            ButtonKind.Operation -> OperationButtonColor
            ButtonKind.Clear -> EraseButtonColor
            ButtonKind.Erase -> EraseButtonColor
            ButtonKind.Eval -> EvalButtonColor
            ButtonKind.Inactive -> Color.Transparent
        }
    }

    fun getFontColor(): Color {
        return when (props.kind) {
            ButtonKind.Number -> Color.Black
            ButtonKind.Operation -> Color.White
            ButtonKind.Clear -> Color.White
            ButtonKind.Erase -> Color.White
            ButtonKind.Eval -> Color.White
            ButtonKind.Inactive -> Color.Black
        }
    }
}

enum class NumericalBase(val radix: Int, val label: String) {
    BINARY(2, "BIN"),
    OCTAL(8, "OCT"),
    DECIMAL(10, "DEC"),
    HEXADECIMAL(16, "HEX"),
}

@Preview
@Composable
fun ProgrammerScreen() {
    var currentExpressionText by remember { mutableStateOf("0") }
    val previousExpressions = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        CalculatorDisplay(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            currentExpressionText,
            previousExpressions
        )
        NumericalBase.entries.forEachIndexed { index, base ->
            val background = if (index % 2 == 0) Color.LightGray else Color.DarkGray
            NumericalBaseDisplay(
                modifier = Modifier.background(
                    background
                ),
                number = currentExpressionText.toInt(),
                base = base
            )
        }

        ProgrammerButtonRows(
            onClickCallback = { programmerButton ->
                {
                    when (programmerButton.kind) {
                        ButtonKind.Number -> currentExpressionText += programmerButton.text
                        ButtonKind.Operation -> currentExpressionText += programmerButton.text
                        ButtonKind.Clear -> currentExpressionText = ""
                        ButtonKind.Erase -> currentExpressionText = currentExpressionText.dropLast(1)
                        ButtonKind.Eval -> {
                            val tokens = parse(currentExpressionText)
                            val result = Calculator.formatDouble(eval(tokens))
                            currentExpressionText = result
                        }
                        ButtonKind.Inactive -> {}
                    }
                }
            }
        )
    }
}

@Composable
fun NumericalBaseDisplay(modifier: Modifier = Modifier, number: Int, base: NumericalBase) {
    Row(
        modifier = modifier
            .padding(6.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(start = 10.dp)
        ) {
            Text(base.label)
        }
        Column(
            modifier = Modifier.padding(start = 10.dp)
        ) {
            Text(number.toString(base.radix))
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun ProgrammerButtonRows(
    onClickCallback: (ProgrammerButton) -> (() -> Unit)
) {
    val buttonRows = remember {
        ProgrammerButton.entries.toList()
    }

    Column(
        modifier = Modifier.padding(4.dp)
    ) {
        buttonRows.chunked(5).forEach { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.forEach { button ->
                    val buttonProps = ProgrammerButtonProps(button)
                    val onClick = onClickCallback(button)

                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(2.dp)
                            .combinedClickable(
                                onClick = onClick
                            ),
                        color = buttonProps.getColor(),
                        shape = RoundedCornerShape(6.dp),
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(button.text, color = buttonProps.getFontColor(), fontSize = 28.sp)
                        }
                    }
                }
            }
        }
    }
}