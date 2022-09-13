package com.example.fiveletters.ui.state

sealed class DialogType {
    data class TextDialog(
        val text: String?=null,
        val textParams: List<String> = emptyList()
    ) : DialogType()

    object HelpDialog : DialogType()

    object SettingsDialog : DialogType()
}