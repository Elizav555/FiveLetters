package com.example.app_bulls_and_cows.domain.model

import com.example.app_bulls_and_cows.domain.model.letter.Letter

data class Word(
    val letters: List<Letter> = emptyList()
) {
    companion object {
        fun wordFromString(letters: String): Word {
            val lettersList = mutableListOf<Letter>()
            letters.forEach { lettersList.add(Letter(symbol = it.toString().uppercase())) }
            return Word(lettersList)
        }
    }
}
