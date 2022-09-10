package com.example.fiveletters.data.model

import com.example.fiveletters.domain.model.Letter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.example.fiveletters.domain.model.Word as WordDomain

@Serializable
data class Word(
    @SerialName("id") val id: Int,
    @SerialName("word") val word: String,
) {
    fun mapToDomain() = WordDomain(
        letters = mapStringToLetters(word)
    )

    private fun mapStringToLetters(letters: String): MutableList<Letter> {
        val lettersList = mutableListOf<Letter>()
        letters.forEach { lettersList.add(Letter(symbol = it.toString())) }
        return lettersList
    }
}
