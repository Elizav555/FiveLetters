package com.example.fiveletters.home.events

sealed class UIEvent {
    data class WordChangedEvent(val word: String) : UIEvent()
    object SubmitEvent : UIEvent()
}