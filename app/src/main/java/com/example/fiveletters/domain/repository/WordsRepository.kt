package com.example.fiveletters.domain.repository

import com.example.fiveletters.domain.model.Word

interface WordsRepository {
    suspend fun getRandomWord(minLength: Int, maxLength: Int): Result<Word>
}