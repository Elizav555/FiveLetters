package com.example.fiveletters.home.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.example.fiveletters.home.utils.Letter
import com.example.fiveletters.home.utils.LetterState
import com.example.fiveletters.ui.theme.FiveLettersTheme

@Composable
fun LetterBox(modifier: Modifier = Modifier, letter: Letter) {
    val shape = RoundedCornerShape(4.dp)
    Box(
        modifier = modifier
            .defaultMinSize(minWidth = 64.dp)
            .border(BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.primary))
            .clip(shape)
            .background(getColorByState(letter.state))
            .padding(vertical = 12.dp, horizontal = 8.dp), contentAlignment = Alignment.Center
    ) {
        Text(text = letter.symbol, fontSize = 32.sp)
    }
}

@Composable
private fun getColorByState(state: LetterState): Color = when (state) {
    LetterState.DEFAULT -> FiveLettersTheme.commonColorScheme.defaultBoxColor
    LetterState.CORRECT -> FiveLettersTheme.commonColorScheme.correctBoxColor
    LetterState.WRONG_POSITION -> FiveLettersTheme.commonColorScheme.wrongPositionBoxColor
    LetterState.WRONG -> FiveLettersTheme.commonColorScheme.wrongBoxColor
}


@Composable
fun LettersRow(
    currentWord: List<Letter>,
    history: List<List<Letter>>,
    count: Int,
    attemptsCount: Int
) {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        repeat(attemptsCount) { attempts ->
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .background(color = Color.Gray)
                    .padding(8.dp)
            ) {
                val word = history.getOrNull(attempts) ?: currentWord
                repeat(count) {
                    val letter = word.elementAtOrNull(it)
                    LetterBox(
                        modifier = Modifier.weight(1f, fill = false),
                        letter = letter ?: Letter(" ")
                    )
                }
            }
        }
    }
}