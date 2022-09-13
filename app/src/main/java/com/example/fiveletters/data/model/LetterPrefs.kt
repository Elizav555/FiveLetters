package com.example.fiveletters.data.model

import com.example.fiveletters.domain.model.LetterState

data class LetterPrefs(
    val symbol: String,
    var state: LetterState = LetterState.DEFAULT
)