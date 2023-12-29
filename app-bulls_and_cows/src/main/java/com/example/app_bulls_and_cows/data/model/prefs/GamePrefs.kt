package com.example.app_bulls_and_cows.data.model.prefs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GamePrefs(
    @SerialName("hiddenWord") val hiddenWord: String,
    @SerialName("word") val word: WordPrefs,
    @SerialName("history") val history: List<WordPrefs>,
    @SerialName("guessesCount") val guessesCount: Int,
    @SerialName("attempts") val attempts: Int,
    @SerialName("keyboard") val keyboard: KeyboardPrefs
)
