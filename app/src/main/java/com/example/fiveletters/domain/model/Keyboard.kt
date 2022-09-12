package com.example.fiveletters.domain.model

data class Keyboard(
    val rows: List<Row>
)

data class Row(
    val keys: List<Key>
)
