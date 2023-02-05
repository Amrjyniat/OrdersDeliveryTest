package com.example.ordersdeliverytest.util

import android.app.Activity
import android.widget.Toast
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

fun Activity.showToast(msg: String) =
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()

fun Activity.drawingBehalfStatusBar() {
    val insetsControllerCompat = WindowInsetsControllerCompat(window, window.decorView)
    WindowCompat.setDecorFitsSystemWindows(window, false)
    insetsControllerCompat.isAppearanceLightStatusBars = false
}

fun Activity.hideSystemBars() {
    val insetsControllerCompat = WindowInsetsControllerCompat(window, window.decorView)
    insetsControllerCompat.systemBarsBehavior =
        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    insetsControllerCompat.hide(WindowInsetsCompat.Type.systemBars())
}