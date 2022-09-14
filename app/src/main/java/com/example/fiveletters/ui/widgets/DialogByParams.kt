package com.example.fiveletters.ui.widgets

import LocalLocalization
import Vocabulary.localization
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.fiveletters.domain.model.Settings
import com.example.fiveletters.domain.model.SettingsDialogParams
import com.example.fiveletters.domain.model.letter.LettersCount
import com.example.fiveletters.ui.model.DialogParams
import com.example.fiveletters.ui.model.DialogType
import com.example.fiveletters.ui.model.TextDialogType
import com.example.fiveletters.ui.model.UIState
import com.example.fiveletters.ui.res.values.apply
import com.example.fiveletters.ui.res.values.cancel
import com.example.fiveletters.ui.res.values.dialogConfirmText
import com.example.fiveletters.ui.res.values.dialogConfirmTitle
import com.example.fiveletters.ui.res.values.dialogHelpTitle
import com.example.fiveletters.ui.res.values.dialogLostText
import com.example.fiveletters.ui.res.values.dialogLostTitle
import com.example.fiveletters.ui.res.values.dialogSettingsTitle
import com.example.fiveletters.ui.res.values.dialogWinText
import com.example.fiveletters.ui.res.values.dialogWinTitle
import com.example.fiveletters.ui.res.values.gotIt
import com.example.fiveletters.ui.res.values.newGame
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

@Composable
private fun HelpDialog(dialogParams: DialogParams) {
    AlertDialog(
        onDismissRequest = dialogParams.closeDialogAction,
        title = {
            Text(text = localization.dialogHelpTitle())
        },
        text = {
            HelpDialogContent()
        },
        confirmButton = {
            TextButton(
                onClick = { dialogParams.confirmAction(null) }
            ) {
                Text(localization.gotIt())
            }
        },
        dismissButton = {
        },
    )
}

@Composable
private fun SettingsDialog(
    dialogParams: DialogParams,
    newLettersCountState: MutableState<LettersCount>,
    settings: Settings,
    changeTheme: (isDark: Boolean) -> Unit,
    changeLocale: (locale: Locale) -> Unit
) {
    val systemMode = isSystemInDarkTheme()
    val isDarkTheme = remember {
        mutableStateOf(settings.isDarkMode ?: systemMode)
    }

    val currentLocale = remember {
        mutableStateOf(settings.locale)
    }

    val localLocale = LocalLocalization.current.locale

    AlertDialog(
        onDismissRequest = dialogParams.closeDialogAction,
        title = {
            Text(text = localization.dialogSettingsTitle())
        },
        text = {
            SettingsDialogContent(
                lettersCountState = newLettersCountState,
                isDarkTheme = isDarkTheme,
                currentLocale = currentLocale,
                changeTheme = changeTheme,
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    dialogParams.confirmAction(
                        SettingsDialogParams(
                            lettersCount = newLettersCountState.value,
                            isLocaleChanged = localLocale != currentLocale.value,
                            locale = currentLocale.value
                        )
                    )
                    changeLocale(currentLocale.value)
                }
            ) {
                Text(localization.apply())
            }
        },
        dismissButton = {
            TextButton(
                onClick = dialogParams.closeDialogAction
            ) {
                Text(localization.cancel())
            }
        },
    )
}

@Composable
private fun TextDialog(dialogParams: DialogParams) {
    val textDialogType = (dialogParams.dialogType as DialogType.TextDialog).textDialogType
    var title = ""
    var text = ""
    when (textDialogType) {
        TextDialogType.WON -> {
            title = localization.dialogWinTitle()
            text = localization.dialogWinText()
        }
        TextDialogType.LOST -> {
            title = localization.dialogLostTitle()
            text = localization.dialogLostText()
        }
        TextDialogType.CONFIRM -> {
            title = localization.dialogConfirmTitle()
            text = localization.dialogConfirmText()
        }
    }
    AlertDialog(
        onDismissRequest = dialogParams.closeDialogAction,
        title = {
            Text(text = title)
        },
        text = {
            TextDialogContent(text = text, params = dialogParams.dialogType.textParams)
        },
        confirmButton = {
            TextButton(
                onClick = { dialogParams.confirmAction(null) }
            ) {
                Text(localization.newGame())
            }
        },
        dismissButton = {
            TextButton(
                onClick = dialogParams.closeDialogAction
            ) {
                Text(localization.cancel())
            }
        },
    )
}