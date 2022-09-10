package com.example.fiveletters.domain.model

import com.example.fiveletters.domain.utils.mockedDictionary

data class Game(
    val hiddenWord: String = mockedDictionary.random(),
    val word: Word = Word(),
    val history: List<Word> = emptyList(),
    val lettersCount: Int = 5,
    val guessesCount: Int = 5,
    val attempts: Int = 1,
)
