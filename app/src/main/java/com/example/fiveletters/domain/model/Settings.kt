package com.example.fiveletters.domain.model

import java.util.Locale

data class Settings(
    val isDarkMode: Boolean? = null,
    val locale: Locale = Locale.ENGLISH
)
