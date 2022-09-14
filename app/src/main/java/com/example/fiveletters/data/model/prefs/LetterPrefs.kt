package com.example.fiveletters.data.model.prefs

import com.example.fiveletters.domain.model.letter.LetterState
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LetterPrefs(
    @SerialName("symbol") val symbol: String,
    @SerialName("state") var state: LetterState
)