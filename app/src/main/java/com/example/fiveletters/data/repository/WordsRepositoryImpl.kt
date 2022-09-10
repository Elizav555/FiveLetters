package com.example.fiveletters.data.repository

import com.example.fiveletters.data.api.WordsApi
import com.example.fiveletters.di.coroutines.qualifiers.IoDispatcher
import com.example.fiveletters.domain.model.Word
import com.example.fiveletters.domain.repository.WordsRepository
import com.example.fiveletters.home.utils.Result
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class WordsRepositoryImpl @Inject constructor(
    private val api: WordsApi,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
) : WordsRepository {
    override suspend fun getRandomWord(minLength: Int, maxLength: Int): Result<Word> =
        withContext(coroutineDispatcher) {
            val response = api.getRandomWord(minLength = minLength, maxLength = maxLength)
            response.body()?.let { Result.success(it.mapToDomain()) }
                ?: Result.error(Error(response.message()))
        }
}