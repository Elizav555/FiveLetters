package com.example.app_bulls_and_cows.domain.model

import com.example.app_bulls_and_cows.domain.model.keyboard.Keyboard

data class Game(
    val hiddenWord: String,
    val word: Word = Word(),
    val history: List<Word> = emptyList(),
    val guessesCount: Int = 7,
    val attempts: Int = 1,
    val keyboard : Keyboard
)
