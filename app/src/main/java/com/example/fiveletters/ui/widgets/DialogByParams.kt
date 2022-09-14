package com.example.fiveletters.ui.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.fiveletters.domain.model.Settings
import com.example.fiveletters.ui.model.DialogType
import com.example.fiveletters.ui.model.UIState
import com.example.fiveletters.ui.widgets.dialogs.help.HelpDialog
import com.example.fiveletters.ui.widgets.dialogs.settings.SettingsDialog
import com.example.fiveletters.ui.widgets.dialogs.text.TextDialog
import java.util.Locale

@Composable
fun DialogByParams(
    uiState: UIState,
    settings: Settings,
    changeTheme: (isDark: Boolean) -> Unit,
    changeLocale: (locale: Locale) -> Unit
) {
    val dialogParams = uiState.dialogParams
    val newLettersCountState = remember {
        mutableStateOf(uiState.game.lettersCount)
    }
    when (uiState.dialogParams.dialogType) {
        is DialogType.HelpDialog -> {
            HelpDialog(dialogParams = uiState.dialogParams)
        }
        is DialogType.SettingsDialog -> {
            SettingsDialog(
                dialogParams = dialogParams,
                newLettersCountState = newLettersCountState,
                settings = settings,
                changeTheme = changeTheme,
                changeLocale = changeLocale
            )
        }
        is DialogType.TextDialog -> {
            TextDialog(dialogParams = uiState.dialogParams)
        }
    }
}

