package com.example.fiveletters.domain.interactors.words

interface WordsInteractor {
    suspend fun getRandomWord(length: Int): Result<String>
}