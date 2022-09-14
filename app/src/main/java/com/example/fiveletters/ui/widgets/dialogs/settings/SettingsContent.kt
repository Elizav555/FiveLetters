package com.example.fiveletters.ui.widgets.dialogs.settings

import Vocabulary.localization
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.fiveletters.domain.model.letter.LettersCount
import com.example.fiveletters.ui.res.values.chooseLettersCount
import com.example.fiveletters.ui.res.values.chooseLocale
import com.example.fiveletters.ui.res.values.isDarkMode
import java.util.Locale
import supportedLocales

@Composable
fun SettingsDialogContent(
    lettersCountState: MutableState<LettersCount>,
    isDarkTheme: MutableState<Boolean>,
    currentLocale: MutableState<Locale>,
    changeTheme: (isDark: Boolean) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.verticalScroll(
            rememberScrollState()
        )
    ) {

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

        val countOptions = LettersCount.values()
        Column(Modifier.selectableGroup()) {
            countOptions.forEach { lettersCount ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = (lettersCount == lettersCountState.value),
                            onClick = { lettersCountState.value = lettersCount },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (lettersCount == lettersCountState.value),
                        onClick = null
                    )
                    Text(
                        text = lettersCount.count.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }

        Text(
            text = localization.chooseLocale(),
            style = MaterialTheme.typography.titleLarge
        )

        val localeOptions = supportedLocales
        Column(Modifier.selectableGroup()) {
            localeOptions.forEach { locale ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = (locale == currentLocale.value),
                            onClick = { currentLocale.value = locale },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (locale == currentLocale.value),
                        onClick = null
                    )
                    Text(
                        text = locale.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}