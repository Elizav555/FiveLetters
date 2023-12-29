package com.example.app_bulls_and_cows.domain.interactors.preferences

import com.example.app_bulls_and_cows.domain.model.Game

interface GamePrefsInteractor {
    suspend fun saveGame(key: String, game: Game)

    suspend fun getGame(key: String): Game?
}