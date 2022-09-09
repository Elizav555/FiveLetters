package com.example.fiveletters.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fiveletters.home.utils.Key

@Composable
fun KeyBox(modifier: Modifier = Modifier, key: Key) {
    val shape = RoundedCornerShape(4.dp)
    Box(
        modifier = modifier
            .clip(shape)
            .clickable(onClick = { key.keyClick(key.symbol) })
            .background(color=MaterialTheme.colorScheme.onSecondary)
            .padding(8.dp)
            .padding(vertical = 12.dp, horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = key.symbol, fontSize = 20.sp)
    }
}

@Composable
fun KeyRow(keys: List<Key>) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
    ) {
        keys.forEach {
            KeyBox(modifier = Modifier.weight(1f, fill = false), key = it)
        }
    }
}

@Composable
fun Keyboard(keys: List<List<Key>>) {
    Column(
        modifier = Modifier
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