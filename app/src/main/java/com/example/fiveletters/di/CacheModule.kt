package com.example.fiveletters.di

import com.example.fiveletters.data.cache.CacheCleaner
import com.example.fiveletters.data.cache.CacheProvider
import com.example.fiveletters.data.cache.LruCacheProvider
import com.example.fiveletters.di.coroutines.qualifiers.IoDispatcher
import com.example.fiveletters.domain.interactors.cache.CacheInteractor
import com.example.fiveletters.domain.interactors.cache.CacheInteractorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
class CacheModule {

    @Provides
    @Singleton
    fun cacheProvider(): CacheProvider = LruCacheProvider()

    @Provides
    @Singleton
    fun cacheCleaner(cacheProvider: CacheProvider): CacheCleaner = cacheProvider

    @Provides
    @Singleton
    fun cacheInteractor(
        cacheProvider: CacheProvider,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): CacheInteractor = CacheInteractorImpl(cacheProvider, coroutineDispatcher)
}