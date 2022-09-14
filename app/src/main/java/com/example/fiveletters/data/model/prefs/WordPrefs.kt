package com.example.fiveletters.data.model.prefs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WordPrefs(
    @SerialName("letters") val letters: List<LetterPrefs>
)