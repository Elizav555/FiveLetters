package com.example.fiveletters.domain.model.letter

data class Letter(
    val symbol: String,
    val state: LetterState = LetterState.DEFAULT
)
