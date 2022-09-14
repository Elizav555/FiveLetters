package com.example.fiveletters.domain.interactors.words

import com.example.fiveletters.domain.repository.WordsRepository
import javax.inject.Inject

class WordsInteractorImpl @Inject constructor(
    private val wordsRepository: WordsRepository
) : WordsInteractor {
    override suspend fun getRandomWord(length: Int): Result<String> =
        wordsRepository.getRandomWord(length)
}