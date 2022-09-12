package com.example.fiveletters.ui.events

sealed class MainEvent {
    data class ChangeThemeEvent(val isDarkMode: Boolean) : MainEvent()
}