package com.example.app_bulls_and_cows.ui.widgets.dialogs.settings

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
import com.example.app_bulls_and_cows.domain.model.Settings
import com.example.app_bulls_and_cows.domain.model.SettingsDialogParams
import com.example.app_bulls_and_cows.ui.model.DialogParams
import com.example.app_bulls_and_cows.ui.res.values.apply
import com.example.app_bulls_and_cows.ui.res.values.cancel
import com.example.app_bulls_and_cows.ui.res.values.dialogSettingsTitle
import java.util.Locale


@Composable
fun SettingsDialog(
    dialogParams: DialogParams,
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
