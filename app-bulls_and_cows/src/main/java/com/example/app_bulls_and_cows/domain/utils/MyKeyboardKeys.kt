package com.example.app_bulls_and_cows.domain.utils

import com.example.app_bulls_and_cows.domain.model.keyboard.Key
import com.example.app_bulls_and_cows.domain.model.keyboard.KeyType
import com.example.app_bulls_and_cows.domain.model.keyboard.Keyboard
import com.example.app_bulls_and_cows.domain.model.keyboard.Row

fun myKeyboardKeys() = Keyboard(
    rows = listOf(
        Row(
            keys = listOf(
                Key("1", KeyType.DEFAULT),
                Key("2", KeyType.DEFAULT),
                Key("3", KeyType.DEFAULT),
                Key("4", KeyType.DEFAULT),
                Key("5", KeyType.DEFAULT),
                Key("⌫", KeyType.ERASE),
            )
        ),
        Row(
            keys = listOf(
                Key("6", KeyType.DEFAULT),
                Key("7", KeyType.DEFAULT),
                Key("8", KeyType.DEFAULT),
                Key("9", KeyType.DEFAULT),
                Key("0", KeyType.DEFAULT),
                Key("✓", KeyType.SUBMIT),
            )
        ),
    )
)
