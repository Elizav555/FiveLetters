package com.example.fiveletters.ui.model

import androidx.compose.ui.graphics.vector.ImageVector

data class AppBarIcon(
    val icon: ImageVector,
    val onClick: () -> Unit,
    val desc: String
)
