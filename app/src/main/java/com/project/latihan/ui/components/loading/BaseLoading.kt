package com.project.latihan.ui.components.loading

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BaseLoading(
    isLoading: Boolean
) {
    if (isLoading) {
        // InfiniteTransition to animate values indefinitely
        val infiniteTransition = rememberInfiniteTransition(label = "")

        // Animating the size of the CircularProgressIndicator
        val size by infiniteTransition.animateFloat(
            initialValue = 40f,
            targetValue = 60f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )

        // Animating the color of the CircularProgressIndicator
        val color: Color by infiniteTransition.animateColor(
            initialValue = Color.White,
            targetValue = Color.Gray,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .absoluteOffset(x = 0.dp, y = 0.dp)
                .background(Color.Black.copy(alpha = 0.7f))
                .wrapContentSize(Alignment.Center)
        ) {
            CircularProgressIndicator(
                color = color,
                modifier = Modifier.size(size.dp)
            )
        }
    }
}