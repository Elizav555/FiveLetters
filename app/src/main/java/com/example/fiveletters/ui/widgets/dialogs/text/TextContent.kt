package com.example.fiveletters.ui.widgets.dialogs.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TextDialogContent(text: String, params: List<String>? = null) {
    params?.let { Text(text = text.format(*it.toTypedArray())) } ?: Text(text = text)
}