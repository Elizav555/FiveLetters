package com.example.fiveletters.ui.state

import androidx.compose.ui.graphics.vector.ImageVector

data class AppBarIcon(
    val icon: ImageVector,
    val onClick: () -> Unit,
    val desc: String
)
