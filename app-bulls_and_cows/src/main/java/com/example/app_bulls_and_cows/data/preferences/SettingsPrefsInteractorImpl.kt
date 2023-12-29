package com.example.app_bulls_and_cows.data.preferences

import com.example.app_bulls_and_cows.data.model.prefs.SettingsPrefs
import com.example.app_bulls_and_cows.domain.model.Settings
import com.example.app_bulls_and_cows.domain.preferences.Preferences
import com.example.app_bulls_and_cows.di.coroutines.qualifiers.IoDispatcher
import com.example.app_bulls_and_cows.domain.interactors.preferences.SettingsPrefsInteractor
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
            settingsPrefs?.let { Settings(it.isDarkMode, it.locale) }
        }
}
