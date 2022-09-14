package com.example.fiveletters.domain.model

import com.example.fiveletters.domain.model.letter.LettersCount
import java.util.Locale

data class SettingsDialogParams(
    val lettersCount: LettersCount,
    val isLocaleChanged: Boolean,
    val locale: Locale
)