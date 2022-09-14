package com.example.fiveletters.ui.model

sealed class DialogType {
    data class TextDialog(
        val textParams: List<String> = emptyList(),
        val textDialogType: TextDialogType
    ) : DialogType()

    object HelpDialog : DialogType()

    object SettingsDialog : DialogType()
}
