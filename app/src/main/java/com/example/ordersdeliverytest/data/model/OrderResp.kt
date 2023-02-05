package com.example.ordersdeliverytest.data.model

import com.example.ordersdeliverytest.domain.model.OrderData
import com.squareup.moshi.Json

class DeliveryStatusTypesResp(
    @Json(name = "DeliveryStatusTypes") val deliveryStatusTypes: List<OrderResp>
)

data class OrderResp(
    @Json(name = "TYP_NO") val orderStatus: OrderStatus,
    @Json(name = "TYP_NM") val typeName: String
){
    companion object{
        fun List<OrderResp>.toOrderData() = mapIndexed { index, orderResp ->
            with(orderResp) {
                OrderData(index, typeName, "${(100..1000).random()} LE", "11/6/2020" , orderStatus)
            }
        }
    }
}

enum class OrderStatus {
    @Json(name = "1")
    DELIVERED,

    @Json(name = "2")
    PARTIAL_RETURN,

    @Json(name = "3")
    FULL_RETURN
}