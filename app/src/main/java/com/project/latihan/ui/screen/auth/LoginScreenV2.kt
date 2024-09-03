package com.project.latihan.ui.screen.auth

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.latihan.R

@Composable
fun LoginScreenV2() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Yellow background for the top part
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.Yellow)
        )

        // Semi-circle shape
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.BottomCenter),
            contentAlignment = Alignment.TopCenter
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawRoundRect(
                    color = Color.Red,
                    topLeft = center.copy(x = size.width / 2, y = size.height / 2),
                    size = size.copy(width = size.width, height = size.height),
                    cornerRadius = CornerRadius(60f, 60f)
                )
            }
        }

        // Place the Image
        Image(
            painter = painterResource(id = R.drawable.user), // Replace with your image resource
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.TopCenter)
                .offset(y = 100.dp),
            contentScale = ContentScale.Crop
        )

        // Other UI elements like TextFields and Buttons go here
        // Example:
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp)
        ) {
            // Text fields and buttons would go here
        }
    }
}