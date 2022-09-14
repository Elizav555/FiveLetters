package com.example.fiveletters.ui.widgets.dialogs.help

import Vocabulary.localization
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fiveletters.ui.res.theme.FiveLettersTheme
import com.example.fiveletters.ui.res.values.helpCorrect
import com.example.fiveletters.ui.res.values.helpText
import com.example.fiveletters.ui.res.values.helpWrong
import com.example.fiveletters.ui.res.values.helpWrongPosition


@Composable
fun HelpDialogContent() {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
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
