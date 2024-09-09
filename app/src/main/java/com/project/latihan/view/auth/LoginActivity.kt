package com.project.latihan.view.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.project.latihan.view.layer.MainActivity
import com.project.latihan.controller.AuthController
import com.project.latihan.model.datastore.UserPreferencesDataStore
import com.project.latihan.ui.layout.SimpleLayout
import com.project.latihan.ui.screen.auth.LoginScreenV2
import com.project.latihan.util.navigateToActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
class LoginActivity : ComponentActivity() {

    private lateinit var authController: AuthController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mengatur warna status bar
        setStatusBarColor(Color(0xFFB04A4A))

        // Initialize AuthController
        val userPreferencesDataStore = UserPreferencesDataStore(applicationContext)
        authController = AuthController(userPreferencesDataStore)

        // Cek token saat aplikasi dijalankan
        lifecycleScope.launch {
            authController.userToken.collect { token ->
                if (!token.isNullOrEmpty()) {
                    navigateToActivity(this@LoginActivity, MainActivity::class.java)
//                    navigateToMainActivity()
                } else {
                    setContent {
                        var isLoading by remember { mutableStateOf(false) }
                        SimpleLayout {
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
                navigateToActivity(this@LoginActivity, MainActivity::class.java)

            } catch (e: Exception) {
                Log.d("haaaaai", "xxxxxxxxx ${e.message}")
                Toast.makeText(this@LoginActivity, e.message, Toast.LENGTH_LONG).show()
            } finally {
                onLoadingChange(false)
            }
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }

    private fun setStatusBarColor(color: Color) {
        // Mengambil kontroler untuk status bar
        WindowCompat.getInsetsController(window, window.decorView).apply {
            // Mengatur apakah ikon status bar harus terang
            isAppearanceLightStatusBars = color == Color.White
        }
        // Mengatur warna status bar
        window.statusBarColor = color.toArgb()
    }

}