package com.example.fiveletters.domain.interactors.cache

import kotlin.reflect.KType

interface CacheInteractor {
    suspend fun <T : Any> saveCache(item: T, key: String, type: KType)

    suspend fun deleteCache(key: String, type: KType)

    suspend fun <T : Any> getFromCache(key: String, type: KType): T?
}