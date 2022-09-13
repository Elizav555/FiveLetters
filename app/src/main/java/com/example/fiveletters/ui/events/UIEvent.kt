package com.example.fiveletters.ui.events

import com.example.fiveletters.domain.model.LettersCount
import com.example.fiveletters.domain.model.SettingsDialogParams
import java.util.Locale

sealed class UIEvent {
    data class LetterAddedEvent(val letter: String) : UIEvent()
    object ErasedEvent : UIEvent()
    object SubmitEvent : UIEvent()
    object NewGameStartedEvent : UIEvent()
    object OpenSettingsEvent : UIEvent()
    object HelpEvent : UIEvent()
    object ConfirmNewGameEvent : UIEvent()
    data class ApplySettingEvent(val settingsDialogParams: SettingsDialogParams?) : UIEvent()
    data class SetLocaleEvent(val locale: Locale) : UIEvent()
}