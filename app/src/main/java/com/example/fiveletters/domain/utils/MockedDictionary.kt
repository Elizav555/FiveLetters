package com.example.fiveletters.domain.utils

import com.example.fiveletters.domain.model.letter.LettersCount
import java.util.Locale

fun mockedDictionary(locale: Locale? = Locale.ENGLISH, lettersCount: LettersCount) = when (locale) {
    RUSSIAN -> getRussianDictionary(lettersCount)
    else -> getEnglishDictionary(lettersCount)
}


private fun getEnglishDictionary(lettersCount: LettersCount) = when (lettersCount) {
    LettersCount.FIVE -> listOf(
        "adult",
        "agent",
        "alpha",
        "agile",
        "alien",
        "mercy",
        "music",
        "sound",
        "zebra",
        "react",
        "giant",
        "youth",
        "yacht",
        "heart",
        "pizza",
        "water",
        "happy",
        "month",
        "angel",
        "death",
        "green",
        "music",
        "party",
        "piano",
        "mouth",
        "woman",
        "sugar",
        "amber",
        "dream",
        "apple",
        "laugh",
        "tiger",
        "faith",
        "earth",
        "river",
        "money",
        "peace"
    )
    LettersCount.SIX -> listOf(
        "orange",
        "family",
        "silver",
        "future",
        "banana",
        "office",
        "nature",
        "eleven",
        "animal",
        "snitch",
        "poetry",
        "potato",
        "circle",
        "school",
        "breath",
        "moment",
        "person",
        "energy",
        "sister",
        "change",
        "monkey",
        "system",
        "secret",
        "pirate",
        "turtle",
        "mother",
        "winter",
        "bucket"
    )
    LettersCount.SEVEN -> listOf(
        "perfect",
        "country",
        "pumpkin",
        "special",
        "freedom",
        "picture",
        "husband",
        "monster",
        "sixteen",
        "morning",
        "journey",
        "history",
        "dolphin",
        "teacher",
        "kitchen",
        "holiday",
        "justice",
        "diamond",
        "courage",
        "silence",
        "someone",
        "science",
        "revenge",
        "harmony",
        "problem",
        "penguin",
        "blanket",
        "student",
        "mercury",
        "initial",
        "mystery",
        "natural",
        "kingdom",
        "captain",
        "message",
        "failure"
    )
}


private fun getRussianDictionary(lettersCount: LettersCount) = when (lettersCount) {
    LettersCount.FIVE -> listOf(
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
        "??????????",
    )
    LettersCount.SIX -> listOf(
        "????????????",
        "????????????",
        "????????????",
        "????????????",
        "????????????",
        "????????????",
        "????????????",
        "????????????",
        "????????????",
        "????????????",
        "????????????",
        "????????????",
        "????????????",
        "????????????",
        "????????????",
        "????????????",
        "????????????",
        "????????????",
        "??????????????",
        "????????????",
        "????????????",
        "????????????",
        "????????????",
        "????????????",
        "????????????",
        "????????????",
        "????????????",
        "????????????",
        "????????????"
    )
    LettersCount.SEVEN -> listOf(
        "??????????????",
        "??????????????",
        "??????????????",
        "??????????????",
        "??????????????",
        "??????????????",
        "??????????????",
        "??????????????",
        "??????????????",
        "??????????????",
        "??????????????",
        "??????????????",
        "??????????????",
        "??????????????",
        "??????????????",
        "??????????????",
        "??????????????"
    )
}