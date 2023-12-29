package com.example.app_bulls_and_cows.ui.events

import java.util.Locale

sealed class MainEvent {
    data class ChangeThemeEvent(val isDarkMode: Boolean) : MainEvent()
    data class ChangeLocaleEvent(val locale: Locale) : MainEvent()
}