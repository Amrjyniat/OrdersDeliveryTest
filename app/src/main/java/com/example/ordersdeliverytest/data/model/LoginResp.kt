package com.example.ordersdeliverytest.data.model

import com.squareup.moshi.Json

class LoginResp(
    @Json(name = "DeliveryName") val DeliveryName: String?
)