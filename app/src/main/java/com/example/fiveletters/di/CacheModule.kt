package com.example.fiveletters.di

import com.example.fiveletters.data.cache.CacheCleaner
import com.example.fiveletters.data.cache.CacheProvider
import com.example.fiveletters.data.cache.LruCacheProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheModule {

    @Provides
    @Singleton
    fun cacheProvider(): CacheProvider = LruCacheProvider()

    @Provides
    @Singleton
    fun cacheCleaner(cacheProvider: CacheProvider): CacheCleaner = cacheProvider
}