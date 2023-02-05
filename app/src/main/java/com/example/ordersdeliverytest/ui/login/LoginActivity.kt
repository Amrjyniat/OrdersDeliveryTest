package com.example.ordersdeliverytest.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.ordersdeliverytest.databinding.ActivityLoginBinding
import com.example.ordersdeliverytest.ui.orders.OrdersActivity
import com.example.ordersdeliverytest.util.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        drawingBehalfStatusBar()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root){ view, insets ->
            val bottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            view.updatePadding(bottom = bottom)
            insets
        }

        binding.apply {
            inputUserName.bind(this@LoginActivity, viewModel.inputUserName)
            inputPassword.bind(this@LoginActivity, viewModel.inputPassword)
            btnLogin.bindEnable(this@LoginActivity, viewModel.isSubmittable)

            btnLogin.setOnClickListener {
                viewModel.login()
            }
        }

        viewModel.loginResp.launchAndRepeatInLifecycle(this) {
            val response = it.body()
            if (response != null) {
                if (response.resultResponse.ErrNo == 0) {
                    showToast("Login Successfully")
                    val intent = Intent(this@LoginActivity, OrdersActivity::class.java).apply {
                        putExtra(OrdersActivity.USER_NAME_ARG, response.data.DeliveryName)
                    }
                    startActivity(intent)
                } else {
                    showToast("Login failed: ${response.resultResponse.errorMsg}")
                }
            } else {
                showToast("Something went wrong :(")
            }
        }


    }
}