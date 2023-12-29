package com.example.app_bulls_and_cows.ui.model

import androidx.compose.ui.graphics.vector.ImageVector

data class AppBarIcon(
    val icon: ImageVector,
    val onClick: () -> Unit,
    val desc: String
)
