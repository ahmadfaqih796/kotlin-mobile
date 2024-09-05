package com.project.latihan.ui.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.latihan.R
import com.project.latihan.ui.components.button.CustomButton
import com.project.latihan.ui.components.form.PasswordFieldV2
import com.project.latihan.ui.components.form.TextFieldV2

@Composable
fun LoginScreenV2(
    onLoginClicked: (email: String, password: String) -> Unit
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isSubmit by remember { mutableStateOf(false) }

    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    val focusRequesters = List(2) { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    fun requestNextFocus(currentIndex: Int) {
        if (currentIndex < focusRequesters.size - 1) {
            focusRequesters[currentIndex + 1].requestFocus()
        } else {
            keyboardController?.hide()
        }
    }

    fun validateInputs(): Boolean {
        var isValid = true
        if (isSubmit) {
            emailError = when {
                email.isBlank() -> "Email cannot be empty"
                !email.matches(Regex("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) -> "Invalid email format"
                else -> null
            }
//            emailError = if (email.isBlank()) "Email cannot be empty" else null
            passwordError = if (password.isBlank()) "Password cannot be empty" else null
            if (emailError != null || passwordError != null) {
                isValid = false
            }
        }
        return isValid
    }

    Box(
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.dots),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFB04A4A))
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
//                .weight(0.35f)
                .height(250.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.user_icon),
                contentDescription = null,
                modifier = Modifier
                    .width(200.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            )
//            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Peduli Lindungi",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Color.White,
                fontFamily = FontFamily.Monospace
            )
        }
        Column(
            modifier = Modifier
//                .weight(0.65f)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color(0xFFB04A4A)),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(0.8f)
                    .fillMaxWidth()
                    .padding(24.dp, 0.dp),
                verticalArrangement = Arrangement.Center
            ) {
                TextFieldV2(
                    value = email,
//                    onValueChange = { email = it },
                    onValueChange = { newValue ->
                        email = newValue
                        if (isSubmit) {
//                            emailError = if (newValue.isBlank()) "Email cannot be empty" else null
                            emailError = when {
                                newValue.isBlank() -> "Email cannot be empty"
                                !newValue.matches(Regex("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) -> "Invalid email format"
                                else -> null
                            }
                        }
                    },
                    label = "Email",
                    focusRequester = focusRequesters[0],
                    onDone = { requestNextFocus(0) },
                    isError = emailError != null,
                    errorMessage = emailError
                )
                PasswordFieldV2(
                    value = password,
                    onValueChange = { newValue ->
                        password = newValue
                        if (isSubmit) {
                            passwordError =
                                if (newValue.isBlank()) "Password cannot be empty" else null
                        }
                    },
                    focusRequester = focusRequesters[1],
                    onDone = { keyboardController?.hide() },
                    isError = passwordError != null,
                    errorMessage = passwordError
                )
                CustomButton(
                    label = "Login",
                    backgroundColor = Color.White,
                    contentColor = Color(0xFFB04A4A),
                    onClick = {
                        isSubmit = true
                        if (validateInputs()) {
                            onLoginClicked(email, password)
                        }
                    })
            }
            Column(
                modifier = Modifier
                    .weight(0.2f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.footer),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }

}