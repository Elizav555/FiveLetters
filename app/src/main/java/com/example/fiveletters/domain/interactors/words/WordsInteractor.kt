package com.example.fiveletters.domain.interactors.words

interface WordsInteractor {
    suspend fun getRandomWord(minLength: Int, maxLength: Int): Result<String>
}