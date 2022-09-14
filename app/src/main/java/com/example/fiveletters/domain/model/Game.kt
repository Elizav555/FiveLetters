package com.example.fiveletters.domain.model

import com.example.fiveletters.domain.model.keyboard.Keyboard
import com.example.fiveletters.domain.model.letter.LettersCount

data class Game(
    val hiddenWord: String,
    val word: Word = Word(),
    val history: List<Word> = emptyList(),
    val lettersCount: LettersCount,
    val guessesCount: Int = 5,
    val attempts: Int = 1,
    val keyboard : Keyboard
)
