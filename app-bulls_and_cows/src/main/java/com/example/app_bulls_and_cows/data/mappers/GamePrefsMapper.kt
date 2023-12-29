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

object GamePrefsMapper {
    fun GamePrefs.toDomain() = Game(
        hiddenWord = hiddenWord,
        word = word.toDomain(),
        history = history.map { it.toDomain() },
        guessesCount = guessesCount,
        attempts = attempts,
        keyboard = keyboard.toDomain()
    )

    private fun WordPrefs.toDomain() = Word(
        letters = letters.map { it.toDomain() }
    )

    private fun LetterPrefs.toDomain() = Letter(
        symbol = symbol, state = state
    )

    private fun KeyboardPrefs.toDomain() = Keyboard(
        rows = rows.map { it.toDomain() }
    )

    private fun RowPrefs.toDomain() = Row(
        keys = keys.map { it.toDomain() }
    )

    private fun KeyPrefs.toDomain() = Key(
        symbol = symbol, isWrong = isWrong, keyType = keyType
    )
}