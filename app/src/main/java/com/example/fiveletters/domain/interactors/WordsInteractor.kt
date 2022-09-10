package com.example.fiveletters.domain.interactors

interface WordsInteractor {
    suspend fun getRandomWord(minLength: Int, maxLength: Int): Result<String>
}