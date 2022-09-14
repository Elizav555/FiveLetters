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
        " agent",
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
        "парус",
        "мачта",
        "мечта",
        "ведро",
        "пакет",
        "живот",
        "пятак",
        "майор",
        "время",
        "ветер",
        "малыш",
        "почта",
        "пожар",
        "огонь",
        "метро",
        "плечо",
        "разум",
        "волос",
        "голос",
        "гольф",
        "волан",
        "балон",
        "батон",
        "питон",
        "бровь",
        "синяк",
        "соска",
        "смесь",
        "водка",
        "лодка",
        "будка",
        "порог",
        "короб",
        "штора",
        "вилка",
        "ложка",
        "кошка",
        "миска",
        "плита",
        "набор",
        "выбор",
        "дозор",
        "лазер",
        "козел",
        "баран",
        "варан",
        "драма",
        "катер",
        "рупор",
        "стезя",
        "поход",
        "доход",
        "завод",
        "проба",
        "сдоба",
        "булка",
        "пирог",
        "пицца",
        "сахар",
        "какао",
        "тупик",
        "порка",
        "карта",
        "догма",
        "барак",
        "метан",
        "банка",
        "рюмка",
        "книга",
        "зебра",
        "носок",
        "рубин",
        "пачка",
        "рубль",
        "вклад",
        "паром",
        "лилия",
        "сосна",
        "круиз",
        "тромб",
        "брошь",
        "блеск",
        "город",
        "народ",
        "парик",
        "скоба",
        "мираж",
        "сумка",
        "сетка",
        "кость",
        "гарем",
        "полет",
        "кубок",
        "белок",
        "леска",
        "порез",
        "поезд",
        "багет",
        "песок",
        "голод",
        "холод",
        "моток",
        "каток",
        "чулок",
        "весна",
        "солод",
        "купаж",
        "трико",
        "грязь",
        "балет",
        "букет",
        "салат",
        "метла",
        "табло",
        "пчела",
        "зефир",
        "кефир",
        "лимон",
        "уксус",
        "рукав",
        "халат",
        "мотор",
        "забор",
        "базар",
        "гелий",
        "гений",
        "актер",
        "потоп",
        "ванна",
        "комод",
        "крыша",
        "лапша",
        "перец",
        "кабан",
        "фазан",
        "тубус",
        "зомби",
        "акула",
        "тепло",
        "бетон",
        "сушка",
        "насос",
        "парад",
        "магия",
        "почка",
        "репка",
        "редис",
        "тыква",
        "гость",
        "косяк",
        "банан",
        "позор",
        "ликер",
        "виски",
        "угорь",
        "окунь",
        "омуль",
        "лампа",
        "комар",
        "чехол",
        "топор",
        "буква",
        "слюна",
        "синий",
        "белый",
        "карма",
        "жираф",
        "масло",
        "кофта",
        "кепка",
        "шапка",
        "муфта",
        "олень",
        "доска",
        "парта",
        "туфля",
        "танец",
        "жених",
        "маска",
        "посол",
        "тапок",
        "трава",
        "мешок",
        "кешью",
        "ствол",
        "охота",
        "шприц",
        "сосуд",
        "принц",
        "князь",
        "дубль",
        "дупло",
        "алмаз",
        "топаз",
        "донос",
        "посох",
        "сироп",
        "ягода",
        "пушка",
        "казнь",
        "рынок",
        "мумия",
        "лента",
        "скотч",
        "клише",
        "успех",
        "линия",
        "ссора",
        "пихта",
        "школа",
        "пенал",
        "бекон",
        "канал",
        "слеза",
        "купец",
        "конец",
    )
    LettersCount.SIX -> listOf(
        "лошадь",
        "корова",
        "курица",
        "огород",
        "машина",
        "жаркое",
        "печень",
        "творог",
        "грусть",
        "январь",
        "апрель",
        "деньги",
        "валюта",
        "города",
        "аптека",
        "билеты",
        "пороша",
        "свинья",
        "бассейн",
        "цветок",
        "курага",
        "яблоко",
        "орешки",
        "курорт",
        "туризм",
        "стирка",
        "корона",
        "свитер",
        "солнце"
    )
    LettersCount.SEVEN -> listOf(
        "девушка",
        "кумушка",
        "кровать",
        "счастье",
        "выпечка",
        "болезнь",
        "артишок",
        "медведь",
        "человек",
        "игрушка",
        "катушка",
        "красота",
        "новости",
        "театрал",
        "коляска",
        "шоколад",
        "маринад"
    )
}