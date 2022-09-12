package com.example.fiveletters.ui.state

import androidx.annotation.StringRes

sealed class DialogType {
    data class TextDialog(
        @StringRes val textId: Int,
    ) : DialogType()

    object HelpDialog : DialogType()

    object SettingsDialog : DialogType()
}