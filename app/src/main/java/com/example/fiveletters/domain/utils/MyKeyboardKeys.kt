package com.example.fiveletters.domain.utils

import com.example.fiveletters.domain.model.keyboard.Key
import com.example.fiveletters.domain.model.keyboard.KeyType
import com.example.fiveletters.domain.model.keyboard.Keyboard
import com.example.fiveletters.domain.model.keyboard.Row
import java.util.Locale

fun myKeyboardKeys(locale: Locale? = Locale.ENGLISH) = when (locale) {
    RUSSIAN -> getRussianKeys()
    else -> getEnglishKeys()
}

private fun getEnglishKeys() =
    Keyboard(
        rows = listOf(
            Row(
                keys = listOf(
                    Key("Q", KeyType.DEFAULT),
                    Key("W", KeyType.DEFAULT),
                    Key("E", KeyType.DEFAULT),
                    Key("R", KeyType.DEFAULT),
                    Key("T", KeyType.DEFAULT),
                    Key("Y", KeyType.DEFAULT),
                    Key("U", KeyType.DEFAULT),
                    Key("I", KeyType.DEFAULT),
                    Key("O", KeyType.DEFAULT),
                    Key("P", KeyType.DEFAULT),
                )
            ),
            Row(
                keys = listOf(
                    Key("A", KeyType.DEFAULT),
                    Key("S", KeyType.DEFAULT),
                    Key("D", KeyType.DEFAULT),
                    Key("F", KeyType.DEFAULT),
                    Key("G", KeyType.DEFAULT),
                    Key("H", KeyType.DEFAULT),
                    Key("J", KeyType.DEFAULT),
                    Key("K", KeyType.DEFAULT),
                    Key("L", KeyType.DEFAULT),
                    Key("⌫", KeyType.ERASE),
                )
            ),
            Row(
                keys = listOf(
                    Key("Z", KeyType.DEFAULT),
                    Key("X", KeyType.DEFAULT),
                    Key("C", KeyType.DEFAULT),
                    Key("V", KeyType.DEFAULT),
                    Key("B", KeyType.DEFAULT),
                    Key("N", KeyType.DEFAULT),
                    Key("M", KeyType.DEFAULT),
                    Key("✓", KeyType.SUBMIT),
                )
            ),
        )
    )

private fun getRussianKeys() =
    Keyboard(
        rows = listOf(
            Row(
                keys = listOf(
                    Key("Й", KeyType.DEFAULT),
                    Key("Ц", KeyType.DEFAULT),
                    Key("У", KeyType.DEFAULT),
                    Key("К", KeyType.DEFAULT),
                    Key("Е", KeyType.DEFAULT),
                    Key("Н", KeyType.DEFAULT),
                    Key("Г", KeyType.DEFAULT),
                    Key("Ш", KeyType.DEFAULT),
                    Key("Щ", KeyType.DEFAULT),
                    Key("З", KeyType.DEFAULT),
                    Key("Х", KeyType.DEFAULT),
                    Key("Ъ", KeyType.DEFAULT),
                )
            ),
            Row(
                keys = listOf(
                    Key("Ф", KeyType.DEFAULT),
                    Key("Ы", KeyType.DEFAULT),
                    Key("В", KeyType.DEFAULT),
                    Key("А", KeyType.DEFAULT),
                    Key("П", KeyType.DEFAULT),
                    Key("Р", KeyType.DEFAULT),
                    Key("О", KeyType.DEFAULT),
                    Key("Л", KeyType.DEFAULT),
                    Key("Д", KeyType.DEFAULT),
                    Key("Ж", KeyType.DEFAULT),
                    Key("Э", KeyType.DEFAULT),
                    Key("⌫", KeyType.ERASE),
                )
            ),
            Row(
                keys = listOf(
                    Key("Я", KeyType.DEFAULT),
                    Key("Ч", KeyType.DEFAULT),
                    Key("С", KeyType.DEFAULT),
                    Key("М", KeyType.DEFAULT),
                    Key("И", KeyType.DEFAULT),
                    Key("Т", KeyType.DEFAULT),
                    Key("Ь", KeyType.DEFAULT),
                    Key("Б", KeyType.DEFAULT),
                    Key("Ю", KeyType.DEFAULT),
                    Key("✓", KeyType.SUBMIT),
                )
            ),
        )
    )
