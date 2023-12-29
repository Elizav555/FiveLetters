package com.example.app_bulls_and_cows.ui.model

sealed class DialogType {
    data class TextDialog(
        val textParams: List<String> = emptyList(),
        val textDialogType: TextDialogType
    ) : DialogType()

    object HelpDialog : DialogType()

    object SettingsDialog : DialogType()
}
