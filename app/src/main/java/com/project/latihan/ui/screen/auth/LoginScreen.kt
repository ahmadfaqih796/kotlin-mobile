package com.project.latihan.ui.screen.auth

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.project.latihan.ui.components.button.CustomButton
import com.project.latihan.ui.components.form.TextFieldV2
import com.project.latihan.ui.components.form.PasswordFieldV2
import com.project.latihan.R
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

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
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.4f)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xff990099), // Violet
                            Color(0xffffccff)  // Dark Violet
                        )
                    )
                ),
            verticalArrangement = Arrangement.Bottom
        ) {
            Image(
                painter = painterResource(id = R.drawable.user),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .weight(0.8f)
                    .align(alignment = Alignment.CenterHorizontally)
            )
            // Box for Semicircle
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f)
                    .height(50.dp)
            ) {
                drawSemicircle()
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.6f)
//                .background(Color.Yellow)
                .padding(16.dp, 0.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Login",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 40.sp
            )
            Spacer(modifier = Modifier.height(30.dp))
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
                onClick = {
                    onLoginClicked(email, password)
                })
        }

    }
}

fun DrawScope.drawSemicircle() {
    // Translate the canvas to center the semicircle
    translate(
//        left = 0f,
//        top = size.height / 2
    ) {
        drawArc(
            color = Color.White,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = true,
            size = size.copy(height = size.height * 2, width = size.width)
        )
    }
}