package com.example.fiveletters.domain.interactors.preferences

import com.example.fiveletters.domain.model.Game
import com.example.fiveletters.domain.model.keyboard.KeyClick

interface GamePrefsInteractor {
    suspend fun saveGame(key: String, game: Game)

    suspend fun getGame(key: String, keyClicks: List<List<KeyClick>>?): Game?
}