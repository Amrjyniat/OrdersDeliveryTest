package com.example.ordersdeliverytest.data

import com.example.ordersdeliverytest.data.model.BaseResponse
import com.example.ordersdeliverytest.data.model.DeliveryStatusTypesResp
import com.example.ordersdeliverytest.data.model.LoginResp
import com.example.ordersdeliverytest.data.model.OrderResp
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface OrderApi {

    @POST("CheckDeliveryLogin")
    suspend fun login(
        @Body body: RequestBody
    ): Response<BaseResponse<LoginResp>>

    @POST("GetDeliveryStatusTypes")
    suspend fun getOrdersStatus(
        @Body body: RequestBody
    ): Response<BaseResponse<DeliveryStatusTypesResp>>

}

