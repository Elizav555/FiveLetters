package com.example.fiveletters.domain.utils

import com.example.fiveletters.domain.model.Key
import com.example.fiveletters.domain.model.KeyClick
import com.example.fiveletters.domain.model.Keyboard
import com.example.fiveletters.domain.model.Row

fun myKeyboardKeys(defaultKeyClick: KeyClick, eraseKeyClick: KeyClick, submitKeyClick: KeyClick) =
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