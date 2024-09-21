package com.project.latihan.ui.screen.layer

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.project.latihan.ui.components.button.CustomButton

@Composable
public fun MainScreen(
    onLogoutClicked: () -> Unit,
    context: Context
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f)
                .background(Color(0xFFB04A4A)),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hallo"
            )
            CustomButton(
                modifier = Modifier
                    .width(150.dp),
                onClick = {
                    onLogoutClicked()
                },
                backgroundColor = Color.DarkGray,
                label = "Logout",
                icon = {
                    Icon(
                        imageVector = Icons.Default.Logout,
                        contentDescription = "Logout Icon"
                    )
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.8f)
                .background(Color(0xFFB04A4A)),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
// nampilin mapnya ?
            MapScreen(
                context = context
            )
        }
    }
}