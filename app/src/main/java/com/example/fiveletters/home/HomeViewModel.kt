package com.example.fiveletters.home

import androidx.lifecycle.ViewModel
import com.example.fiveletters.home.events.UIEvent
import com.example.fiveletters.home.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _uiState: MutableStateFlow<UIState> = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState
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
        }
    }

    private fun onLetterAdded(letter: String) = with(_uiState.value) {
        if (word.count() >= lettersCount) {
            return@with
        }
        val newWord = word.toMutableList().apply { add(letter) }
        _uiState.update { it.copy(word = newWord) }
    }

    private fun onSubmit() {
        TODO("Not yet implemented")
    }

    private fun onErase() = with(_uiState.value) {
        if (word.isEmpty()) {
            return@with
        }
        val newWord = word.toMutableList().apply { removeLast() }
        _uiState.update { it.copy(word = newWord) }
    }
}