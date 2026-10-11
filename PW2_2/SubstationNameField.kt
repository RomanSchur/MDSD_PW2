package com.example.pw2_2.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
@Composable
fun SubstationNameField(
    value: TextFieldValue,
    isError: Boolean,
    onValueChange: (TextFieldValue) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Назва підстанції / вузла") },
        isError = isError,
        modifier = Modifier.fillMaxWidth()
    )

    if (isError) {
        Text(
            text = "Поле не може бути порожнім",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall
        )
    } else {
        Text(
            text = "Введіть офіційну назву вузла (напр., ПС 110/10 кВ)",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}