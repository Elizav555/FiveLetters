package com.example.app_bulls_and_cows.data.mappers

import com.example.app_bulls_and_cows.data.model.prefs.GamePrefs
import com.example.app_bulls_and_cows.data.model.prefs.KeyPrefs
import com.example.app_bulls_and_cows.data.model.prefs.KeyboardPrefs
import com.example.app_bulls_and_cows.data.model.prefs.LetterPrefs
import com.example.app_bulls_and_cows.data.model.prefs.RowPrefs
import com.example.app_bulls_and_cows.data.model.prefs.WordPrefs
import com.example.app_bulls_and_cows.domain.model.Game
import com.example.app_bulls_and_cows.domain.model.Word
import com.example.app_bulls_and_cows.domain.model.keyboard.Key
import com.example.app_bulls_and_cows.domain.model.keyboard.Keyboard
import com.example.app_bulls_and_cows.domain.model.keyboard.Row
import com.example.app_bulls_and_cows.domain.model.letter.Letter

object GameDomainMapper {
    fun Game.toPrefs() = GamePrefs(
        hiddenWord = hiddenWord,
        word = word.toPrefs(),
        history = history.map { it.toPrefs() },
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
        symbol = symbol, isWrong = isWrong, keyType = keyType
    )
}