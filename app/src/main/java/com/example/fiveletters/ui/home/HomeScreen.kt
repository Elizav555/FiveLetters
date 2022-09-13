package com.example.fiveletters.ui.home

import Vocabulary
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
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.fiveletters.ui.events.UIEvent
import com.example.fiveletters.ui.res.values.dialogHelpTitle
import com.example.fiveletters.ui.res.values.dialogSettingsTitle
import com.example.fiveletters.ui.res.values.newGame
import com.example.fiveletters.ui.state.AppBarIcon
import com.example.fiveletters.ui.state.UIState
import com.example.fiveletters.ui.widgets.DialogByParams
import com.example.fiveletters.ui.widgets.KeyboardWidget
import com.example.fiveletters.ui.widgets.LettersRow
import java.util.Locale

@Composable
fun HomeScreen(
    changeTheme: (isDark: Boolean) -> Unit,
    changeLocale: (locale: Locale) -> Unit
) {
    val viewModel = hiltViewModel<HomeViewModel>()

    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val localization = Vocabulary.localization
    viewModel.onEvent(UIEvent.SetLocaleEvent(localization.locale))

    val icons = listOf(
        AppBarIcon(
            icon = Icons.Outlined.Refresh,
            onClick = { viewModel.onEvent(UIEvent.ConfirmNewGameEvent) },
            desc = localization.newGame()
        ),
        AppBarIcon(
            icon = Icons.Outlined.Settings,
            onClick = { viewModel.onEvent(UIEvent.OpenSettingsEvent) },
            desc = localization.dialogSettingsTitle()
        ),
        AppBarIcon(
            icon = Icons.Outlined.Info,
            onClick = { viewModel.onEvent(UIEvent.HelpEvent) },
            desc = localization.dialogHelpTitle()
        ),
    )

    HomeScreenLayout(
        uiState = uiState,
        icons = icons,
        snackbarHostState = snackbarHostState,
        changeTheme = changeTheme,
        changeLocale = changeLocale
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenLayout(
    uiState: UIState,
    icons: List<AppBarIcon>,
    snackbarHostState: SnackbarHostState,
    changeTheme: (isDark: Boolean) -> Unit,
    changeLocale: (locale: Locale) -> Unit
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
                DialogByParams(uiState, changeTheme, changeLocale)
            }
            if (uiState.isInited) {
                HomeContent(
                    modifier = Modifier.padding(padding),
                    uiState = uiState,
                )
            } else {
                Loading()
            }
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
    uiState: UIState
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
            repeat(uiState.game.attempts) {
                val word = uiState.game.history.getOrNull(it) ?: uiState.game.word
                LettersRow(
                    word = word,
                    count = uiState.game.lettersCount.count,
                )
            }
        }
        KeyboardWidget(
            keyboard = uiState.game.keyboard
        )
    }
}

@Composable
private fun Loading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}
