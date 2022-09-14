package com.example.fiveletters.data.preferences

import com.example.fiveletters.data.mappers.GameDomainMapper.toPrefs
import com.example.fiveletters.data.mappers.GamePrefsMapper.toDomain
import com.example.fiveletters.data.model.prefs.GamePrefs
import com.example.fiveletters.di.coroutines.qualifiers.IoDispatcher
import com.example.fiveletters.domain.interactors.preferences.GamePrefsInteractor
import com.example.fiveletters.domain.model.Game
import com.example.fiveletters.domain.preferences.Preferences
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
