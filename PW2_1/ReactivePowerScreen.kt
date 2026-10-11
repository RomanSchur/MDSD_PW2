package com.example.pw2_1.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pw2_1.Screen
import com.example.pw2_1.components.MenuButton
import com.example.pw2_1.components.ScreenTitle

@Composable
fun ReactivePowerScreen(navController: NavHostController, operatorName: String) {
    var stepsCountInput by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenTitle("Диспетчер: $operatorName")
        Text("Мережа 10 кВ | Поточне навантаження Q = 350 квар", fontSize = 15.sp)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = stepsCountInput,
            onValueChange = { stepsCountInput = it },
            label = { Text("Ступенів батареї конденсаторів (по 50 квар)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        MenuButton("Розрахувати компенсацію") {
            val steps = stepsCountInput.toIntOrNull() ?: 0
            navController.navigate("${Screen.CompensationDetails.route}/$steps")
        }
        MenuButton("Повернутися в меню") {
            navController.navigate(Screen.MainMenu.route) {
                popUpTo(Screen.MainMenu.route) { inclusive = true }
            }
        }
    }
}