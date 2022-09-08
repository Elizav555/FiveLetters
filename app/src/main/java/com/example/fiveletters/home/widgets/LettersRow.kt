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
import com.example.fiveletters.home.utils.Letter
import com.example.fiveletters.home.utils.LetterState

@Composable
fun LetterBox(modifier: Modifier = Modifier, letter: Letter) {
    val shape = RoundedCornerShape(4.dp)
    Box(
        modifier = modifier
            .clip(shape)
            .background(getColorByState(letter.state))
            .padding(vertical = 12.dp, horizontal = 8.dp), contentAlignment = Alignment.Center
    ) {
        Text(text = letter.symbol, fontSize = 32.sp)
    }
}

private fun getColorByState(state: LetterState): Color = when (state) {
    LetterState.DEFAULT -> TODO()
    LetterState.CORRECT -> TODO()
    LetterState.WRONG_POSITION -> TODO()
    LetterState.WRONG -> TODO()
}


@Composable
fun LettersRow(letters: List<Letter>, count: Int) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .background(color = Color.Gray)
    ) {
        repeat(count) {
            val letter = letters.elementAtOrNull(it)
            LetterBox(modifier = Modifier.weight(1f, fill = false), letter = letter ?: Letter(""))
        }
    }
}