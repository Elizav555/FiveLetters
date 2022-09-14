package com.example.fiveletters.data.model.prefs

import com.example.fiveletters.domain.model.letter.LetterState

data class LetterPrefs(
    val symbol: String,
    var state: LetterState = LetterState.DEFAULT
)