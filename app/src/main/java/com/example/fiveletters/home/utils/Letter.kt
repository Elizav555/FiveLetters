package com.example.fiveletters.home.utils

data class Letter(
    val symbol: String,
    var state: LetterState = LetterState.DEFAULT
)

enum class LetterState {
    DEFAULT, CORRECT, WRONG_POSITION, WRONG
}