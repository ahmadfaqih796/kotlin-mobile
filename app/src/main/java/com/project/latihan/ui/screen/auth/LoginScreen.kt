package com.project.latihan.ui.screen.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp
import com.project.latihan.ui.components.form.TextFieldV2
import com.project.latihan.ui.components.form.PasswordFieldV2

@Composable
fun LoginScreen(
    onLoginClicked: (email: String, password: String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val focusRequesters = List(2) { FocusRequester() }
    fun requestNextFocus(currentIndex: Int) {
        if (currentIndex < focusRequesters.size - 1) {
            focusRequesters[currentIndex + 1].requestFocus()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextFieldV2(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            focusRequester = focusRequesters[0],
            onDone = { requestNextFocus(0) }
        )
        PasswordFieldV2(
            value = password,
            onValueChange = { password = it },
        )
        Button(
            onClick = {
                onLoginClicked(email, password)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
    }
}