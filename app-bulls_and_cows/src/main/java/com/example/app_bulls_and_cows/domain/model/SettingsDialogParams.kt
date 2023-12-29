package com.example.app_bulls_and_cows.domain.model

import java.util.Locale

data class SettingsDialogParams(
    val isLocaleChanged: Boolean,
    val locale: Locale
)