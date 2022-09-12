package com.example.fiveletters.ui.state

import androidx.annotation.StringRes

data class DialogParams(
    val isOpened: Boolean = false,
    val dialogType: DialogType,
    @StringRes val titleId: Int? = null,
    @StringRes val confirmBtnTextId: Int,
    val closeDialogAction: () -> Unit,
    val confirmAction: (Any?) -> Unit,
)
