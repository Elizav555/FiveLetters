package com.example.fiveletters.ui.state

import androidx.annotation.StringRes

sealed class DialogType(
    @StringRes open val titleId: Int? = null,
    @StringRes open val confirmBtnTextId: Int,
) {
    data class TextDialog(
        @StringRes override val titleId: Int? = null,
        @StringRes val textId: Int,
        val confirmAction: () -> Unit,
        @StringRes override val confirmBtnTextId: Int
    ) :
        DialogType(titleId, confirmBtnTextId)

    data class HelpDialog(
        @StringRes override val titleId: Int? = null,
        val confirmAction: () -> Unit,
        @StringRes override val confirmBtnTextId: Int

    ) : DialogType(titleId, confirmBtnTextId)

    data class SettingsDialog(
        @StringRes override val titleId: Int? = null,
        val confirmAction: (lettersCount: Int, isDarkTheme: Boolean) -> Unit,
        @StringRes override val confirmBtnTextId: Int
    ) : DialogType(titleId, confirmBtnTextId)
}