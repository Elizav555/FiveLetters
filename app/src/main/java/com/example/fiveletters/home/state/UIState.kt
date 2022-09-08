package com.example.fiveletters.home.state

import com.example.fiveletters.home.utils.Letter
import com.example.fiveletters.home.utils.mockedDictionary

data class UIState(
    val hiddenWord: String = mockedDictionary.random(),
    val word: List<Letter> = emptyList(),
    val lettersCount: Int = 5
)