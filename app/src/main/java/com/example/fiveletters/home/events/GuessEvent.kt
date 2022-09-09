package com.example.fiveletters.home.events

sealed class GuessEvent {
    object RightGuess : GuessEvent()
    object WrongGuess : GuessEvent()
    object LastGuess : GuessEvent()
}
