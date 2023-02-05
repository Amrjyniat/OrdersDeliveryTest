package com.example.ordersdeliverytest.domain.model

import com.example.ordersdeliverytest.data.model.OrderStatus
import com.example.ordersdeliverytest.ui.base.Listable

data class OrderData(
    override val id: Int,
    val typeName: String,
    val totalPrice: String,
    val date: String,
    val orderStatus: OrderStatus
) : Listable