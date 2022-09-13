package com.example.fiveletters.ui.state

sealed class DialogType {
    data class TextDialog(
        val textParams: List<String> = emptyList(),
    val textDialogType: TextDialogType
    ) : DialogType()

    object HelpDialog : DialogType()

    object SettingsDialog : DialogType()
}

enum class TextDialogType{
    WON,LOST,CONFIRM
}