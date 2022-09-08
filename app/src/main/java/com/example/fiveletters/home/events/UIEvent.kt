package com.example.fiveletters.home.events

sealed class UIEvent {
    data class LetterAddedEvent(val letter: String) : UIEvent()
    object ErasedEvent : UIEvent()
    object SubmitEvent : UIEvent()
}