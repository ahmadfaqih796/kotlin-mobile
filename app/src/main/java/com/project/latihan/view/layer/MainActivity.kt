package com.project.latihan.view.layer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.lifecycleScope
import com.project.latihan.model.datastore.UserPreferencesDataStore
import com.project.latihan.ui.layout.SimpleLayout
import com.project.latihan.ui.screen.layer.MainScreen
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
                MainScreen(
                    onLogoutClicked = { performLogout() }
                )
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