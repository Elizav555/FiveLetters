package com.example.app_bulls_and_cows.data.preferences

import com.example.app_bulls_and_cows.data.mappers.GameDomainMapper.toPrefs
import com.example.app_bulls_and_cows.data.mappers.GamePrefsMapper.toDomain
import com.example.app_bulls_and_cows.data.model.prefs.GamePrefs
import com.example.app_bulls_and_cows.domain.model.Game
import com.example.app_bulls_and_cows.domain.preferences.Preferences
import com.example.app_bulls_and_cows.di.coroutines.qualifiers.IoDispatcher
import com.example.app_bulls_and_cows.domain.interactors.preferences.GamePrefsInteractor
import com.google.gson.reflect.TypeToken
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GamePrefsInteractorImpl @Inject constructor(
    private val preferences: Preferences,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) : GamePrefsInteractor {

    override suspend fun saveGame(key: String, game: Game) =
        withContext(coroutineDispatcher) {
            val gamePrefs = game.toPrefs()
            preferences.setItem(key, gamePrefs)
        }

    override suspend fun getGame(key: String): Game? =
        withContext(coroutineDispatcher) {
            val gamePrefs: GamePrefs? = preferences.getItem(
                key,
                object : TypeToken<GamePrefs?>() {}.type
            )
            gamePrefs?.let { it.toDomain() }
        }
}
