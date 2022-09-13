package com.example.fiveletters.data.mappers

import com.example.fiveletters.data.model.GamePrefs
import com.example.fiveletters.data.model.KeyPrefs
import com.example.fiveletters.data.model.KeyboardPrefs
import com.example.fiveletters.data.model.LetterPrefs
import com.example.fiveletters.data.model.RowPrefs
import com.example.fiveletters.data.model.WordPrefs
import com.example.fiveletters.domain.model.Game
import com.example.fiveletters.domain.model.Key
import com.example.fiveletters.domain.model.KeyClick
import com.example.fiveletters.domain.model.Keyboard
import com.example.fiveletters.domain.model.Letter
import com.example.fiveletters.domain.model.Row
import com.example.fiveletters.domain.model.Word

object GamePrefsMapper {
    fun GamePrefs.toDomain(keyClicks: List<List<KeyClick>>?) = Game(
        hiddenWord = hiddenWord,
        word = word.toDomain(),
        history = history.map { it.toDomain() },
        lettersCount = lettersCount,
        guessesCount = guessesCount,
        attempts = attempts,
        keyboard = keyClicks?.let { keyboard.toDomain(it) }?:Keyboard()
    )

    private fun WordPrefs.toDomain() = Word(
        letters = letters.map { it.toDomain() }
    )

    private fun LetterPrefs.toDomain() = Letter(
        symbol = symbol, state = state
    )

    private fun KeyboardPrefs.toDomain(keyClicks: List<List<KeyClick>>) = Keyboard(
        rows = rows.mapIndexed { index, row -> row.toDomain(keyClicks[index]) }
    )

    private fun RowPrefs.toDomain(keyClicks: List<KeyClick>) = Row(
        keys = keys.mapIndexed { index, key -> key.toDomain(keyClicks[index]) }
    )

    private fun KeyPrefs.toDomain(keyClick: KeyClick) = Key(
        symbol = symbol, isWrong = isWrong, keyClick = keyClick
    )
}