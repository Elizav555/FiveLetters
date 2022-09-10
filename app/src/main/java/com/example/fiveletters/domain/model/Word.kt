package com.example.fiveletters.domain.model

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
