package com.example.fiveletters.domain.model

data class Letter(
    val symbol: String,
    var state: LetterState = LetterState.DEFAULT
)
