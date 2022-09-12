package com.example.fiveletters.ui.main

import androidx.lifecycle.ViewModel
import com.example.fiveletters.ui.events.MainEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _isDarkModeState: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val isDarkModeState: StateFlow<Boolean?> = _isDarkModeState

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.ChangeThemeEvent -> {
                setMode(event.isDarkMode)
            }
        }
    }

    private fun setMode(isDarkMode: Boolean) {
        _isDarkModeState.update { isDarkMode }
    }
}