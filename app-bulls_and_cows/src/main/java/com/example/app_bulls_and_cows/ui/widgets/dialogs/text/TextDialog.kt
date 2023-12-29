package com.example.app_bulls_and_cows.ui.widgets.dialogs.text

import Vocabulary.localization
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.example.app_bulls_and_cows.ui.model.DialogParams
import com.example.app_bulls_and_cows.ui.model.DialogType
import com.example.app_bulls_and_cows.ui.model.TextDialogType
import com.example.app_bulls_and_cows.ui.res.values.cancel
import com.example.app_bulls_and_cows.ui.res.values.dialogConfirmText
import com.example.app_bulls_and_cows.ui.res.values.dialogConfirmTitle
import com.example.app_bulls_and_cows.ui.res.values.dialogLostText
import com.example.app_bulls_and_cows.ui.res.values.dialogLostTitle
import com.example.app_bulls_and_cows.ui.res.values.dialogWinText
import com.example.app_bulls_and_cows.ui.res.values.dialogWinTitle
import com.example.app_bulls_and_cows.ui.res.values.newGame


@Composable
fun TextDialog(dialogParams: DialogParams) {
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