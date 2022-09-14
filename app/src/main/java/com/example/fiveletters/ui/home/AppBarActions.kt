package com.example.fiveletters.ui.home

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.example.fiveletters.ui.model.AppBarIcon

@Composable
fun AppBarActions(icons: List<AppBarIcon>) {
    icons.forEach {
        IconButton(onClick = it.onClick) {
            Icon(
                imageVector = it.icon,
                contentDescription = it.desc
            )
        }
    }
}