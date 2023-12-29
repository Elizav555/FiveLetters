package com.example.app_bulls_and_cows.domain.interactors.preferences

import com.example.app_bulls_and_cows.domain.model.Settings

interface SettingsPrefsInteractor {
    suspend fun saveSettings(key: String, settings: Settings)

    suspend fun getSettings(key: String): Settings?
}