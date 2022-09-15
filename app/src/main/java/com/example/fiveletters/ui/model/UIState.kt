package com.example.fiveletters.ui.model

import com.example.fiveletters.domain.model.Game

data class UIState(
    val game: Game,
    val dialogParams: DialogParams,
    val isInited: Boolean = false,
) {
    val isEndGame get() = game.history.count() > game.guessesCount
}