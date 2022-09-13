package com.example.fiveletters.data.model

data class GamePrefs(
    val hiddenWord: String,
    val word: WordPrefs = WordPrefs(),
    val history: List<WordPrefs> = emptyList(),
    val lettersCount: Int,
    val guessesCount: Int = 5,
    val attempts: Int = 1,
    val keyboard : KeyboardPrefs
)
