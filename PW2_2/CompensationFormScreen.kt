package com.example.pw2_2.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.pw2_2.components.*

@Composable
fun CompensationFormScreen() {
    var substationName by remember { mutableStateOf(TextFieldValue("")) }
    var operatorEmail by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var reactivePower by remember { mutableStateOf(TextFieldValue("")) }
    var substationNameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var powerError by remember { mutableStateOf(false) }
    var isSubmitted by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(32.dp)) // <-- Відступ 32.dp або 40.dp зверху від камери
        FormHeader()

        SubstationNameField(substationName, substationNameError) {
            substationName = it
            substationNameError = false
        }

        Spacer(modifier = Modifier.height(8.dp))

        EmailField(operatorEmail, emailError) {
            operatorEmail = it
            emailError = false
        }

        Spacer(modifier = Modifier.height(8.dp))

        PasswordField(password, passwordError) {
            password = it
            passwordError = false
        }
        Spacer(modifier = Modifier.height(8.dp))

        ReactivePowerField(reactivePower, powerError) {
            reactivePower = it
            powerError = false
        }
        Spacer(modifier = Modifier.height(16.dp))
        SubmitButton(
            onSubmit = {
                isSubmitted = false
                var valid = true
                if (substationName.text.isBlank()) {
                    substationNameError = true
                    valid = false
                }
                if (!operatorEmail.text.contains("@") || !operatorEmail.text.contains(".")) {
                    emailError = true
                    valid = false
                }
                if (password.text.length < 8 ||
                    !password.text.any { it.isUpperCase() } ||
                    !password.text.any { it.isDigit() }
                ) {
                    passwordError = true
                    valid = false
                }
                val powerValue = reactivePower.text.toDoubleOrNull()
                if (powerValue == null || powerValue <= 0.0) {
                    powerError = true
                    valid = false
                }
                if (valid) {
                    isLoading = true
                    scope.launch {
                        delay(2000)
                        isLoading = false
                        isSubmitted = true
                    }
                }
            }
        )
        if (isLoading) {
            Spacer(modifier = Modifier.height(16.dp))
            LoadingIndicator()
        }
        if (isSubmitted) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Вузол КРП успішно зареєстровано та параметри перевірено!",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}