package com.example.fiveletters.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Letter(modifier: Modifier = Modifier, label: String) {
    val shape = RoundedCornerShape(4.dp)
    Box(
        modifier = modifier
            .clip(shape)
            .background(Color.White)
            .padding(vertical = 12.dp, horizontal = 8.dp), contentAlignment = Alignment.Center
    ) {
        Text(text = label, fontSize = 32.sp)
    }
}

@Composable
fun LettersRow(letters: List<String>, count: Int) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .background(color = Color.Gray)
    ) {
        repeat(5) {
            val letter = letters.elementAtOrNull(it)
            Letter(modifier = Modifier.weight(1f, fill = false), label = letter ?: "")
        }
    }
}