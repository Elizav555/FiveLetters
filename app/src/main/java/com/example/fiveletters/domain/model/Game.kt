package com.example.fiveletters.domain.model

data class Game(
    val hiddenWord: String,
    val word: Word = Word(),
    val history: List<Word> = emptyList(),
    val lettersCount: Int,
    val guessesCount: Int = 5,
    val attempts: Int = 1,
)
