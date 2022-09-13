package com.example.fiveletters.di

import com.example.fiveletters.di.coroutines.qualifiers.IoDispatcher
import com.example.fiveletters.domain.interactors.preferences.GamePrefsInteractor
import com.example.fiveletters.data.preferences.GamePrefsInteractorImpl
import com.example.fiveletters.domain.interactors.preferences.SettingsPrefsInteractor
import com.example.fiveletters.data.preferences.SettingsPrefsInteractorImpl
import com.example.fiveletters.domain.preferences.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
class InteractorBindsModule {
    @Provides
    @Singleton
    fun gamePrefsInteractor(
        preferences: Preferences,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): GamePrefsInteractor = GamePrefsInteractorImpl(preferences, coroutineDispatcher)

    @Provides
    @Singleton
    fun settingsPrefsInteractor(
        preferences: Preferences,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): SettingsPrefsInteractor = SettingsPrefsInteractorImpl(preferences, coroutineDispatcher)
}