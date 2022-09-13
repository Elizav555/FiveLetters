package com.example.fiveletters.domain.model

data class Game(
    val hiddenWord: String,
    val word: Word = Word(),
    val history: List<Word> = emptyList(),
    val lettersCount: LettersCount,
    val guessesCount: Int = 5,
    val attempts: Int = 1,
    val keyboard : Keyboard
)
