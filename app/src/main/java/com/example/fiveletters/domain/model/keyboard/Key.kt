package com.example.fiveletters.domain.model.keyboard


data class Key(
    val symbol: String,
    val keyType: KeyType,
    var isWrong: Boolean = false
)

enum class KeyType {
    DEFAULT, SUBMIT, ERASE
}