package com.calculator.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

enum class Destination(
    val route: String,
    val icon: ImageVector
) {
    BASIC("basic", Icons.Default.Home),
    SCIENTIFIC("scientific", Icons.Filled.Star),
    PROGRAMMER("programmer", Icons.Filled.Star)
}