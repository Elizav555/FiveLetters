package com.example.fiveletters.domain.model

data class SettingsDialogParams(
    val lettersCount: LettersCount,
    val isLocaleChanged: Boolean
)