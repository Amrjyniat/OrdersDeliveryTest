package com.example.ordersdeliverytest.ui.orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.ordersdeliverytest.R
import com.example.ordersdeliverytest.data.model.OrderStatus
import com.example.ordersdeliverytest.databinding.ItemOrderBinding
import com.example.ordersdeliverytest.domain.model.OrderData
import com.example.ordersdeliverytest.ui.base.BaseListAdapter

class OrdersAdapter : BaseListAdapter<OrderData, ItemOrderBinding>() {

    override fun onHolderCreated(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = ItemOrderBinding.inflate(layoutInflater, parent, false)

    override fun ItemOrderBinding.onDataBound(item: OrderData) {

        tvId.text = item.id.toString()
        tvStatusValue.text = item.typeName
        tvPriceValue.text = item.totalPrice
        tvDateValue.text = item.date

        val colorRes = when (item.orderStatus) {
            OrderStatus.DELIVERED -> R.color.green
            OrderStatus.PARTIAL_RETURN -> R.color.grey
            OrderStatus.FULL_RETURN -> R.color.red
        }

        val color = ContextCompat.getColor(root.context, colorRes)
        tvStatusValue.setTextColor(color)
        tvOrderDetails.setBackgroundColor(color)
    }
}