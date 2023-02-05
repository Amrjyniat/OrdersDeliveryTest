package com.example.ordersdeliverytest.util

import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

inline fun <T> Flow<T>.launchAndRepeatInLifecycle(
    lifecycle: LifecycleOwner,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline result: suspend (T) -> Unit
) {
    lifecycle.lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(state) {
            collectLatest { result(it) }
        }
    }
}

inline fun EditText.bind(
    lifecycle: LifecycleOwner,
    inputName: MutableStateFlow<String>,
    crossinline onTextChanged: (String) -> Unit = {}
) {
    doAfterTextChanged {
        val text = it.toString()
        if (text != inputName.value) {
            inputName.value = text
            onTextChanged(text)
        }
    }
    inputName.launchAndRepeatInLifecycle(lifecycle) { newValue ->
        if (newValue != text.toString()) setText(newValue)
    }
}

fun Button.bindEnable(
    lifecycle: LifecycleOwner,
    enabledStatus: StateFlow<Boolean>
) = enabledStatus.launchAndRepeatInLifecycle(lifecycle) { isEnabled = it }

fun <T> Flow<T>.myShareIn(
    scope: CoroutineScope,
    started: SharingStarted = SharingStarted.Lazily,
    replay: Int = 0
): SharedFlow<T> = shareIn(scope, started, replay)

fun <T> Flow<T>.myStateIn(
    scope: CoroutineScope,
    initialValue: T,
    started: SharingStarted = SharingStarted.Lazily
): StateFlow<T> = stateIn(scope, started, initialValue)
