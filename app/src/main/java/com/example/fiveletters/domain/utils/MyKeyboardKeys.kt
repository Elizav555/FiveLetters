package com.example.fiveletters.domain.utils

import com.example.fiveletters.domain.model.Key
import com.example.fiveletters.domain.model.KeyClick
import com.example.fiveletters.domain.model.Keyboard
import com.example.fiveletters.domain.model.Row
import com.example.fiveletters.ui.res.values.RUSSIAN
import java.util.Locale

object MockedKeyboard {
    fun myKeyClicks(
        locale: Locale? = Locale.ENGLISH,
        defaultKeyClick: KeyClick,
        eraseKeyClick: KeyClick,
        submitKeyClick: KeyClick
    ) = when (locale) {
        RUSSIAN -> listOf(
            List(12) { defaultKeyClick },
            List(11) { defaultKeyClick },
            List(11) { index ->
                when (index) {
                    0 -> submitKeyClick
                    10 -> eraseKeyClick
                    else -> defaultKeyClick
                }
            },
        )
        else -> listOf(
            List(10) { defaultKeyClick },
            List(9) { defaultKeyClick },
            List(9) { index ->
                when (index) {
                    0 -> submitKeyClick
                    8 -> eraseKeyClick
                    else -> defaultKeyClick
                }
            },
        )
    }

    fun myKeyboardKeys(
        locale: Locale? = Locale.ENGLISH,
        defaultKeyClick: KeyClick,
        eraseKeyClick: KeyClick,
        submitKeyClick: KeyClick
    ) = when (locale) {
        RUSSIAN -> getRussianKeys(
            defaultKeyClick,
            eraseKeyClick,
            submitKeyClick
        )
        else -> getEnglishKeys(
            defaultKeyClick,
            eraseKeyClick,
            submitKeyClick
        )
    }

    private fun getEnglishKeys(
        defaultKeyClick: KeyClick,
        eraseKeyClick: KeyClick,
        submitKeyClick: KeyClick
    ) =
        Keyboard(
            rows = listOf(
                Row(
                    keys = listOf(
                        Key("Q", defaultKeyClick),
                        Key("W", defaultKeyClick),
                        Key("E", defaultKeyClick),
                        Key("R", defaultKeyClick),
                        Key("T", defaultKeyClick),
                        Key("Y", defaultKeyClick),
                        Key("U", defaultKeyClick),
                        Key("I", defaultKeyClick),
                        Key("O", defaultKeyClick),
                        Key("P", defaultKeyClick),
                    )
                ),
                Row(
                    keys = listOf(
                        Key("A", defaultKeyClick),
                        Key("S", defaultKeyClick),
                        Key("D", defaultKeyClick),
                        Key("F", defaultKeyClick),
                        Key("G", defaultKeyClick),
                        Key("H", defaultKeyClick),
                        Key("J", defaultKeyClick),
                        Key("K", defaultKeyClick),
                        Key("L", defaultKeyClick),
                    )
                ),
                Row(
                    keys = listOf(
                        Key("⎆", submitKeyClick),
                        Key("Z", defaultKeyClick),
                        Key("X", defaultKeyClick),
                        Key("C", defaultKeyClick),
                        Key("V", defaultKeyClick),
                        Key("B", defaultKeyClick),
                        Key("N", defaultKeyClick),
                        Key("M", defaultKeyClick),
                        Key("⌫", eraseKeyClick),
                    )
                ),
            )
        )

    private fun getRussianKeys(
        defaultKeyClick: KeyClick,
        eraseKeyClick: KeyClick,
        submitKeyClick: KeyClick
    ) =
        Keyboard(
            rows = listOf(
                Row(
                    keys = listOf(
                        Key("Й", defaultKeyClick),
                        Key("Ц", defaultKeyClick),
                        Key("У", defaultKeyClick),
                        Key("К", defaultKeyClick),
                        Key("Е", defaultKeyClick),
                        Key("Н", defaultKeyClick),
                        Key("Г", defaultKeyClick),
                        Key("Ш", defaultKeyClick),
                        Key("Щ", defaultKeyClick),
                        Key("З", defaultKeyClick),
                        Key("Х", defaultKeyClick),
                        Key("Ъ", defaultKeyClick),
                    )
                ),
                Row(
                    keys = listOf(
                        Key("Ф", defaultKeyClick),
                        Key("Ы", defaultKeyClick),
                        Key("В", defaultKeyClick),
                        Key("А", defaultKeyClick),
                        Key("П", defaultKeyClick),
                        Key("Р", defaultKeyClick),
                        Key("О", defaultKeyClick),
                        Key("Л", defaultKeyClick),
                        Key("Д", defaultKeyClick),
                        Key("Ж", defaultKeyClick),
                        Key("Э", defaultKeyClick),
                    )
                ),
                Row(
                    keys = listOf(
                        Key("⎆", submitKeyClick),
                        Key("Я", defaultKeyClick),
                        Key("Ч", defaultKeyClick),
                        Key("С", defaultKeyClick),
                        Key("М", defaultKeyClick),
                        Key("И", defaultKeyClick),
                        Key("Т", defaultKeyClick),
                        Key("Ь", defaultKeyClick),
                        Key("Б", defaultKeyClick),
                        Key("Ю", defaultKeyClick),
                        Key("⌫", eraseKeyClick),
                    )
                ),
            )
        )
}