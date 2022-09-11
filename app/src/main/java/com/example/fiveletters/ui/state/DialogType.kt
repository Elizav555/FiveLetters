package com.example.fiveletters.ui.state

import androidx.annotation.StringRes

sealed class DialogType(
    @StringRes open val titleId: Int? = null,
    open val confirmAction: Function<Unit>,
    @StringRes open val confirmBtnTextId: Int,
) {
    data class TextDialog(
        @StringRes override val titleId: Int? = null,
        @StringRes val textId: Int,
        override val confirmAction: () -> Unit,
        @StringRes override val confirmBtnTextId: Int
    ) :
        DialogType(titleId, confirmAction, confirmBtnTextId)

    data class HelpDialog(
        @StringRes override val titleId: Int? = null,
        override val confirmAction: () -> Unit,
        @StringRes override val confirmBtnTextId: Int

    ) : DialogType(titleId, confirmAction, confirmBtnTextId)

    data class SettingsDialog(
        @StringRes override val titleId: Int? = null,
        override val confirmAction: (lettersCount: Int, isDarkTheme: Boolean) -> Unit,
        @StringRes override val confirmBtnTextId: Int
    ) : DialogType(titleId, confirmAction, confirmBtnTextId)
}