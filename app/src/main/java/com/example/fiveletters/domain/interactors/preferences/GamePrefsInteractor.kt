package com.example.fiveletters.domain.interactors.preferences

import com.example.fiveletters.domain.model.Game

interface GamePrefsInteractor {
    suspend fun saveGame(key: String, game: Game)

    suspend fun getGame(key: String): Game?
}