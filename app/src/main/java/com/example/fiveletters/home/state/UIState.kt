package com.example.fiveletters.home.state

data class UIState(
    val word: List<String> = emptyList(),
    val lettersCount: Int = 5
)