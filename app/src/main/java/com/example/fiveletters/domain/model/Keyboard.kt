package com.example.fiveletters.domain.model

data class Keyboard(
    val rows: List<Row> = emptyList()
)

data class Row(
    val keys: List<Key> = emptyList()
)
