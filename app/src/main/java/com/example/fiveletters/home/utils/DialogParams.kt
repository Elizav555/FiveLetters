package com.example.fiveletters.home.utils

import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class DialogParams(
    val state: MutableState<Boolean> = mutableStateOf(false),
    @StringRes var titleId: Int? = null,
    @StringRes var textId: Int? = null,
    val confirmAction: () -> Unit
)
