package com.example.pw2_1.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.navigation.NavHostController
import com.example.pw2_1.Screen
import com.example.pw2_1.components.MenuButton
import com.example.pw2_1.components.ScreenTitle

@Composable
fun UserInfoScreen(navController: NavHostController) {
    var operatorName by rememberSaveable { mutableStateOf("") }
    var substationName by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenTitle("Дані чергового диспетчера")

        OutlinedTextField(
            value = operatorName,
            onValueChange = { operatorName = it },
            label = { Text("ПІБ диспетчера") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = substationName,
            onValueChange = { substationName = it },
            label = { Text("Диспетчерський вузол / ПС") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))

        MenuButton("Перейти до моніторингу") {
            val name = operatorName.ifBlank { "Черговий" }
            navController.navigate("${Screen.ReactivePower.route}/$name")
        }
        MenuButton("Повернутися в меню") {
            navController.popBackStack()
        }
    }
}