package com.example.fiveletters.data.model.prefs

import com.example.fiveletters.domain.model.keyboard.KeyType

data class KeyboardPrefs(
    val rows: List<RowPrefs>
)

data class RowPrefs(
    val keys: List<KeyPrefs>
)

data class KeyPrefs(
    val symbol: String,
    var isWrong: Boolean = false,
    val keyType: KeyType
)