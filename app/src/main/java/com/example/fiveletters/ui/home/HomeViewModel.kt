package com.example.fiveletters.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiveletters.R
import com.example.fiveletters.domain.interactors.preferences.PreferencesInteractor
import com.example.fiveletters.domain.interactors.words.WordsInteractor
import com.example.fiveletters.domain.model.Game
import com.example.fiveletters.domain.model.Letter
import com.example.fiveletters.domain.model.LetterState
import com.example.fiveletters.domain.model.Word
import com.example.fiveletters.domain.utils.mockedDictionary
import com.example.fiveletters.ui.events.UIEvent
import com.example.fiveletters.ui.state.DialogParams
import com.example.fiveletters.ui.state.DialogType
import com.example.fiveletters.ui.state.UIState
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.reflect.Type
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferencesInteractor: PreferencesInteractor,
   private val wordsInteractor: WordsInteractor
) : ViewModel() {
    private val _uiState: MutableStateFlow<UIState> = MutableStateFlow(getInitialUIState())
    val uiState: StateFlow<UIState> = _uiState

    init {
        initGame()
    }

    private fun getInitialUIState(): UIState {
        val defaultLettersCount = 5
        return UIState(
            game = Game(
                lettersCount = defaultLettersCount,
                hiddenWord = mockedDictionary.random()
            ),
            dialogParams = DialogParams(
                dialogType = DialogType.TextDialog(
                    textId = R.string.app_name
                ),
                confirmAction = { onEvent(UIEvent.NewGameStartedEvent) },
                confirmBtnTextId = R.string.close_dialog,
                closeDialogAction = { closeDialog() }
            ),
        )
    }

    private fun initGame() = viewModelScope.launch {
        val type: Type = object : TypeToken<Game?>() {}.type
        val cachedGame: Game? = preferencesInteractor.getItem<Game>(GAME_KEY, type)
        if (cachedGame != null) {
            _uiState.update {
                it.copy(game = cachedGame, isInited = true)
            }
        } else {
            val word = getNewHiddenWord(_uiState.value.game.lettersCount).getOrNull()
                ?: mockedDictionary.random()
            _uiState.update {
                it.copy(game = it.game.copy(hiddenWord = word))
            }
        }
    }

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
            is UIEvent.HelpEvent -> {
                onHelp()
            }
            is UIEvent.OpenSettingsEvent -> {
                onSettings()
            }
            is UIEvent.ConfirmNewGameEvent -> {
                onConfirmNewGame()
            }
            is UIEvent.ApplySettingEvent -> {
                onApplySettingsEvent(event.lettersCount)
            }
        }
        viewModelScope.launch {
            preferencesInteractor.saveItem(GAME_KEY, _uiState.value.game)
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
                    dialogType = DialogType.TextDialog(
                        textId = R.string.dialog_win_text,
                    ),
                    titleId = R.string.dialog_win_title,
                    confirmAction = { onEvent(UIEvent.NewGameStartedEvent) },
                    confirmBtnTextId = R.string.new_game,
                    isOpened = true
                )
            )
        }
    }

    private fun onConfirmNewGame() {
        _uiState.update {
            it.copy(
                dialogParams = it.dialogParams.copy(
                    dialogType = DialogType.TextDialog(
                        textId = R.string.dialog_confirm_text,
                    ),
                    titleId = R.string.dialog_confirm_title,
                    confirmAction = { onEvent(UIEvent.NewGameStartedEvent) },
                    confirmBtnTextId = R.string.new_game,
                    isOpened = true
                )
            )
        }
    }

    private fun onLostGame() {
        _uiState.update {
            it.copy(
                dialogParams = it.dialogParams.copy(
                    dialogType = DialogType.TextDialog(
                        textId = R.string.dialog_lost_text,
                    ),
                    titleId = R.string.dialog_lost_title,
                    confirmAction = { onEvent(UIEvent.NewGameStartedEvent) },
                    confirmBtnTextId = R.string.new_game,
                    isOpened = true
                )
            )
        }
    }

    private fun onHelp() {
        _uiState.update {
            it.copy(
                dialogParams = it.dialogParams.copy(
                    dialogType = DialogType.HelpDialog,
                    titleId = R.string.dialog_help_title,
                    confirmAction = { closeDialog() },
                    confirmBtnTextId = R.string.got_it,
                    isOpened = true,
                )
            )
        }
    }

    private fun onSettings() {
        _uiState.update {
            it.copy(
                dialogParams = it.dialogParams.copy(
                    dialogType = DialogType.SettingsDialog,
                    confirmAction = { lettersCount: Any? ->
                        onEvent(UIEvent.ApplySettingEvent(lettersCount as? Int))
                    },
                    confirmBtnTextId = R.string.apply,
                    isOpened = true,
                )
            )
        }
    }

    private fun onApplySettingsEvent(lettersCount: Int?) {
        (lettersCount)?.let { int ->
            if (_uiState.value.game.lettersCount == int) {
                applySettings(
                    int,
                )
            } else {
                closeDialog()
            }
        } ?: closeDialog()
    }

    private fun closeDialog() = _uiState.update {
        it.copy(
            dialogParams = it.dialogParams.copy(
                isOpened = false
            )
        )
    }

    private fun applySettings(lettersCount: Int) = viewModelScope.launch {
        val word = getNewHiddenWord(lettersCount).getOrNull() ?: mockedDictionary.random()
        _uiState.update {
            it.copy(
                game = Game(lettersCount = lettersCount, hiddenWord = word),
                dialogParams = it.dialogParams.copy(
                    isOpened = false
                ),
            )
        }
    }

    private suspend fun getNewHiddenWord(lettersCount: Int): Result<String> {
        return wordsInteractor.getRandomWord(lettersCount)
    }

    companion object {
        const val GAME_KEY = "game_key"
    }
}