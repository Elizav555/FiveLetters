package com.example.app_bulls_and_cows.di

import com.example.app_bulls_and_cows.data.preferences.PreferencesImpl
import com.example.app_bulls_and_cows.domain.preferences.Preferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBindsModule {

    @Binds
    @Singleton
    abstract fun bindPreferences(
        preferencesImpl: PreferencesImpl
    ): Preferences
}