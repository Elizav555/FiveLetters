package com.example.fiveletters.ui.events

sealed class GuessEvent {
    object RightGuess : GuessEvent()
    object LastGuess : GuessEvent()
}
