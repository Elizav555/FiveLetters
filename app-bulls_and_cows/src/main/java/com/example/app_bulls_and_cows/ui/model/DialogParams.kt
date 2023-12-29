package com.example.app_bulls_and_cows.ui.model

data class DialogParams(
    val isOpened: Boolean = false,
    val dialogType: DialogType,
    val closeDialogAction: () -> Unit,
    val confirmAction: (Any?) -> Unit,
)
