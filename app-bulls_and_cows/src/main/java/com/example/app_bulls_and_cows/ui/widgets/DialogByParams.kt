package com.example.app_bulls_and_cows.ui.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.app_bulls_and_cows.domain.model.Settings
import com.example.app_bulls_and_cows.ui.model.DialogType
import com.example.app_bulls_and_cows.ui.model.UIState
import com.example.app_bulls_and_cows.ui.widgets.dialogs.help.HelpDialog
import com.example.app_bulls_and_cows.ui.widgets.dialogs.settings.SettingsDialog
import com.example.app_bulls_and_cows.ui.widgets.dialogs.text.TextDialog
import java.util.Locale

@Composable
fun DialogByParams(
    uiState: UIState,
    settings: Settings,
    changeTheme: (isDark: Boolean) -> Unit,
    changeLocale: (locale: Locale) -> Unit
) {
    val dialogParams = uiState.dialogParams
    when (uiState.dialogParams.dialogType) {
        is DialogType.HelpDialog -> {
            HelpDialog(dialogParams = uiState.dialogParams)
        }
        is DialogType.SettingsDialog -> {
            SettingsDialog(
                dialogParams = dialogParams,
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

