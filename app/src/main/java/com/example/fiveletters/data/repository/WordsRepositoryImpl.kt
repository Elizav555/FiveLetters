package com.example.fiveletters.data.repository

import com.example.fiveletters.data.api.WordsApi
import com.example.fiveletters.di.coroutines.qualifiers.IoDispatcher
import com.example.fiveletters.domain.repository.WordsRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class WordsRepositoryImpl @Inject constructor(
    private val api: WordsApi,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
) : WordsRepository {
    override suspend fun getRandomWord(length: Int): Result<String> =
        withContext(coroutineDispatcher) {
            val response = api.getRandomWord(length)
            response.body()?.let { Result.success(it.word) }
                ?: Result.failure(Error(response.message()))
        }
}