package com.example.fiveletters.data.mappers

import com.example.fiveletters.data.model.SettingsPrefs
import com.example.fiveletters.domain.model.Settings

object SettingsMapper {
    fun SettingsPrefs.mapToDomain() = Settings(
        isDarkMode = isDarkMode, language = language
    )

    fun Settings.mapToPrefs() = SettingsPrefs(
        isDarkMode = isDarkMode, language = language
    )
}