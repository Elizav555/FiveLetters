package com.example.fiveletters.di

import com.example.fiveletters.data.repository.WordsRepositoryImpl
import com.example.fiveletters.domain.repository.WordsRepository
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
    abstract fun bindWordsRepository(
        wordsRepositoryImpl: WordsRepositoryImpl
    ): WordsRepository
}