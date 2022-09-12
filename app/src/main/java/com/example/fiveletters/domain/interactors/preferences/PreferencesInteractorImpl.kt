package com.example.fiveletters.domain.interactors.preferences

import com.example.fiveletters.di.coroutines.qualifiers.IoDispatcher
import com.example.fiveletters.domain.preferences.Preferences
import java.lang.reflect.Type
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext


class PreferencesInteractorImpl @Inject constructor(
    private val preferences: Preferences,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) : PreferencesInteractor {

    override suspend fun <T : Any> saveItem(key: String, item: T) =
        withContext(coroutineDispatcher) {
            preferences.setItem(key, item)
        }

    override suspend fun <T : Any> getItem(key: String, type: Type): T? =
        withContext(coroutineDispatcher) {
            preferences.getItem(key, type)
        }
}
