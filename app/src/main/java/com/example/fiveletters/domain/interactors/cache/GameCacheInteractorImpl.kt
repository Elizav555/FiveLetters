package com.example.fiveletters.domain.interactors.cache

import com.example.fiveletters.data.cache.CacheProvider
import com.example.fiveletters.di.coroutines.qualifiers.IoDispatcher
import com.example.fiveletters.domain.model.Game
import javax.inject.Inject
import kotlin.reflect.typeOf
import kotlin.time.Duration
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GameCacheInteractorImpl @Inject constructor(
    private val cacheProvider: CacheProvider,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) : GameCacheInteractor {

    override suspend fun saveGameCache(Game: Game) = withContext(coroutineDispatcher) {
        cacheProvider.save(
            typeOf<Game>(),
            ADD_GAME_STATE,
            Game,
            Duration.INFINITE
        )
    }

    override suspend fun deleteGameCache() = withContext(coroutineDispatcher) {
        cacheProvider.clear(
            typeOf<Game>(),
            ADD_GAME_STATE
        )
    }

    override suspend fun getGameFromCache() = withContext(coroutineDispatcher) {
        cacheProvider.get<Game>(
            typeOf<Game>(),
            ADD_GAME_STATE
        )
    }

    companion object {
        const val ADD_GAME_STATE = "game_state"
    }
}
