package com.example.fiveletters.domain.interactors.cache

import com.example.fiveletters.domain.model.Game

interface GameCacheInteractor {
    suspend fun saveGameCache(game: Game)

    suspend fun deleteGameCache()

    suspend fun getGameFromCache(): Game?
}