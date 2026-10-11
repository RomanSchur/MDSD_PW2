package com.example.pw2_1.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pw2_1.Screen
import com.example.pw2_1.components.MenuButton
import com.example.pw2_1.components.ScreenTitle

@Composable
fun MainMenuScreen(navController: NavHostController) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isLargeScreen = maxWidth > 600.dp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScreenTitle("Компенсація реактивної потужності")

            if (isLargeScreen) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        MenuButton("Інформація про диспетчера") {
                            navController.navigate(Screen.UserInfo.route)
                        }
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        MenuButton("Моніторинг мережі") {
                            navController.navigate("${Screen.ReactivePower.route}/Черговий")
                        }
                    }
                }
            } else {
                MenuButton("Інформація про диспетчера") {
                    navController.navigate(Screen.UserInfo.route)
                }
                MenuButton("Моніторинг мережі") {
                    navController.navigate("${Screen.ReactivePower.route}/Черговий")
                }
            }
        }
    }
}