package com.example.app_bulls_and_cows.ui.events

import com.example.app_bulls_and_cows.domain.model.SettingsDialogParams
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