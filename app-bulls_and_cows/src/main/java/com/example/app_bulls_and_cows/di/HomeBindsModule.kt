package com.example.app_bulls_and_cows.di

import com.example.app_bulls_and_cows.domain.interactors.words.WordsInteractor
import com.example.app_bulls_and_cows.domain.interactors.words.WordsInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeBindsModule {

    @Binds
    @ViewModelScoped
    abstract fun wordsInteractor(wordsInteractorImpl: WordsInteractorImpl): WordsInteractor
}