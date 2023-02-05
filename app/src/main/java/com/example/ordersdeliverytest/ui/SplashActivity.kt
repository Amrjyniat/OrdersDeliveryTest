package com.example.ordersdeliverytest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.ordersdeliverytest.R
import com.example.ordersdeliverytest.ui.login.LoginActivity
import com.example.ordersdeliverytest.util.hideSystemBars
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        hideSystemBars()

        lifecycleScope.launch {
            delay(1000)
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        }
    }
}