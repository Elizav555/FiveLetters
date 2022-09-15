package com.example.fiveletters.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fiveletters.domain.model.keyboard.KeyClick
import com.example.fiveletters.domain.model.keyboard.KeyType
import com.example.fiveletters.ui.model.UIState
import com.example.fiveletters.ui.widgets.KeyboardWidget
import com.example.fiveletters.ui.widgets.LettersRow

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    uiState: UIState,
    keyClickMap: Map<KeyType, KeyClick>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(all = 8.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            repeat(uiState.game.attempts) {
                val word = uiState.game.history.getOrNull(it) ?: uiState.game.word
                LettersRow(
                    word = word,
                    count = uiState.game.lettersCount.count,
                )
            }
        }
        KeyboardWidget(
            keyboard = uiState.game.keyboard, keyClickMap = keyClickMap
        )
    }
}