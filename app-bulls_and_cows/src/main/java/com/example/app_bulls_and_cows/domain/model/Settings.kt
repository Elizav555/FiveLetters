package com.example.app_bulls_and_cows.domain.model

import java.util.Locale

data class Settings(
    val isDarkMode: Boolean? = null,
    val locale: Locale = Locale.ENGLISH
)
