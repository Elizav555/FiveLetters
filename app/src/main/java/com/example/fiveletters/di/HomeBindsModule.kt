package com.example.fiveletters.di

import com.example.fiveletters.domain.interactors.WordsInteractor
import com.example.fiveletters.domain.interactors.WordsInteractorImpl
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