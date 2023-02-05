package com.example.ordersdeliverytest.data.model

import com.squareup.moshi.Json

class BaseResponse<T>(
    @Json(name = "Data") val data: T,
    @Json(name = "Result") val resultResponse: ResultResponse
)

class ResultResponse(
    @Json(name = "ErrMsg") val errorMsg: String,
    @Json(name = "ErrNo") val ErrNo: Int
)