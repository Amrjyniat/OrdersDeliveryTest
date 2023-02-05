package com.example.ordersdeliverytest.util

import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

fun createJson(vararg params: Pair<String, Any>?): JSONObject {
    val paramsNotNull = params.filterNotNull().toTypedArray()
    return JSONObject(mapOf(*paramsNotNull))
}

fun createJsonValue(vararg params: Pair<String, Any>?): RequestBody {
    val json = createJson(*params)
    return JSONObject().apply {
        put("Value", json)
    }.toString().toRequestBody()
}