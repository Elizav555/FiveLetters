package com.example.app_bulls_and_cows.di

import com.example.app_bulls_and_cows.di.coroutines.qualifiers.IoDispatcher
import com.example.app_bulls_and_cows.domain.interactors.preferences.GamePrefsInteractor
import com.example.app_bulls_and_cows.data.preferences.GamePrefsInteractorImpl
import com.example.app_bulls_and_cows.domain.interactors.preferences.SettingsPrefsInteractor
import com.example.app_bulls_and_cows.data.preferences.SettingsPrefsInteractorImpl
import com.example.app_bulls_and_cows.domain.preferences.Preferences
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