package com.example.fiveletters.ui.state

data class DialogParams(
    val isOpened: Boolean = false,
    val dialogType: DialogType,
    val closeDialogAction: () -> Unit
)
