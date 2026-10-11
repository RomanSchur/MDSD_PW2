package com.example.pw2_2.components
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
@Composable
fun ReactivePowerField(
    value: TextFieldValue,
    isError: Boolean,
    onValueChange: (TextFieldValue) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Реактивне навантаження мережі Q (квар)") },
        isError = isError,
        modifier = Modifier.fillMaxWidth()
    )

    if (isError) {
        Text(
            text = "Введіть коректне число більше 0",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall
        )
    } else {
        Text(
            text = "Наприклад: 350 або 500",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}