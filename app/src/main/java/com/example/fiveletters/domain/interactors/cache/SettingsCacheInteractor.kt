package com.example.fiveletters.domain.interactors.cache

import com.example.fiveletters.domain.model.Settings

interface SettingsCacheInteractor {
    suspend fun saveSettingsCache(settings: Settings)

    suspend fun deleteSettingsCache()

    suspend fun getSettingsFromCache(): Settings?
}