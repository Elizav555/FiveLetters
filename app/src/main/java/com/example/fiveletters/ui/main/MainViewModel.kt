package com.example.fiveletters.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiveletters.domain.interactors.preferences.SettingsPrefsInteractor
import com.example.fiveletters.domain.model.Settings
import com.example.fiveletters.ui.events.MainEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val settingsPrefsInteractor: SettingsPrefsInteractor
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
            is MainEvent.ChangeLocaleEvent -> {
                setLocale(event.locale)
            }
        }
    }

    private fun getInitialSettingsState() = viewModelScope.launch {
        val settings = settingsPrefsInteractor.getSettings(SETTINGS_KEY)
        settings?.let { _settingsState.update { settings } }
    }

    private fun setMode(isDarkMode: Boolean) = viewModelScope.launch {
        val newSettings = _settingsState.value.copy(isDarkMode = isDarkMode)
        _settingsState.update { newSettings }
        settingsPrefsInteractor.saveSettings(SETTINGS_KEY, newSettings)
    }

    private fun setLocale(locale: Locale) = viewModelScope.launch {
        val newSettings = _settingsState.value.copy(locale = locale)
        _settingsState.update { newSettings }
        settingsPrefsInteractor.saveSettings(SETTINGS_KEY, newSettings)
    }

    companion object {
        const val SETTINGS_KEY = "settings_key"
    }
}