package com.example.fiveletters.ui.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fiveletters.domain.model.Letter
import com.example.fiveletters.domain.model.LetterState
import com.example.fiveletters.ui.theme.FiveLettersTheme

@Composable
fun LetterBox(modifier: Modifier = Modifier, letter: Letter) {
    Box(
        modifier = modifier
            .padding(horizontal = 4.dp)
            .border(BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.primary))
            .background(colorByState(letter.state))
            .padding(all = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = letter.symbol, fontSize = 32.sp)
    }
}

@Composable
private fun colorByState(state: LetterState): Color = when (state) {
    LetterState.DEFAULT -> FiveLettersTheme.commonColorScheme.defaultBoxColor
    LetterState.CORRECT -> FiveLettersTheme.commonColorScheme.correctBoxColor
    LetterState.WRONG_POSITION -> FiveLettersTheme.commonColorScheme.wrongPositionBoxColor
    LetterState.WRONG -> FiveLettersTheme.commonColorScheme.wrongBoxColor
}


@Composable
fun LettersRow(
    modifier: Modifier = Modifier,
    word: List<Letter>,
    count: Int,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(count) {
            val letter = word.elementAtOrNull(it)
            LetterBox(
                modifier = Modifier.weight(1f, fill = true),
                letter = letter ?: Letter(" ")
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0x989a82)
@Composable
fun LettersRowPreview() {
    Column() {
        LettersRow(word = listOf(), count = 5)
        LettersRow(
            word = listOf(Letter("L"), Letter("I"), Letter("M"), Letter("B"), Letter("O")),
            count = 5
        )
    }
}