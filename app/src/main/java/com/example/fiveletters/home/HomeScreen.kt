package com.example.fiveletters.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fiveletters.R
import com.example.fiveletters.home.events.GuessEvent
import com.example.fiveletters.home.events.UIEvent
import com.example.fiveletters.home.state.UIState
import com.example.fiveletters.home.utils.DialogParams
import com.example.fiveletters.home.utils.KeyClick
import com.example.fiveletters.home.utils.myKeyboardKeys
import com.example.fiveletters.home.widgets.Keyboard
import com.example.fiveletters.home.widgets.LettersRow

@Composable
fun HomeScreen() {
    val viewModel = hiltViewModel<HomeViewModel>()
    // val scope = rememberCoroutineScope()
    // val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val startNewGame = {
        viewModel.onEvent(UIEvent.NewGameStartedEvent)
    }

    val dialogParams = remember {
        DialogParams(confirmAction = startNewGame)
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.guessEventFlow.collect {
            when (it) {
                is GuessEvent.RightGuess -> {
                    //snackbarHostState.showSnackbar(message = "Right Guess")
                    dialogParams.apply {
                        titleId = R.string.dialog_win_title
                        textId = R.string.dialog_win_text
                        state.value = true
                    }
                }
                is GuessEvent.WrongGuess -> {
                    //TODO
                    snackbarHostState.showSnackbar(
                        message = "Wrong Guess",
                        duration = SnackbarDuration.Short,
                        withDismissAction = true
                    )
                }
                is GuessEvent.LastGuess -> {
                    //snackbarHostState.showSnackbar(message = "Last Guess")
                    dialogParams.apply {
                        titleId = R.string.dialog_lost_title
                        textId = R.string.dialog_lost_text
                        state.value = true
                    }
                }
            }
        }
    }

    val defaultKeyClick: KeyClick = { letter: String? ->
        letter?.let { viewModel.onEvent(UIEvent.LetterAddedEvent(it)) }
    }
    val eraseKeyClick: KeyClick = { _ ->
        viewModel.onEvent(UIEvent.ErasedEvent)
    }
    val submitKeyClick: KeyClick = { _ ->
        viewModel.onEvent(UIEvent.SubmitEvent)
    }

    HomeScreenLayout(
        uiState = uiState,
        defaultKeyClick = defaultKeyClick,
        eraseKeyClick = eraseKeyClick,
        submitKeyClick = submitKeyClick,
        snackbarHostState = snackbarHostState,
        dialogParams = dialogParams
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenLayout(
    uiState: UIState,
    defaultKeyClick: KeyClick,
    eraseKeyClick: KeyClick,
    submitKeyClick: KeyClick,
    snackbarHostState: SnackbarHostState,
    dialogParams: DialogParams,
) {
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SmallTopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                title = { Text(stringResource(id = R.string.app_name)) },
                scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
            )
        },
        content = { padding ->
            if (dialogParams.state.value) {
                ShowDialog(dialogParams)
            }
            HomeContent(
                paddingValues = padding,
                uiState = uiState,
                defaultKeyClick = defaultKeyClick,
                eraseKeyClick = eraseKeyClick,
                submitKeyClick = submitKeyClick
            )
        }
    )
}


@Composable
private fun ShowDialog(dialogParams: DialogParams) {
    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(text = dialogParams.titleId?.let { return@let stringResource(id = it) } ?: "")
        },
        text = {
            Text(text = dialogParams.textId?.let { return@let stringResource(id = it) } ?: "")
        },
        confirmButton = {
            TextButton(
                onClick = {
                    dialogParams.state.value = false
                    dialogParams.confirmAction()
                }
            ) {
                // in this line we are adding
                // text for our confirm button.
                Text(stringResource(id = R.string.new_game))
            }
        },
    )
}

@Composable
fun HomeContent(
    paddingValues: PaddingValues,
    uiState: UIState,
    defaultKeyClick: KeyClick,
    eraseKeyClick: KeyClick,
    submitKeyClick: KeyClick
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(all = 8.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            repeat(uiState.attempts) {
                val word = uiState.history.getOrNull(it) ?: uiState.word
                LettersRow(
                    word = word,
                    count = uiState.lettersCount,
                )
            }
        }
        Keyboard(
            keys = myKeyboardKeys(defaultKeyClick, eraseKeyClick, submitKeyClick)
        )
    }
}

