package com.example.fiveletters.data.model

data class KeyboardForPrefs(
    val rows: List<RowForPrefs>
)

data class RowForPrefs(
    val keys: List<KeyForPrefs>
)

data class KeyForPrefs(
    val symbol: String,
    var isWrong: Boolean = false
)