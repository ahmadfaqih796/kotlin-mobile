package com.project.latihan.view.layer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.project.latihan.model.datastore.UserPreferencesDataStore
import com.project.latihan.ui.components.button.CustomButton
import com.project.latihan.ui.layout.SimpleLayout
import com.project.latihan.view.auth.LoginActivity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    private lateinit var userPreferencesDataStore: UserPreferencesDataStore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize UserPreferencesDataStore
        userPreferencesDataStore = UserPreferencesDataStore(applicationContext)
        Log.d("ssssssss", "xsxsxsx")

        setContent {
            SimpleLayout {
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
                                performLogout()
                            },
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
                            .weight(0.8f),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {

                    }
                }

            }
        }
    }

    private fun performLogout() {
        // Clear the session token and navigate back to login screen
        lifecycleScope.launch {
            userPreferencesDataStore.saveUserToken("")  // Clear the token
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

}