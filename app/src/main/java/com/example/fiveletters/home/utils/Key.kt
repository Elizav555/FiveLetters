package com.example.fiveletters.home.utils

typealias KeyClick = (String?) -> Unit

data class Key(
    val symbol: String,
    val keyClick: KeyClick
)