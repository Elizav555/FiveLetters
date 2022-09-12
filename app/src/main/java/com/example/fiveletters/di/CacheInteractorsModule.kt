package com.example.fiveletters.di

import com.example.fiveletters.domain.interactors.cache.GameCacheInteractor
import com.example.fiveletters.domain.interactors.cache.GameCacheInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class CacheInteractorsModule {
    @Binds
    @ViewModelScoped
    abstract fun gameCacheInteractor(gameCacheInteractorImpl: GameCacheInteractorImpl): GameCacheInteractor
}