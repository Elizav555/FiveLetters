package com.example.fiveletters.ui.state

import com.example.fiveletters.domain.model.Game

data class UIState(
    val game: Game,
    val dialogParams: DialogParams
)