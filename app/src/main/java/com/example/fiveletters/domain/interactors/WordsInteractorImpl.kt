package com.example.fiveletters.domain.interactors

import com.example.fiveletters.domain.model.Word
import com.example.fiveletters.domain.repository.WordsRepository
import javax.inject.Inject

class WordsInteractorImpl @Inject constructor(
    private val wordsRepository: WordsRepository
) : WordsInteractor {
    override suspend fun getRandomWord(minLength: Int, maxLength: Int): Result<Word> =
        wordsRepository.getRandomWord(minLength, maxLength)
}