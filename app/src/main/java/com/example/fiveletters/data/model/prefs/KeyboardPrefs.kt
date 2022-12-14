package com.example.fiveletters.data.model.prefs

import com.example.fiveletters.domain.model.keyboard.KeyType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KeyboardPrefs(
    @SerialName("rows") val rows: List<RowPrefs>
)

@Serializable
data class RowPrefs(
    @SerialName("keys") val keys: List<KeyPrefs>
)

@Serializable
data class KeyPrefs(
    @SerialName("symbol") val symbol: String,
    @SerialName("isWrong") var isWrong: Boolean,
    @SerialName("keyType") val keyType: KeyType
)