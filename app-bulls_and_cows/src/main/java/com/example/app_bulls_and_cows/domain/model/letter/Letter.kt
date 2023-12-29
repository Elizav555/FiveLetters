package com.example.app_bulls_and_cows.domain.model.letter

data class Letter(
    val symbol: String,
    var state: LetterState = LetterState.DEFAULT
)
