package com.example.countplus

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPrefs = application.getSharedPreferences("counterPrefs", Context.MODE_PRIVATE)
    private val _count = MutableStateFlow(sharedPrefs.getInt("count", 0))
    val count = _count.asStateFlow()
    private val _sideEffect = Channel<SideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()
    fun onClickPlus() {
        _count.value++
        sharedPrefs.edit {
            putInt("count", _count.value)

        }
        if (count.value % 10 == 0) {

            _sideEffect.trySend(SideEffect.TOAST)

        }
    }

    enum class SideEffect {
        TOAST
    }
}
