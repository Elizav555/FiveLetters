package com.example.app_bulls_and_cows.domain.model.keyboard

data class Keyboard(
    val rows: List<Row> = emptyList()
)

data class Row(
    val keys: List<Key> = emptyList()
)
