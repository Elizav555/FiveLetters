package com.example.fiveletters.data.mappers

import com.example.fiveletters.data.model.prefs.GamePrefs
import com.example.fiveletters.data.model.prefs.KeyPrefs
import com.example.fiveletters.data.model.prefs.KeyboardPrefs
import com.example.fiveletters.data.model.prefs.LetterPrefs
import com.example.fiveletters.data.model.prefs.RowPrefs
import com.example.fiveletters.data.model.prefs.WordPrefs
import com.example.fiveletters.domain.model.Game
import com.example.fiveletters.domain.model.keyboard.Key
import com.example.fiveletters.domain.model.keyboard.Keyboard
import com.example.fiveletters.domain.model.letter.Letter
import com.example.fiveletters.domain.model.keyboard.Row
import com.example.fiveletters.domain.model.Word
import com.example.fiveletters.domain.model.letter.lettersCountFromInt

object GamePrefsMapper {
    fun GamePrefs.toDomain() = Game(
        hiddenWord = hiddenWord,
        word = word.toDomain(),
        history = history.map { it.toDomain() },
        lettersCount = lettersCountFromInt(lettersCount),
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