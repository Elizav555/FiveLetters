package com.example.fiveletters.domain.repository

interface WordsRepository {
    suspend fun getRandomWord(minLength: Int, maxLength: Int): Result<String>
}