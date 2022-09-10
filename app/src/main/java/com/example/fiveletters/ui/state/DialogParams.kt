package com.example.fiveletters.ui.state

import androidx.annotation.StringRes

data class DialogParams(
    val isOpened: Boolean = false,
    @StringRes val titleId: Int? = null,
    @StringRes val textId: Int? = null,
    val confirmAction: () -> Unit
)
