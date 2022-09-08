package com.example.fiveletters.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiveletters.home.events.UIEvent
import com.example.fiveletters.home.events.ValidationEvent
import com.example.fiveletters.home.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _uiState: MutableStateFlow<UIState> = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState

    private val _validationChannel = Channel<ValidationEvent>()
    val validationFlow = _validationChannel.receiveAsFlow()

    fun onEvent(event: UIEvent) {
        when (event) {
            is UIEvent.WordChangedEvent -> {
                onWordChanged()
            }
            is UIEvent.SubmitEvent -> {
                submitWord()
            }
        }
    }

    private fun onWordChanged() {
        TODO("Not yet implemented")
    }

    private fun submitWord() {
        TODO("Not yet implemented")
    }

    private fun validate() {
        var hasError = false
        viewModelScope.launch {
            if (!hasError) {
                _validationChannel.send(ValidationEvent.Success)
            }
        }
    }
}