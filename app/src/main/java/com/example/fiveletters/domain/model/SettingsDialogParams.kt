package com.example.fiveletters.domain.model

import java.util.Locale

data class SettingsDialogParams(
    val lettersCount: LettersCount,
    val isLocaleChanged: Boolean,
    val locale: Locale
)