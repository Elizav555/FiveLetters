package com.example.app_bulls_and_cows.domain.model.keyboard

data class Key(
    val symbol: String,
    val keyType: KeyType,
    var isWrong: Boolean = false
)