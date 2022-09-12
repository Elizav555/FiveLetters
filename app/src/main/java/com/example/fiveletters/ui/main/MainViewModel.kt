package com.example.fiveletters.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiveletters.domain.interactors.cache.CacheInteractor
import com.example.fiveletters.domain.model.Settings
import com.example.fiveletters.ui.events.MainEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.reflect.typeOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val cacheInteractor: CacheInteractor
) : ViewModel() {
    private val _isDarkModeState: MutableStateFlow<Settings> = MutableStateFlow(Settings())
    val isDarkModeState: StateFlow<Settings> = _isDarkModeState

    init {
        getInitialSettingsState()
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.ChangeThemeEvent -> {
                setMode(event.isDarkMode)
            }
        }
    }

    private fun getInitialSettingsState() = viewModelScope.launch {
        cacheInteractor.getFromCache<Settings>(SETTINGS_KEY, typeOf<Settings>())?.let {
            _isDarkModeState.update { it }
        }
    }

    private fun setMode(isDarkMode: Boolean) {
        _isDarkModeState.update { it.copy(isDarkMode = isDarkMode) }
    }

    companion object {
        const val SETTINGS_KEY = "settings_key"
    }
}