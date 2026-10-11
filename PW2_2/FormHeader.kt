package com.example.pw2_2.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
@Composable
fun FormHeader() {
    Text(
        text = "Реєстрація вузла компенсації реактивної потужності",
        style = MaterialTheme.typography.headlineSmall
    )
}