package com.calculator.ui.components

import androidx.compose.foundation.layout.Box
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

@Preview
@Composable
fun CalculatorDisplay(modifier: Modifier = Modifier, text: String = "", ) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = modifier,
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray
            ),
            shape = RoundedCornerShape(0.0f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 10.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(text, fontSize = 40.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

