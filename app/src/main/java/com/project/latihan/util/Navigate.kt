package com.project.latihan.util

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat

public fun navigateToActivity(context: Context, destination: Class<out ComponentActivity>) {
    val intent = Intent(context, destination)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    context.startActivity(intent)
    if (context is ComponentActivity) {
        context.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        ActivityCompat.finishAffinity(context)
    }
}