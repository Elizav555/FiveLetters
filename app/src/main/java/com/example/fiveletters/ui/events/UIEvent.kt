package com.example.fiveletters.ui.events

sealed class UIEvent {
    data class LetterAddedEvent(val letter: String) : UIEvent()
    object ErasedEvent : UIEvent()
    object SubmitEvent : UIEvent()
    object NewGameStartedEvent : UIEvent()
    object OpenSettingsEvent : UIEvent()
    object HelpEvent : UIEvent()
    object ConfirmNewGameEvent : UIEvent()
    data class ApplySettingEvent(val lettersCount: Int?) : UIEvent()
}