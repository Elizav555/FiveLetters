package com.example.fiveletters.home.widgets

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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fiveletters.home.utils.Key
import com.example.fiveletters.home.utils.myKeyboardKeys

@Composable
fun KeyBox(modifier: Modifier = Modifier, key: Key) {
    Text(
        modifier = modifier
            .clickable(onClick = { key.keyClick(key.symbol) })
            .border(
                width = 2.dp,
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colorScheme.onSurface
            )
            .padding(8.dp),
        text = key.symbol,
        textAlign = TextAlign.Center,
        fontSize = 22.sp,
    )
}

@Composable
fun KeyRow(modifier: Modifier = Modifier, keys: List<Key>) {
    Row(
        modifier = modifier
    ) {
        keys.forEach {
            KeyBox(
                modifier = Modifier
                    .padding(2.dp)
                    .weight(1f, fill = false), key = it
            )
        }
    }
}

@Composable
fun Keyboard(modifier: Modifier = Modifier, keys: List<List<Key>>) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        keys.forEach {
            KeyRow(keys = it)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0x989a82)
@Composable
fun KeyboardPreview() {
    Keyboard(keys = myKeyboardKeys({}, {}, {}))
}