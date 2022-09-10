package com.example.fiveletters.domain.interactors

import com.example.fiveletters.domain.model.Word

interface WordsInteractor {
    suspend fun getRandomWord(minLength: Int, maxLength: Int): Result<Word>
}