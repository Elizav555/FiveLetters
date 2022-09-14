package com.example.fiveletters.ui.widgets.dialogs.settings

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
import com.example.fiveletters.ui.res.values.apply
import com.example.fiveletters.ui.res.values.cancel
import com.example.fiveletters.ui.res.values.dialogSettingsTitle
import com.example.fiveletters.ui.widgets.SettingsDialogContent
import java.util.Locale


@Composable
fun SettingsDialog(
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
