package com.example.fiveletters.domain.repository

interface WordsRepository {
    suspend fun getRandomWord(length: Int): Result<String>
}