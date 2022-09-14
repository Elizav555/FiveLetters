package com.example.fiveletters.ui.model

data class DialogParams(
    val isOpened: Boolean = false,
    val dialogType: DialogType,
    val closeDialogAction: () -> Unit,
    val confirmAction: (Any?) -> Unit,
)
