package com.example.fiveletters.data.model

data class KeyboardPrefs(
    val rows: List<RowPrefs>
)

data class RowPrefs(
    val keys: List<KeyPrefs>
)

data class KeyPrefs(
    val symbol: String,
    var isWrong: Boolean = false
)