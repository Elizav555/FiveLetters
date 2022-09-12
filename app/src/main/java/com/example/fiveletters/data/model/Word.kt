package com.example.fiveletters.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Word(
    @SerialName("id") val id: Int,
    @SerialName("word") val word: String,
)
