package com.example.fiveletters.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiveletters.di.coroutines.qualifiers.DefaultDispatcher
import com.example.fiveletters.domain.interactors.preferences.GamePrefsInteractor
import com.example.fiveletters.domain.model.Game
import com.example.fiveletters.domain.model.KeyClick
import com.example.fiveletters.domain.model.Letter
import com.example.fiveletters.domain.model.LetterState
import com.example.fiveletters.domain.model.LettersCount
import com.example.fiveletters.domain.model.SettingsDialogParams
import com.example.fiveletters.domain.model.Word
import com.example.fiveletters.domain.utils.MockedKeyboard.myKeyClicks
import com.example.fiveletters.domain.utils.MockedKeyboard.myKeyboardKeys
import com.example.fiveletters.domain.utils.mockedDictionary
import com.example.fiveletters.ui.events.UIEvent
import com.example.fiveletters.ui.state.DialogParams
import com.example.fiveletters.ui.state.DialogType
import com.example.fiveletters.ui.state.TextDialogType
import com.example.fiveletters.ui.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gamePrefsInteractor: GamePrefsInteractor,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiState: MutableStateFlow<UIState> = MutableStateFlow(getInitialUIState())
    val uiState: StateFlow<UIState> = _uiState
    private var keyClicks: List<List<KeyClick>>? = null
    private var locale: Locale = Locale.ENGLISH

    init {
        initGame()
    }

    private fun getInitialUIState(): UIState {
        val defaultLettersCount = LettersCount.FIVE
        val defaultKeyClick: KeyClick = { letter: String? ->
            letter?.let { onEvent(UIEvent.LetterAddedEvent(it)) }
        }
        val eraseKeyClick: KeyClick = {
            onEvent(UIEvent.ErasedEvent)
        }
        val submitKeyClick: KeyClick = {
            onEvent(UIEvent.SubmitEvent)
        }
        keyClicks = myKeyClicks(
            locale = locale,
            defaultKeyClick = defaultKeyClick,
            eraseKeyClick = eraseKeyClick,
            submitKeyClick = submitKeyClick
        )
        return UIState(
            game = Game(
                lettersCount = defaultLettersCount,
                hiddenWord = getNewHiddenWord(defaultLettersCount),
                keyboard = myKeyboardKeys(
                    locale = locale,
                    defaultKeyClick = defaultKeyClick,
                    eraseKeyClick = eraseKeyClick,
                    submitKeyClick = submitKeyClick
                )
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
        val cachedGame: Game? = gamePrefsInteractor.getGame(GAME_KEY, keyClicks = keyClicks)
        if (cachedGame != null) {
            _uiState.update {
                it.copy(game = cachedGame, isInited = true)
            }
        } else {
            val word = getNewHiddenWord(_uiState.value.game.lettersCount)
            _uiState.update {
                it.copy(game = it.game.copy(hiddenWord = word), isInited = true)
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
                onApplySettingsEvent(event.settingsDialogParams)
            }
            is UIEvent.SetLocaleEvent -> {
                locale = event.locale
                onNewGame()
            }
        }
        viewModelScope.launch {
            gamePrefsInteractor.saveGame(GAME_KEY, _uiState.value.game)
        }
    }

    private fun onLetterAdded(letter: String) = with(_uiState.value.game) {
        if (word.letters.count() >= lettersCount.count) {
            return@with
        }
        val newWord = word.letters.toMutableList().apply { add(Letter(letter)) }
        _uiState.update { it.copy(game = it.game.copy(word = Word(newWord))) }
    }

    private fun onSubmit() = with(_uiState.value.game) {
        if (word.letters.count() < lettersCount.count) {
            return@with
        }
        viewModelScope.launch(defaultDispatcher) {
            var rightLettersCount = 0
            val newWord = word.letters.toMutableList()
            var newAttempts = attempts
            val keyboard = _uiState.value.game.keyboard
            repeat(lettersCount.count) { index ->
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
            if (rightLettersCount == lettersCount.count) {
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
        lettersCount: LettersCount = _uiState.value.game.lettersCount,
        newLocale: Locale = locale
    ) =
        viewModelScope.launch {
            locale = newLocale
            val word = getNewHiddenWord(lettersCount)
            _uiState.update {
                getInitialUIState()
            }
            _uiState.update {
                it.copy(
                    isInited = true,
                    game = it.game.copy(
                        hiddenWord = word,
                        lettersCount = lettersCount
                    )
                )
            }
            gamePrefsInteractor.saveGame(GAME_KEY, _uiState.value.game)
        }

    private fun onWonGame() {
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

    private fun onLostGame() {
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
            if (_uiState.value.game.lettersCount != params.lettersCount || params.isLocaleChanged) {
                onNewGame(
                    params.lettersCount,
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

    private fun getNewHiddenWord(lettersCount: LettersCount): String {
        //TODO
        return mockedDictionary(locale, lettersCount).random()
    }

    companion object {
        const val GAME_KEY = "game_key"
    }
}