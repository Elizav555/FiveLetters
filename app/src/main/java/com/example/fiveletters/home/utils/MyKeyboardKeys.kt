package com.example.fiveletters.home.utils

fun myKeyboardKeys(defaultKeyClick: KeyClick, eraseKeyClick: KeyClick, submitKeyClick: KeyClick) =
    listOf(
        listOf(
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
        ),
        listOf(
            Key("A", defaultKeyClick),
            Key("S", defaultKeyClick),
            Key("D", defaultKeyClick),
            Key("F", defaultKeyClick),
            Key("G", defaultKeyClick),
            Key("H", defaultKeyClick),
            Key("J", defaultKeyClick),
            Key("K", defaultKeyClick),
            Key("L", defaultKeyClick),
        ),
        listOf(
            Key("⎆", submitKeyClick),
            Key("Z", defaultKeyClick),
            Key("X", defaultKeyClick),
            Key("C", defaultKeyClick),
            Key("V", defaultKeyClick),
            Key("B", defaultKeyClick),
            Key("N", defaultKeyClick),
            Key("M", defaultKeyClick),
            Key("⌫", eraseKeyClick),
        ),
    )