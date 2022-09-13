package com.example.fiveletters.data.mappers

import com.example.fiveletters.data.model.GamePrefs
import com.example.fiveletters.data.model.KeyPrefs
import com.example.fiveletters.data.model.KeyboardPrefs
import com.example.fiveletters.data.model.LetterPrefs
import com.example.fiveletters.data.model.RowPrefs
import com.example.fiveletters.data.model.WordPrefs
import com.example.fiveletters.domain.model.Game
import com.example.fiveletters.domain.model.Key
import com.example.fiveletters.domain.model.Keyboard
import com.example.fiveletters.domain.model.Letter
import com.example.fiveletters.domain.model.Row
import com.example.fiveletters.domain.model.Word

object GameDomainMapper {
    fun Game.toPrefs() = GamePrefs(
        hiddenWord = hiddenWord,
        word = word.toPrefs(),
        history = history.map { it.toPrefs() },
        lettersCount = lettersCount.count,
        guessesCount = guessesCount,
        attempts = attempts,
        keyboard = keyboard.toPrefs()
    )

    private fun Word.toPrefs() = WordPrefs(
        letters = letters.map { it.toPrefs() }
    )

    private fun Letter.toPrefs() = LetterPrefs(
        symbol = symbol, state = state
    )

    private fun Keyboard.toPrefs() = KeyboardPrefs(
        rows = rows.map { it.toPrefs() }
    )

    private fun Row.toPrefs() = RowPrefs(
        keys = keys.map { it.toPrefs() }
    )

    private fun Key.toPrefs() = KeyPrefs(
        symbol = symbol, isWrong = isWrong
    )
}