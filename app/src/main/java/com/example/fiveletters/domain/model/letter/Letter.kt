package com.example.fiveletters.domain.model.letter

data class Letter(
    val symbol: String,
    var state: LetterState = LetterState.DEFAULT
)
