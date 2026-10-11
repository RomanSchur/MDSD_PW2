package com.example.pw2_1.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pw2_1.Screen
import com.example.pw2_1.components.InfoRow
import com.example.pw2_1.components.MenuButton
import com.example.pw2_1.components.ScreenTitle

@Composable
fun CompensationDetailsScreen(navController: NavHostController, stepsCount: Int) {
    val stepPower = 50.0
    val initialQ = 350.0
    val compensatedQ = stepsCount * stepPower
    val remainingQ = (initialQ - compensatedQ).coerceAtLeast(0.0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenTitle("Результати компенсації")

        InfoRow(label = "Підключено ступенів БК", value = stepsCount.toString())
        InfoRow(label = "Потужність батареї", value = "$compensatedQ квар")
        InfoRow(label = "Залишкове реактивне навантаження", value = "$remainingQ квар")

        Spacer(modifier = Modifier.height(24.dp))

        MenuButton("Змінити параметри") {
            navController.popBackStack()
        }
        MenuButton("В головне меню") {
            navController.navigate(Screen.MainMenu.route) {
                popUpTo(Screen.MainMenu.route) { inclusive = true }
            }
        }
    }
}