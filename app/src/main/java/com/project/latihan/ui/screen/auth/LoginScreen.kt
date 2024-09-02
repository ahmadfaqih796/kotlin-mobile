package com.project.latihan.ui.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.project.latihan.ui.components.button.CustomButton
import com.project.latihan.ui.components.form.TextFieldV2
import com.project.latihan.ui.components.form.PasswordFieldV2
import com.project.latihan.R

@Composable
fun LoginScreen(
    onLoginClicked: (email: String, password: String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val focusRequesters = List(2) { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    fun requestNextFocus(currentIndex: Int) {
        if (currentIndex < focusRequesters.size - 1) {
            focusRequesters[currentIndex + 1].requestFocus()
        } else {
            keyboardController?.hide()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.user),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
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
            focusRequester = focusRequesters[1],
            onDone = { keyboardController?.hide() }
        )
        CustomButton(
            label = "Login",
            onClick = {onLoginClicked(email, password)
        })
    }
}