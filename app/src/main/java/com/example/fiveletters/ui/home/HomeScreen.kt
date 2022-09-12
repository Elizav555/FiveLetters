package com.example.fiveletters.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fiveletters.R
import com.example.fiveletters.domain.model.Game
import com.example.fiveletters.domain.model.KeyClick
import com.example.fiveletters.domain.utils.myKeyboardKeys
import com.example.fiveletters.ui.events.UIEvent
import com.example.fiveletters.ui.state.AppBarIcon
import com.example.fiveletters.ui.state.UIState
import com.example.fiveletters.ui.widgets.DialogByParams
import com.example.fiveletters.ui.widgets.Keyboard
import com.example.fiveletters.ui.widgets.LettersRow

@Composable
fun HomeScreen(changeTheme: (isDark: Boolean) -> Unit) {
    val viewModel = hiltViewModel<HomeViewModel>()

    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember {
        SnackbarHostState()
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

    val icons = listOf(
        AppBarIcon(
            icon = Icons.Outlined.Refresh,
            onClick = { viewModel.onEvent(UIEvent.ConfirmNewGameEvent) },
            desc = stringResource(id = R.string.new_game)
        ),
        AppBarIcon(
            icon = Icons.Outlined.Settings,
            onClick = { viewModel.onEvent(UIEvent.OpenSettingsEvent) },
            desc = stringResource(id = R.string.dialog_settings_title)
        ),
        AppBarIcon(
            icon = Icons.Outlined.Info,
            onClick = { viewModel.onEvent(UIEvent.HelpEvent) },
            desc = stringResource(id = R.string.dialog_help_title)
        ),
    )

    HomeScreenLayout(
        uiState = uiState,
        icons = icons,
        defaultKeyClick = defaultKeyClick,
        eraseKeyClick = eraseKeyClick,
        submitKeyClick = submitKeyClick,
        snackbarHostState = snackbarHostState,
        changeTheme = changeTheme
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenLayout(
    uiState: UIState,
    icons: List<AppBarIcon>,
    defaultKeyClick: KeyClick,
    eraseKeyClick: KeyClick,
    submitKeyClick: KeyClick,
    snackbarHostState: SnackbarHostState,
    changeTheme: (isDark: Boolean) -> Unit
) {
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(
                        stringResource(id = R.string.app_name),
                    )
                },
                colors = TopAppBarDefaults
                    .smallTopAppBarColors
                        (containerColor = MaterialTheme.colorScheme.surfaceVariant),
                actions = { AppBarActions(icons) },
                scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
            )
        },
        content = { padding ->
            if (uiState.dialogParams.isOpened) {
                DialogByParams(uiState, changeTheme)
            }
            HomeContent(
                modifier = Modifier.padding(padding),
                game = uiState.game,
                defaultKeyClick = defaultKeyClick,
                eraseKeyClick = eraseKeyClick,
                submitKeyClick = submitKeyClick
            )
        }
    )
}

@Composable
private fun AppBarActions(icons: List<AppBarIcon>) {
    icons.forEach {
        IconButton(onClick = it.onClick) {
            Icon(
                imageVector = it.icon,
                contentDescription = it.desc
            )
        }
    }
}


@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    game: Game,
    defaultKeyClick: KeyClick,
    eraseKeyClick: KeyClick,
    submitKeyClick: KeyClick
) {
    Column(
        modifier = modifier
            .fillMaxSize()
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
            repeat(game.attempts) {
                val word = game.history.getOrNull(it) ?: game.word
                LettersRow(
                    word = word,
                    count = game.lettersCount,
                )
            }
        }
        Keyboard(
            keys = myKeyboardKeys(defaultKeyClick, eraseKeyClick, submitKeyClick)
        )
    }
}

