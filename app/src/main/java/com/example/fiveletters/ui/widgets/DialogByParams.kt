package com.example.fiveletters.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.fiveletters.R
import com.example.fiveletters.ui.state.DialogParams
import com.example.fiveletters.ui.state.DialogType
import com.example.fiveletters.ui.state.UIState
import com.example.fiveletters.ui.theme.FiveLettersTheme

@Composable
fun DialogByParams(uiState: UIState) {
    Dialog(
        onDismissRequest = uiState.dialogParams.closeDialogAction,
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true),
    ) {
        if (uiState.dialogParams.dialogType is DialogType.TextDialog) {
            TextDialogContent(dialogParams = uiState.dialogParams)
        } else {
            Surface(
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colorScheme.surface
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(16.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.headlineSmall,
                        text = uiState.dialogParams.dialogType.titleId?.let {
                            return@let stringResource(id = it)
                        } ?: ""
                    )
                    when (uiState.dialogParams.dialogType) {
                        is DialogType.HelpDialog -> {
                            HelpDialogContent(dialogParams = uiState.dialogParams)
                        }
                        is DialogType.SettingsDialog -> {
                            SettingsDialogContent(uiState = uiState)
                        }
                        else -> return@Column
                    }
                }
            }
        }

    }
}

@Composable
private fun HelpDialogContent(dialogParams: DialogParams) {
    val dialog = dialogParams.dialogType as DialogType.HelpDialog
//TODO
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .size(32.dp)
                .background(color = FiveLettersTheme.commonColorScheme.correctBoxColor)
        )
        Text(
            text = stringResource(id = R.string.help_correct),
            style = MaterialTheme.typography.bodyLarge
        )
    }
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .size(32.dp)
                .background(color = FiveLettersTheme.commonColorScheme.wrongPositionBoxColor)
        )
        Text(
            text = stringResource(id = R.string.help_wrong_position),
            style = MaterialTheme.typography.bodyLarge
        )
    }
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .size(32.dp)
                .background(color = FiveLettersTheme.commonColorScheme.wrongBoxColor)
        )
        Text(
            text = stringResource(id = R.string.help_wrong),
            style = MaterialTheme.typography.bodyLarge
        )
    }
    Text(
        modifier = Modifier.padding(16.dp),
        text = stringResource(id = R.string.help_text),
        style = MaterialTheme.typography.bodyLarge
    )
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextButton(
            onClick = dialogParams.closeDialogAction
        ) {
            Text(
                text = stringResource(id = R.string.cancel),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
        TextButton(
            onClick = dialog.confirmAction
        ) {
            Text(
                text = stringResource(id = dialog.confirmBtnTextId),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun SettingsDialogContent(uiState: UIState) {
    val dialog = uiState.dialogParams.dialogType as DialogType.SettingsDialog
    val isDarkTheme = isSystemInDarkTheme()
    val lettersCount = uiState.game.lettersCount
    Text(
        text = "Settingsss",
        style = MaterialTheme.typography.bodyLarge
    )
    Row(horizontalArrangement = Arrangement.SpaceAround) {
        TextButton(
            onClick = uiState.dialogParams.closeDialogAction
        ) {
            Text(
                text = stringResource(id = R.string.cancel),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
        TextButton(
            onClick = { dialog.confirmAction(lettersCount, isDarkTheme) }
        ) {
            Text(
                text = stringResource(id = uiState.dialogParams.dialogType.confirmBtnTextId),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun TextDialogContent(dialogParams: DialogParams) {
    val dialog = dialogParams.dialogType as DialogType.TextDialog
    AlertDialog(
        onDismissRequest = dialogParams.closeDialogAction,
        title = {
            Text(text = dialogParams.dialogType.titleId?.let { return@let stringResource(id = it) }
                ?: "")
        },
        text = {
            Text(text = stringResource(id = dialog.textId))
        },
        confirmButton = {
            TextButton(
                onClick = dialog.confirmAction
            ) {
                Text(stringResource(id = dialog.confirmBtnTextId))
            }
        },
        dismissButton = {
            TextButton(
                onClick = dialogParams.closeDialogAction
            ) {
                Text(stringResource(id = R.string.cancel))
            }
        },
    )
}