package com.example.ordersdeliverytest.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ordersdeliverytest.data.OrderApi
import com.example.ordersdeliverytest.util.createJsonValue
import com.example.ordersdeliverytest.util.myShareIn
import com.example.ordersdeliverytest.util.myStateIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val orderApi: OrderApi
) : ViewModel() {

    val inputUserName = MutableStateFlow("")
    val inputPassword = MutableStateFlow("")

    val isSubmittable = inputUserName.combine(inputPassword) { userName, pass ->
        userName.isNotEmpty() && pass.isNotEmpty()
    }.myStateIn(viewModelScope, initialValue = false)

    private val loginChannel = Channel<Unit>()
    val loginResp = loginChannel.receiveAsFlow().mapLatest {
        val requestBody = createJsonValue(
            "P_LANG_NO" to "1",
            "P_DLVRY_NO" to inputUserName.value,
            "P_PSSWRD" to inputPassword.value,
        )

        orderApi.login(requestBody)
    }.myShareIn(viewModelScope)

    fun login() = viewModelScope.launch {
        loginChannel.send(Unit)
    }

}
