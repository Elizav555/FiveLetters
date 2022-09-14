package com.example.fiveletters.domain.model.letter

enum class LettersCount(val count: Int) {
    FIVE(5),
    SIX(6),
    SEVEN(7)
}

fun lettersCountFromInt(count:Int) = when(count){
    6-> LettersCount.SIX
    7-> LettersCount.SEVEN
    else-> LettersCount.FIVE
}