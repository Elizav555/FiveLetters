package com.example.fiveletters.data.mappers

import com.example.fiveletters.data.model.prefs.SettingsPrefs
import com.example.fiveletters.domain.model.Settings

object SettingsMapper {
    fun SettingsPrefs.mapToDomain() = Settings(
        isDarkMode = isDarkMode, locale = language
    )

    fun Settings.mapToPrefs() = SettingsPrefs(
        isDarkMode = isDarkMode, language = locale
    )
}