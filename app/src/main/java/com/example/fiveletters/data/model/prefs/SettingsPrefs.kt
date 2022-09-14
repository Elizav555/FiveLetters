package com.example.fiveletters.data.model.prefs

import java.util.Locale
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SettingsPrefs(
    @SerialName("isDarkMode") val isDarkMode: Boolean?,
    @SerialName("locale") @Contextual val locale: Locale
)
