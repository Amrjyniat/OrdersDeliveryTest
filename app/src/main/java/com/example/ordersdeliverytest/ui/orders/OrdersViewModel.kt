package com.example.ordersdeliverytest.ui.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ordersdeliverytest.data.OrderApi
import com.example.ordersdeliverytest.util.createJsonValue
import com.example.ordersdeliverytest.util.myShareIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val orderApi: OrderApi
) : ViewModel() {

    private val loadOrdersChannel = Channel<Unit>()
    val ordersStatus = loadOrdersChannel.receiveAsFlow().mapLatest {
        val requestBody = createJsonValue("P_LANG_NO" to "2")
        orderApi.getOrdersStatus(requestBody)
    }.myShareIn(viewModelScope, replay = 1)

    init {
        loadOrdersStatus()
    }

    private fun loadOrdersStatus() = viewModelScope.launch {
        loadOrdersChannel.send(Unit)
    }

}
