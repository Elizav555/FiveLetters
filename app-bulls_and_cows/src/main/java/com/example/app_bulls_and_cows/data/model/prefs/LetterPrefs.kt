package com.example.app_bulls_and_cows.data.model.prefs

import com.example.app_bulls_and_cows.domain.model.letter.LetterState
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LetterPrefs(
    @SerialName("symbol") val symbol: String,
    @SerialName("state") var state: LetterState
)