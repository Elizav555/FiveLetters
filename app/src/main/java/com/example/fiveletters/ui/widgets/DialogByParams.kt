package com.example.fiveletters.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.fiveletters.R
import com.example.fiveletters.ui.state.DialogParams
import com.example.fiveletters.ui.state.DialogType
import com.example.fiveletters.ui.theme.FiveLettersTheme

@Composable
fun DialogByParams(dialogParams: DialogParams) {
    Dialog(
        onDismissRequest = dialogParams.closeDialogAction,
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true),
    ) {
        Surface() {
            Column(modifier = Modifier.wrapContentSize()) {
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    text = dialogParams.dialogType.titleId?.let {
                        return@let stringResource(id = it)
                    } ?: ""
                )
                when (dialogParams.dialogType) {
                    is DialogType.HelpDialog -> {
                        HelpDialogContent()
                    }
                    is DialogType.SettingsDialog -> {
                        SettingsDialogContent(dialog = dialogParams.dialogType)
                    }
                    is DialogType.TextDialog -> {
                        TextDialogContent(dialog = dialogParams.dialogType)
                    }
                }
                TextButton(
                    modifier = Modifier.align(Alignment.End),
                    onClick = { dialogParams.dialogType.confirmAction }
                ) {
                    Text(
                        text = stringResource(id = dialogParams.dialogType.confirmBtnTextId)
                    )
                }
            }
        }
    }
}

@Composable
private fun HelpDialogContent() {
    Row() {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .size(32.dp)
                .background(color = FiveLettersTheme.commonColorScheme.correctBoxColor)
        )
        Text(
            text = stringResource(id = R.string.help_correct),
            style = MaterialTheme.typography.bodyLarge
        )
    }
    Row() {
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
    Row() {
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
}

@Composable
private fun SettingsDialogContent(dialog: DialogType.SettingsDialog) {
    //TODO
    Text(
        text = "Settingsss",
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
private fun TextDialogContent(dialog: DialogType.TextDialog) {
    Text(
        text = stringResource(id = dialog.textId),
        style = MaterialTheme.typography.bodyLarge
    )
}