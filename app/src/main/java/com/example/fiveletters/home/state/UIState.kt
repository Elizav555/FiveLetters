package com.example.fiveletters.home.state

import com.example.fiveletters.home.utils.Letter
import com.example.fiveletters.home.utils.mockedDictionary

data class UIState(
    val hiddenWord: List<String> = mockedDictionary.random().toList()
        .map { it.toString().uppercase() },
    val word: List<Letter> = emptyList(),
    val history: List<List<Letter>> = emptyList(),
    val lettersCount: Int = 5,
    val guessesCount: Int = 6,
    val attempts: Int = 1,
)