package com.example.ordersdeliverytest.ui.orders

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.ordersdeliverytest.data.model.OrderResp.Companion.toOrderData
import com.example.ordersdeliverytest.databinding.ActivityOrdersBinding
import com.example.ordersdeliverytest.util.drawingBehalfStatusBar
import com.example.ordersdeliverytest.util.launchAndRepeatInLifecycle
import com.example.ordersdeliverytest.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrdersBinding
    private val viewModel: OrdersViewModel by viewModels()
    private val orderAdapter = OrdersAdapter()

    private val userName: String by lazy {
        intent.getStringExtra(USER_NAME_ARG).orEmpty()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        drawingBehalfStatusBar()

        binding = ActivityOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvUserName.text = userName

        viewModel.ordersStatus.launchAndRepeatInLifecycle(this) {
            val response = it.body()
            if (response != null) {
                if (response.resultResponse.ErrNo == 0) {
                    val orders = response.data.deliveryStatusTypes
                    orderAdapter.submitList(orders.toOrderData())
                } else {
                    showToast("Login failed: ${response.resultResponse.errorMsg}")
                }
            } else {
                showToast("Something went wrong :(")
            }
        }

        binding.rvOrders.adapter = orderAdapter

    }

    companion object {
        const val USER_NAME_ARG = "USER_NAME_ARG"
    }

}