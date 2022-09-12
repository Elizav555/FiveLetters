package com.example.fiveletters.domain.interactors.cache

import com.example.fiveletters.data.cache.CacheProvider
import com.example.fiveletters.di.coroutines.qualifiers.IoDispatcher
import javax.inject.Inject
import kotlin.reflect.KType
import kotlin.time.Duration
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext


class CacheInteractorImpl @Inject constructor(
    private val cacheProvider: CacheProvider,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) : CacheInteractor {

    override suspend fun <T : Any> saveCache(item: T, key: String, type: KType) =
        withContext(coroutineDispatcher) {
            cacheProvider.save(
                type,
                key,
                item,
                Duration.INFINITE
            )
        }

    override suspend fun deleteCache(key: String, type: KType) = withContext(coroutineDispatcher) {
        cacheProvider.clear(
            type,
            key
        )
    }

    override suspend fun <T : Any> getFromCache(key: String, type: KType): T? =
        withContext(coroutineDispatcher) {
            cacheProvider.get<T>(
                type,
                key
            )
        }
}
