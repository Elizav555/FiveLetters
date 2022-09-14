package com.example.fiveletters.ui.widgets.dialogs.help

import Vocabulary.localization
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.example.fiveletters.ui.model.DialogParams
import com.example.fiveletters.ui.res.values.dialogHelpTitle
import com.example.fiveletters.ui.res.values.gotIt

@Composable
fun HelpDialog(dialogParams: DialogParams) {
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