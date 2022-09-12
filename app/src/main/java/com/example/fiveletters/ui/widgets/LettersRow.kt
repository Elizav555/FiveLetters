package com.example.fiveletters.ui.widgets

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fiveletters.domain.model.Letter
import com.example.fiveletters.domain.model.LetterState
import com.example.fiveletters.domain.model.Word
import com.example.fiveletters.ui.theme.FiveLettersTheme

@Composable
fun LetterBox(modifier: Modifier = Modifier, letter: Letter, index: Int, count: Int) {
    val rotation by animateFloatAsState(
        targetValue = if (letter.state == LetterState.DEFAULT) 0f else 360f,
        animationSpec = tween(
            delayMillis = 100 * index,
            durationMillis = 1500,
            easing = LinearEasing
        ),
    )
    val animatedColor = animateColorAsState(
        targetValue = colorByState(letter.state),
        animationSpec = tween(
            delayMillis = 100 * index,
            durationMillis = 1200,
            easing = LinearOutSlowInEasing
        )
    )
    Box(
        modifier = modifier
            .graphicsLayer { rotationY = rotation }
            .padding(horizontal = 4.dp)
            .border(BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.primary))
            .background(animatedColor.value)
            .padding(all = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = letter.symbol, fontSize = if (count == 7) 28.sp else 32.sp)
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
    word: Word,
    count: Int,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(count) {
            val letter = word.letters.elementAtOrNull(it)
            LetterBox(
                modifier = Modifier.weight(1f, fill = true),
                letter = letter ?: Letter(" "),
                index = it,
                count = count
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0x989a82)
@Composable
fun LettersRowPreview() {
    Column {
        LettersRow(word = Word(), count = 5)
        LettersRow(
            word = Word(
                listOf(
                    Letter("L"),
                    Letter("I"),
                    Letter("M"),
                    Letter("B"),
                    Letter("O")
                )
            ),
            count = 5
        )
    }
}