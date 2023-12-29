package com.example.app_bulls_and_cows.data.mappers

import com.example.app_bulls_and_cows.data.model.prefs.SettingsPrefs
import com.example.app_bulls_and_cows.domain.model.Settings

object SettingsMapper {
    fun SettingsPrefs.mapToDomain() = Settings(
        isDarkMode = isDarkMode, locale = locale
    )

    fun Settings.mapToPrefs() = SettingsPrefs(
        isDarkMode = isDarkMode, locale = locale
    )
}