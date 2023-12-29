package com.example.app_bulls_and_cows.ui.model

import com.example.app_bulls_and_cows.domain.model.Game
import com.example.app_bulls_and_cows.domain.model.letter.LetterState

data class UIState(
    val game: Game,
    val dialogParams: DialogParams,
    val isInited: Boolean = false,
) {

    val isEndGame: Boolean =
        game.history.count() > game.guessesCount || game.history.lastOrNull()?.letters?.none { it.state != LetterState.CORRECT } == true
}