package com.project.latihan.view.auth

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.project.latihan.controller.AuthController
import com.project.latihan.model.datastore.UserPreferencesDataStore
import com.project.latihan.ui.layout.SimpleLayout
import com.project.latihan.ui.screen.auth.LoginScreen
import com.project.latihan.ui.screen.auth.LoginScreenV2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : ComponentActivity() {

    private lateinit var authController: AuthController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize AuthController
        val userPreferencesDataStore = UserPreferencesDataStore(applicationContext)
        authController = AuthController(userPreferencesDataStore)

        setContent {
            var isLoading by remember { mutableStateOf(false) }
            SimpleLayout {
//                LoginScreen(
//                    onLoginClicked = { email, password ->
//                        performLogin(email, password)
//                    }
//                )
                LoginScreenV2(
                    isLoading = isLoading,
                    onLoginClicked = { email, password ->
                        performLogin(email, password) { loading ->
                            isLoading = loading
                        }
                    }
                )
            }
        }
    }

    private fun performLogin(
        email: String,
        password: String,
        onLoadingChange: (Boolean) -> Unit
    ) {
        onLoadingChange(true)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = authController.login(email, password)
                Log.d("haaaaai", "xxxxxxxxx ${response}")
                Toast.makeText(
                    this@LoginActivity,
                    "Login successful! Welcome ${response.user.fullname}",
                    Toast.LENGTH_LONG
                ).show()
                // Navigate to the next screen or handle login success
            } catch (e: Exception) {
                Log.d("haaaaai", "xxxxxxxxx ${e.message}")
                Toast.makeText(this@LoginActivity, e.message, Toast.LENGTH_LONG).show()
            } finally {
                onLoadingChange(false)
            }
        }
    }

}