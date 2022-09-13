package com.example.fiveletters.domain.interactors.preferences

import com.example.fiveletters.domain.model.Settings

interface SettingsPrefsInteractor {
    suspend fun saveSettings(key: String, settings: Settings)

    suspend fun getSettings(key: String): Settings?
}