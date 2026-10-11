package com.example.pw2_2.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun EmailField(
    value: TextFieldValue,
    isError: Boolean,
    onValueChange: (TextFieldValue) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Email диспетчера") },
        isError = isError,
        modifier = Modifier.fillMaxWidth()
    )

    if (isError) {
        Text(
            text = "Некоректний email (очікується @ та .)",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall
        )
    } else {
        Text(
            text = "Формат: dispatcher@grid.ua",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}