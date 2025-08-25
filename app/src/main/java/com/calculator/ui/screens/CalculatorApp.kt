package com.calculator.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.calculator.ui.components.CalculatorButton
import com.calculator.ui.components.Destination
import com.calculator.ui.components.NavigationBar
import com.calculator.ui.theme.DefaultButtonBackground

data class ButtonInfo(
    val text: String,
    val background: Color = DefaultButtonBackground,
    val textColor: Color = Color.Black,
    val modifier: Modifier = Modifier,
    val onClick: (() -> Unit)? = null,
    val onLongClick: () -> Unit = {}
)

@Preview
@Composable
fun CalculatorApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val startDestination = Destination.PROGRAMMER

    Scaffold(
        modifier = modifier,
        bottomBar = { NavigationBar(navController = navController) }
    ) { padding ->
        AppNavHost(navController, startDestination, padding)
    }
}

@Composable
private fun AppNavHost(
    navController: NavHostController,
    startDestination: Destination,
    padding: PaddingValues
) {
    NavHost(navController, startDestination = startDestination.route) {
        Destination.entries.forEach { destination ->
            composable(destination.route) {
                Column(
                    modifier = Modifier.padding(padding)
                ) {
                    when (destination) {
                        Destination.BASIC -> BasicScreen()
                        Destination.SCIENTIFIC -> ScientificScreen()
                        Destination.PROGRAMMER -> ProgrammerScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun CalculatorRow(buttons: List<ButtonInfo>) {
    Row(
        modifier = Modifier
            .padding(4.dp),
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
                    .weight(if (button.text == "=") 2.1f else 1f)
                    .aspectRatio(if (button.text == "=") 2.1f else 1f)
            )
        }
    }
}
