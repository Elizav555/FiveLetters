package com.example.fiveletters.ui.model

import com.example.fiveletters.domain.model.Game
import com.example.fiveletters.domain.model.letter.LetterState

data class UIState(
    val game: Game,
    val dialogParams: DialogParams,
    val isInited: Boolean = false,
) {
    val isEndGame: Boolean =
        game.history.count() > game.guessesCount || game.history.lastOrNull()?.letters?.none { it.state != LetterState.CORRECT } == true
}