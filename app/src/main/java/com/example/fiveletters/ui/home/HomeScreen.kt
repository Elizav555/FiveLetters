package com.example.fiveletters.ui.home

import Vocabulary
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fiveletters.R
import com.example.fiveletters.domain.model.Settings
import com.example.fiveletters.domain.model.keyboard.KeyClick
import com.example.fiveletters.domain.model.keyboard.KeyType
import com.example.fiveletters.ui.events.UIEvent
import com.example.fiveletters.ui.model.AppBarIcon
import com.example.fiveletters.ui.model.UIState
import com.example.fiveletters.ui.res.values.dialogHelpTitle
import com.example.fiveletters.ui.res.values.dialogSettingsTitle
import com.example.fiveletters.ui.res.values.error
import com.example.fiveletters.ui.res.values.newGame
import com.example.fiveletters.ui.widgets.DialogByParams
import java.util.Locale

@Composable
fun HomeScreen(
    changeTheme: (isDark: Boolean) -> Unit,
    changeLocale: (locale: Locale) -> Unit,
    settings: Settings
) {
    val viewModel = hiltViewModel<HomeViewModel>()

    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val localization = Vocabulary.localization

    LaunchedEffect(key1 = Unit) {
        viewModel.errorMsgEvent.collect {
            snackbarHostState.showSnackbar(
                message = it ?: localization.error(),
                withDismissAction = true
            )
        }
    }

    val keyCLickMap: Map<KeyType, KeyClick> = mapOf(
        KeyType.DEFAULT to { letter: String? ->
            if (!uiState.isEndGame) {
                letter?.let { viewModel.onEvent(UIEvent.LetterAddedEvent(it)) }
            }
        },
        KeyType.ERASE to {
            if (!uiState.isEndGame) {
                viewModel.onEvent(UIEvent.ErasedEvent)
            }
        },
        KeyType.SUBMIT to {
            if (!uiState.isEndGame) {
                viewModel.onEvent(UIEvent.SubmitEvent)
            }
        }
    )

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
        settings = settings,
        icons = icons,
        snackbarHostState = snackbarHostState,
        changeTheme = changeTheme,
        changeLocale = changeLocale,
        keyClickMap = keyCLickMap
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenLayout(
    uiState: UIState,
    settings: Settings,
    icons: List<AppBarIcon>,
    snackbarHostState: SnackbarHostState,
    changeTheme: (isDark: Boolean) -> Unit,
    changeLocale: (locale: Locale) -> Unit,
    keyClickMap: Map<KeyType, KeyClick>
) {
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        modifier = Modifier
            .fillMaxSize(),
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
                DialogByParams(uiState, settings, changeTheme, changeLocale)
            }
            if (uiState.isInited) {
                HomeContent(
                    modifier = Modifier.padding(padding),
                    uiState = uiState,
                    keyClickMap = keyClickMap
                )
            } else {
                Loading()
            }
        }
    )
}


