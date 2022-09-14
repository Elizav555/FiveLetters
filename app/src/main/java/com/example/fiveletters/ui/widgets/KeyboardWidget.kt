package com.example.fiveletters.ui.widgets

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fiveletters.domain.model.keyboard.Key
import com.example.fiveletters.domain.model.keyboard.KeyClick
import com.example.fiveletters.domain.model.keyboard.KeyType
import com.example.fiveletters.domain.model.keyboard.Keyboard
import com.example.fiveletters.ui.res.theme.FiveLettersTheme

@Composable
fun KeyBox(modifier: Modifier = Modifier, key: Key, keyClick: KeyClick) {
    Text(
        modifier = modifier
            .clip(shape = MaterialTheme.shapes.small)
            .shadow(elevation = 2.dp)
            .clickable(onClick = { keyClick(key.symbol) })
            .border(
                width = 1.dp,
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colorScheme.outline
            )
            .padding(10.dp),
        text = key.symbol,
        textAlign = TextAlign.Center,
        fontSize = 22.sp,
        color = if (key.isWrong) FiveLettersTheme.commonColorScheme.wrongBoxColor else MaterialTheme.colorScheme.primary
    )
}

@Composable
fun KeyRow(modifier: Modifier = Modifier, keys: List<Key>, keyClickMap: Map<KeyType, KeyClick>) {
    Row(
        modifier = modifier
    ) {
        keys.forEach {
            KeyBox(
                modifier = Modifier
                    .padding(2.dp)
                    .weight(1f, fill = false),
                key = it,
                keyClick = keyClickMap[it.keyType] ?: {}
            )
        }
    }
}

@Composable
fun KeyboardWidget(
    modifier: Modifier = Modifier,
    keyboard: Keyboard,
    keyClickMap: Map<KeyType, KeyClick>
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        keyboard.rows.forEach {
            KeyRow(keys = it.keys, keyClickMap = keyClickMap)
        }
    }
}