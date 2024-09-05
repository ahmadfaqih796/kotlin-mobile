package com.project.latihan.ui.components.loading

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FadeLoading(
    isLoading: Boolean
) {
    AnimatedVisibility(
        visible = isLoading,
        enter = fadeIn(animationSpec = tween(durationMillis = 500)),
        exit = fadeOut(animationSpec = tween(durationMillis = 500))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(enabled = false) {}
                .background(Color.Black.copy(alpha = 0.7f))
                .absoluteOffset(x = 0.dp, y = 0.dp)

                .wrapContentSize(Alignment.Center)
        ) {
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier.size(50.dp)
            )
        }
    }
}
