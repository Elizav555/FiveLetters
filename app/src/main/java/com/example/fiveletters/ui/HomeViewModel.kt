package com.example.fiveletters.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiveletters.domain.model.Letter
import com.example.fiveletters.domain.model.LetterState
import com.example.fiveletters.ui.events.GuessEvent
import com.example.fiveletters.ui.events.UIEvent
import com.example.fiveletters.ui.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _uiState: MutableStateFlow<UIState> = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState

    private val _guessEventChannel = Channel<GuessEvent>()
    val guessEventFlow = _guessEventChannel.receiveAsFlow()

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

    private fun onLetterAdded(letter: String) = with(_uiState.value) {
        if (word.count() >= lettersCount) {
            return@with
        }
        val newWord = word.toMutableList().apply { add(Letter(letter)) }
        _uiState.update { it.copy(word = newWord) }
    }

    private fun onSubmit() = with(_uiState.value) {
        if (word.count() < lettersCount) {
            return@with
        }
        viewModelScope.launch {
            var rightLettersCount = 0
            val newWord = word.toMutableList()
            var newAttempts = attempts
            repeat(lettersCount) {
                if (word[it].symbol == hiddenWord[it]) {
                    rightLettersCount++
                    newWord[it].state = LetterState.CORRECT
                } else if (hiddenWord.contains(word[it].symbol)) {
                    newWord[it].state = LetterState.WRONG_POSITION
                } else {
                    newWord[it].state = LetterState.WRONG
                }
            }
            if (rightLettersCount == lettersCount) {
                _guessEventChannel.send(GuessEvent.RightGuess)
            } else if (attempts == guessesCount) {
                _guessEventChannel.send(GuessEvent.LastGuess)
            } else {
                newAttempts++
                _guessEventChannel.send(GuessEvent.WrongGuess)
            }
            val newHistory = history.toMutableList().apply { add(newWord) }
            _uiState.update {
                it.copy(
                    word = emptyList(),
                    attempts = newAttempts,
                    history = newHistory
                )
            }
        }
    }

    private fun onErase() = with(_uiState.value) {
        if (word.isEmpty()) {
            return@with
        }
        val newWord = word.toMutableList().apply { removeLast() }
        _uiState.update { it.copy(word = newWord) }
    }

    private fun onNewGame() {
        _uiState.update { UIState() }
    }
}