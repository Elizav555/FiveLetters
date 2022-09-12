package com.example.fiveletters.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiveletters.domain.interactors.preferences.PreferencesInteractor
import com.example.fiveletters.domain.model.Settings
import com.example.fiveletters.ui.events.MainEvent
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.reflect.Type
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferencesInteractor: PreferencesInteractor
) : ViewModel() {
    private val _settingsState: MutableStateFlow<Settings> = MutableStateFlow(Settings())
    val settingsState: StateFlow<Settings> = _settingsState

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
        val type: Type = object : TypeToken<Settings?>() {}.type
        val settings = preferencesInteractor.getItem<Settings>(SETTINGS_KEY, type)
        settings?.let{_settingsState.update { settings }}
    }

    private fun setMode(isDarkMode: Boolean) = viewModelScope.launch {
        val newSettings = _settingsState.value.copy(isDarkMode = isDarkMode)
        _settingsState.update { newSettings }
        preferencesInteractor.saveItem(SETTINGS_KEY, newSettings)
    }

    companion object {
        const val SETTINGS_KEY = "settings_key"
    }
}