package com.example.fiveletters.ui.widgets

import Vocabulary
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.fiveletters.domain.model.LettersCount
import com.example.fiveletters.domain.model.lettersCountFromInt
import com.example.fiveletters.ui.res.theme.FiveLettersTheme
import com.example.fiveletters.ui.res.values.cancel
import com.example.fiveletters.ui.res.values.chooseLettersCount
import com.example.fiveletters.ui.res.values.close
import com.example.fiveletters.ui.res.values.helpCorrect
import com.example.fiveletters.ui.res.values.helpText
import com.example.fiveletters.ui.res.values.helpWrong
import com.example.fiveletters.ui.res.values.helpWrongPosition
import com.example.fiveletters.ui.res.values.isDarkMode
import com.example.fiveletters.ui.state.DialogParams
import com.example.fiveletters.ui.state.DialogType
import com.example.fiveletters.ui.state.UIState
import java.util.Locale

@Composable
fun DialogByParams(
    uiState: UIState,
    changeTheme: (isDark: Boolean) -> Unit,
    changeLocale: (locale: Locale) -> Unit
) {
    val dialogParams = uiState.dialogParams
    val newLettersCount = remember {
        mutableStateOf(uiState.game.lettersCount)
    }
    val localization = Vocabulary.localization
    AlertDialog(
        onDismissRequest = uiState.dialogParams.closeDialogAction,
        title = {
            dialogParams.title?.let {
                Text(text = it)
            }
        },
        text = {
            when (uiState.dialogParams.dialogType) {
                is DialogType.HelpDialog -> {
                    HelpDialogContent(dialogParams = uiState.dialogParams)
                }
                is DialogType.SettingsDialog -> {
                    SettingsDialogContent(
                        newLettersCountState = newLettersCount,
                        changeTheme = changeTheme
                    )
                }
                is DialogType.TextDialog -> {
                    TextDialogContent(dialog = uiState.dialogParams.dialogType)
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (dialogParams.dialogType is DialogType.SettingsDialog) {
                        dialogParams.confirmAction(newLettersCount.value)
                    } else {
                        dialogParams.confirmAction(null)
                    }
                }
            ) {
                Text(dialogParams.confirmBtnText ?: localization.close())
            }
        },
        dismissButton = {
            if (dialogParams.dialogType !is DialogType.HelpDialog) {
                TextButton(
                    onClick = dialogParams.closeDialogAction
                ) {
                    Text(localization.cancel())
                }
            }
        },
    )
}

@Composable
private fun HelpDialogContent(dialogParams: DialogParams) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        val localization = Vocabulary.localization
        val colorDesc = listOf(
            FiveLettersTheme.commonColorScheme.correctBoxColor to localization.helpCorrect(),
            FiveLettersTheme.commonColorScheme.wrongPositionBoxColor to localization.helpWrongPosition(),
            FiveLettersTheme.commonColorScheme.wrongBoxColor to localization.helpWrong(),
        )
        colorDesc.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Box(
                    modifier = Modifier
                        .border(2.dp, color = MaterialTheme.colorScheme.onSurface)
                        .size(32.dp)
                        .background(color = it.first)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = it.second,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = localization.helpText(),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun SettingsDialogContent(
    newLettersCountState: MutableState<LettersCount>,
    changeTheme: (isDark: Boolean) -> Unit
) {
    val localization = Vocabulary.localization
    val isDarkInitial = isSystemInDarkTheme()
    val isDarkTheme = remember {
        mutableStateOf(isDarkInitial)
    }
    Column(verticalArrangement = Arrangement.SpaceBetween) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = localization.isDarkMode(),
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Switch(
                modifier = Modifier.padding(8.dp),
                checked = isDarkTheme.value, onCheckedChange = { isDark ->
                    isDarkTheme.value = isDark
                    changeTheme(isDark)
                },
                thumbContent = {
                    Icon(
                        imageVector = Icons.Outlined.Check,
                        contentDescription = localization.isDarkMode()
                    )
                }
            )
        }
        Text(
            text = localization.chooseLettersCount(),
            style = MaterialTheme.typography.titleLarge
        )
        val radioOptions = listOf(5, 6, 7)
        Column(Modifier.selectableGroup()) {
            radioOptions.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = (text == newLettersCountState.value.count),
                            onClick = { newLettersCountState.value = lettersCountFromInt(text) },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == newLettersCountState.value.count),
                        onClick = null
                    )
                    Text(
                        text = text.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }

}

@Composable
private fun TextDialogContent(dialog: DialogType.TextDialog) {
    Text(text = dialog.text?.format(*dialog.textParams.toTypedArray()) ?: "")
}