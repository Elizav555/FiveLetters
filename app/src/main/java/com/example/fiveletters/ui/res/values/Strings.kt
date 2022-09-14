package com.example.fiveletters.ui.res.values

import NonTranslatable
import Translatable
import com.example.fiveletters.domain.utils.RUSSIAN
import java.util.Locale
import registerSupportedLocales

val supportedLocalesNow = registerSupportedLocales(RUSSIAN)

val appName = NonTranslatable("FiveLetters")

val newGame = Translatable(
    "Start New Game",
    hashMapOf(
        RUSSIAN to "Начать новую игру",
    )
)

val dialogWinTitle = Translatable(
    "Congrats! You won",
    hashMapOf(
        RUSSIAN to "Поздравляем! Вы победили",
    )
)

val dialogWinText = Translatable(
    "Let\'s start new game",
    hashMapOf(
        RUSSIAN to "Давайте начнем новую игру",
    )
)

val dialogLostTitle = Translatable(
    "Oops! You lost",
    hashMapOf(
        RUSSIAN to "Упс! Вы проиграли",
    )
)

val dialogLostText = Translatable(
    "Let\'s try again in new game.\nHidden word was %1\$s",
    hashMapOf(
        RUSSIAN to "Давайте попробуем еще раз в новой игре.\nЗагаданное слово было %1\$s",
    )
)

val dialogConfirmTitle = Translatable(
    "Are you sure?",
    hashMapOf(
        RUSSIAN to "Вы уверены?",
    )
)

val dialogConfirmText = Translatable(
    "Your current game will be lost",
    hashMapOf(
        RUSSIAN to "Ваша текущая игра будет сброшена",
    )
)

val dialogHelpTitle = Translatable(
    "Helpful Info",
    hashMapOf(
        RUSSIAN to "Полезная информация",
    )
)

val dialogSettingsTitle = Translatable(
    "Settings",
    hashMapOf(
        RUSSIAN to "Настройки",
    )
)

val close = Translatable(
    "Close",
    hashMapOf(
        RUSSIAN to "Закрыть",
    )
)

val cancel = Translatable(
    "Cancel",
    hashMapOf(
        RUSSIAN to "Отмена",
    )
)

val gotIt = Translatable(
    "Got It",
    hashMapOf(
        RUSSIAN to "Понятно",
    )
)

val apply = Translatable(
    "Apply",
    hashMapOf(
        RUSSIAN to "Применить",
    )
)

val helpCorrect = Translatable(
    "This color indicates that this letter is guessed correctly",
    hashMapOf(
        RUSSIAN to "Этот цвет показывает, что эта буква отгадана правильно",
    )
)

val helpWrongPosition = Translatable(
    "This color indicates that this letter is in hidden word, but at different position",
    hashMapOf(
        RUSSIAN to "Этот цвет показывает, что эта буква находится в загаданном слове, но на другой позиции",
    )
)


val helpWrong = Translatable(
    "This color indicates that this letter is not in hidden word",
    hashMapOf(
        RUSSIAN to "Этот цвет показывает, что этой буквы нет в загаданном слове",
    )
)

val helpText = Translatable(
    "You have five attempts to guess the hidden word. Submit and erase buttons are on keyboard",
    hashMapOf(
        RUSSIAN to "У вас есть пять попыток отгадать слово. Кнопки ввести и стереть находятся на клавиатуре",
    )
)

val chooseLettersCount = Translatable(
    "Letters count :",
    hashMapOf(
        RUSSIAN to "Число букв :",
    )
)

val chooseLocale = Translatable(
    "Locale :",
    hashMapOf(
        RUSSIAN to "Язык :",
    )
)

val isDarkMode = Translatable(
    "Dark mode :",
    hashMapOf(
        RUSSIAN to "Темный режим :",
    )
)