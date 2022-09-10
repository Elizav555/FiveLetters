package com.example.fiveletters.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiveletters.R
import com.example.fiveletters.domain.model.Game
import com.example.fiveletters.domain.model.Letter
import com.example.fiveletters.domain.model.LetterState
import com.example.fiveletters.domain.model.Word
import com.example.fiveletters.ui.events.UIEvent
import com.example.fiveletters.ui.state.DialogParams
import com.example.fiveletters.ui.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _uiState: MutableStateFlow<UIState> = MutableStateFlow(getInitialUIState())
    val uiState: StateFlow<UIState> = _uiState

    private fun getInitialUIState() = UIState(
        game = Game(),
        dialogParams = DialogParams(
            confirmAction = { onEvent(UIEvent.NewGameStartedEvent) }
        )
    )

    fun onEvent(event: UIEvent) {
        when (event) {
            is UIEvent.LetterAddedEvent -> {
                onLetterAdded(event.letter)
            }
            is UIEvent.SubmitEvent -> {
                onSubmit()
            }
            is UIEvent.ErasedEvent -> {
                onErase()
            }
            is UIEvent.NewGameStartedEvent -> {
                onNewGame()
            }
        }
    }

    private fun onLetterAdded(letter: String) = with(_uiState.value.game) {
        if (word.letters.count() >= lettersCount) {
            return@with
        }
        val newWord = word.letters.toMutableList().apply { add(Letter(letter)) }
        _uiState.update { it.copy(game = it.game.copy(word = Word(newWord))) }
    }

    private fun onSubmit() = with(_uiState.value.game) {
        if (word.letters.count() < lettersCount) {
            return@with
        }
        viewModelScope.launch {
            var rightLettersCount = 0
            val newWord = word.letters.toMutableList()
            var newAttempts = attempts
            repeat(lettersCount) {
                if (word.letters[it].symbol == hiddenWord[it].uppercase()) {
                    rightLettersCount++
                    newWord[it].state = LetterState.CORRECT
                } else if (hiddenWord.uppercase().contains(word.letters[it].symbol)) {
                    newWord[it].state = LetterState.WRONG_POSITION
                } else {
                    newWord[it].state = LetterState.WRONG
                }
            }
            if (rightLettersCount == lettersCount) {
                onWonGame()
            } else if (attempts == guessesCount) {
                onLostGame()
            } else {
                newAttempts++
            }
            val newHistory = history.toMutableList().apply { add(Word(newWord)) }
            _uiState.update {
                it.copy(
                    game = it.game.copy(
                        word = Word(),
                        attempts = newAttempts,
                        history = newHistory
                    )
                )
            }
        }
    }

    private fun onErase() = with(_uiState.value.game) {
        if (word.letters.isEmpty()) {
            return@with
        }
        val newWord = word.letters.toMutableList().apply { removeLast() }
        _uiState.update { it.copy(game = it.game.copy(word = Word(newWord))) }
    }

    private fun onNewGame() {
        _uiState.update { getInitialUIState() }
    }

    private fun onWonGame() {
        _uiState.update {
            it.copy(
                dialogParams = it.dialogParams.copy(
                    titleId = R.string.dialog_win_title,
                    textId = R.string.dialog_win_text,
                    isOpened = true
                )
            )
        }
    }

    private fun onLostGame() {
        _uiState.update {
            it.copy(
                dialogParams = it.dialogParams.copy(
                    titleId = R.string.dialog_lost_title,
                    textId = R.string.dialog_lost_text,
                    isOpened = true
                )
            )
        }
    }
}