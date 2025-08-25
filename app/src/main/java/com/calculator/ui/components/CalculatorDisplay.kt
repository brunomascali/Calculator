package com.calculator.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.calculator.ui.theme.CalculatorDisplayColor

@Preview
@Composable
fun CalculatorDisplay(
    modifier: Modifier = Modifier,
    currentExpression: String = "9",
    previousExpressions: List<String> = listOf("1+2", "3*3")
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = CalculatorDisplayColor,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(0.0f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom
        ) {
            previousExpressions.map {
                Text(
                    it,
                    modifier = Modifier.padding(8.dp),
                    fontSize = 28.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                currentExpression,
                modifier = Modifier.padding(8.dp),
                fontSize = 40.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

