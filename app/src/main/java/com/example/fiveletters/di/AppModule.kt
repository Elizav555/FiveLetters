package com.example.fiveletters.di

import com.example.fiveletters.di.coroutines.qualifiers.IoDispatcher
import com.example.fiveletters.domain.interactors.preferences.PreferencesInteractor
import com.example.fiveletters.domain.interactors.preferences.PreferencesInteractorImpl
import com.example.fiveletters.domain.preferences.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun preferencesInteractor(
        preferences: Preferences,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): PreferencesInteractor = PreferencesInteractorImpl(preferences, coroutineDispatcher)
}