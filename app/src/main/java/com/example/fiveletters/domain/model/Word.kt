package com.example.fiveletters.domain.model

import com.example.fiveletters.domain.model.letter.Letter

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
