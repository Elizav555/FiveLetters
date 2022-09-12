package com.example.fiveletters.domain.interactors.cache

import com.example.fiveletters.data.cache.CacheProvider
import com.example.fiveletters.di.coroutines.qualifiers.IoDispatcher
import com.example.fiveletters.domain.model.Settings
import javax.inject.Inject
import kotlin.reflect.typeOf
import kotlin.time.Duration
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SettingsCacheInteractorImpl @Inject constructor(
    private val cacheProvider: CacheProvider,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) : SettingsCacheInteractor {

    override suspend fun saveSettingsCache(settings: Settings) = withContext(coroutineDispatcher) {
        cacheProvider.save(
            typeOf<Settings>(),
            ADD_SETTINGS_STATE,
            settings,
            Duration.INFINITE
        )
    }

    override suspend fun deleteSettingsCache() = withContext(coroutineDispatcher) {
        cacheProvider.clear(
            typeOf<Settings>(),
            ADD_SETTINGS_STATE
        )
    }

    override suspend fun getSettingsFromCache() = withContext(coroutineDispatcher) {
        cacheProvider.get<Settings>(
            typeOf<Settings>(),
            ADD_SETTINGS_STATE
        )
    }

    companion object {
        const val ADD_SETTINGS_STATE = "settings_state"
    }
}
