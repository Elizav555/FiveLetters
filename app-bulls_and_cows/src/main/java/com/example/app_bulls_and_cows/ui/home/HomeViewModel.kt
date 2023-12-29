package com.example.app_bulls_and_cows.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_bulls_and_cows.di.coroutines.qualifiers.DefaultDispatcher
import com.example.app_bulls_and_cows.domain.interactors.preferences.GamePrefsInteractor
import com.example.app_bulls_and_cows.domain.interactors.words.WordsInteractor
import com.example.app_bulls_and_cows.domain.model.Game
import com.example.app_bulls_and_cows.domain.model.SettingsDialogParams
import com.example.app_bulls_and_cows.domain.model.Word
import com.example.app_bulls_and_cows.domain.model.letter.Letter
import com.example.app_bulls_and_cows.domain.model.letter.LetterState
import com.example.app_bulls_and_cows.domain.utils.myKeyboardKeys
import com.example.app_bulls_and_cows.ui.events.UIEvent
import com.example.app_bulls_and_cows.ui.model.DialogParams
import com.example.app_bulls_and_cows.ui.model.DialogType
import com.example.app_bulls_and_cows.ui.model.TextDialogType
import com.example.app_bulls_and_cows.ui.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gamePrefsInteractor: GamePrefsInteractor,
    private val wordsInteractor: WordsInteractor,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiState: MutableStateFlow<UIState> = MutableStateFlow(getInitialUIState())
    val uiState: StateFlow<UIState> = _uiState
    private var locale: Locale = Locale.ENGLISH

    private val _errorMsgChannel: Channel<String?> =
        Channel(capacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val errorMsgEvent = _errorMsgChannel.receiveAsFlow()

    init {
        initGame()
    }

    fun onEvent(event: UIEvent) {
        when (event) {
            is UIEvent.LetterAddedEvent -> {
                if (!_uiState.value.isEndGame) {
                    onLetterAdded(event.letter)
                }
            }
            is UIEvent.SubmitEvent -> {
                if (!_uiState.value.isEndGame) {
                    onSubmit()
                }
            }
            is UIEvent.ErasedEvent -> {
                if (!_uiState.value.isEndGame) {
                    onErase()
                }
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
                onApplySettingsEvent(event.settingsDialogParams)
            }
            is UIEvent.SetLocaleEvent -> {
                locale = event.locale
            }
        }
        viewModelScope.launch {
            gamePrefsInteractor.saveGame(GAME_KEY, _uiState.value.game)
        }
    }

    private fun getInitialUIState(): UIState {
        return UIState(
            game = Game(
                hiddenWord = wordsInteractor.getRandomWord(),
                keyboard = myKeyboardKeys()
            ),
            dialogParams = DialogParams(
                dialogType = DialogType.TextDialog(
                    textDialogType = TextDialogType.CONFIRM
                ),
                confirmAction = { onEvent(UIEvent.NewGameStartedEvent) },
                closeDialogAction = { closeDialog() }
            ),
        )
    }

    private fun initGame() = viewModelScope.launch {
        val cachedGame: Game? = gamePrefsInteractor.getGame(GAME_KEY)
        if (cachedGame != null) {
            _uiState.update {
                it.copy(game = cachedGame, isInited = true)
            }
        } else {
            val word = wordsInteractor.getRandomWord()
            _uiState.update {
                it.copy(game = it.game.copy(hiddenWord = word), isInited = true)
            }
        }
    }

    private fun onLetterAdded(letter: String) = with(_uiState.value.game) {
        if (word.letters.count() >= 4) {
            return@with
        }
        val newWord = word.letters.toMutableList().apply { add(Letter(letter)) }
        _uiState.update { it.copy(game = it.game.copy(word = Word(newWord))) }
    }

    private fun onSubmit() = with(_uiState.value.game) {
        if (word.letters.count() < 4) {
            return@with
        }
        viewModelScope.launch(defaultDispatcher) {
            var rightLettersCount = 0
            val newWord = word.letters.toMutableList()
            var newAttempts = attempts
            val keyboard = _uiState.value.game.keyboard
            repeat(4) { index ->
                if (word.letters[index].symbol == hiddenWord[index].uppercase()) {
                    rightLettersCount++
                    newWord[index].state = LetterState.CORRECT
                } else if (hiddenWord.uppercase().contains(word.letters[index].symbol)) {
                    newWord[index].state = LetterState.WRONG_POSITION
                } else {
                    newWord[index].state = LetterState.WRONG
                    keyboard.rows.forEachIndexed { indexFirst, row ->
                        row.keys.forEachIndexed { indexSecond, key ->
                            if (key.symbol == word.letters[index].symbol) {
                                keyboard.rows[indexFirst].keys[indexSecond].isWrong = true
                            }
                        }
                    }
                }
            }
            if (rightLettersCount == 4) {
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
                        history = newHistory,
                        keyboard = keyboard
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

    private fun onNewGame(
        newLocale: Locale = locale
    ) =
        viewModelScope.launch {
            locale = newLocale
            val word = wordsInteractor.getRandomWord()
            _uiState.update {
                getInitialUIState()
            }
            _uiState.update {
                it.copy(
                    isInited = true,
                    game = it.game.copy(
                        hiddenWord = word,)
                )
            }
            gamePrefsInteractor.saveGame(GAME_KEY, _uiState.value.game)
        }

    private fun onWonGame() = viewModelScope.launch {
        delay(getDelay())
        _uiState.update {
            it.copy(
                dialogParams = it.dialogParams.copy(
                    dialogType = DialogType.TextDialog(
                        textDialogType = TextDialogType.WON
                    ),
                    confirmAction = { onEvent(UIEvent.NewGameStartedEvent) },
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
                        textDialogType = TextDialogType.CONFIRM
                    ),
                    confirmAction = { onEvent(UIEvent.NewGameStartedEvent) },
                    isOpened = true
                )
            )
        }
    }

    private fun onLostGame() = viewModelScope.launch {
        delay(getDelay())
        _uiState.update {
            it.copy(
                dialogParams = it.dialogParams.copy(
                    dialogType = DialogType.TextDialog(
                        textParams = listOf(_uiState.value.game.hiddenWord),
                        textDialogType = TextDialogType.LOST
                    ),
                    confirmAction = { onEvent(UIEvent.NewGameStartedEvent) },
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
                    confirmAction = { closeDialog() },
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
                    confirmAction = { params: Any? ->
                        onEvent(UIEvent.ApplySettingEvent(params as? SettingsDialogParams))
                    },
                    isOpened = true,
                )
            )
        }
    }

    private fun onApplySettingsEvent(params: SettingsDialogParams?) {
        params?.let {
            if ( params.isLocaleChanged) {
                onNewGame(
                    params.locale
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

    private fun getDelay() = (1500 + 4 * 100).toLong()

    companion object {
        const val GAME_KEY = "game_key"
    }
}