package com.example.fiveletters.data.preferences

import com.example.fiveletters.data.model.prefs.SettingsPrefs
import com.example.fiveletters.di.coroutines.qualifiers.IoDispatcher
import com.example.fiveletters.domain.interactors.preferences.SettingsPrefsInteractor
import com.example.fiveletters.domain.model.Settings
import com.example.fiveletters.domain.preferences.Preferences
import com.google.gson.reflect.TypeToken
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext


class SettingsPrefsInteractorImpl @Inject constructor(
    private val preferences: Preferences,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) : SettingsPrefsInteractor {

    override suspend fun saveSettings(key: String, settings: Settings) =
        withContext(coroutineDispatcher) {
            val settingsPrefs = SettingsPrefs(settings.isDarkMode, settings.locale)
            preferences.setItem(key, settingsPrefs)
        }

    override suspend fun getSettings(key: String): Settings? =
        withContext(coroutineDispatcher) {
            val settingsPrefs = preferences.getItem<SettingsPrefs>(
                key,
                object : TypeToken<SettingsPrefs?>() {}.type
            )
            settingsPrefs?.let { Settings(it.isDarkMode, it.language) }
        }
}
