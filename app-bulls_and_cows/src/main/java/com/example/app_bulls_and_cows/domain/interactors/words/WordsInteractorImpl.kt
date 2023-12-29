package com.example.app_bulls_and_cows.domain.interactors.words

import java.util.Random
import javax.inject.Inject
import kotlin.Result.Companion

class WordsInteractorImpl @Inject constructor() : WordsInteractor {

    override fun getRandomWord(): String {
        val digits = (0..9).toMutableList()
        val uniqueNumber = StringBuilder()

        // Первая цифра должна быть от 1 до 9, чтобы обеспечить, что число будет четырехзначным
        uniqueNumber.append((1..9).random())

        // Удаление уже использованной цифры, чтобы исключить повторы
        digits.remove(uniqueNumber.toString().toInt())

        // Добавление оставшихся трех уникальных цифр
        for (i in 1 until 4) {
            val digit = digits.random()
            uniqueNumber.append(digit)
            digits.remove(digit)
        }

        return uniqueNumber.toString()
    }
}