package com.example.fiveletters.home

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    HomeScreen(onBtnClicked = {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    onBtnClicked: (Any?) -> Unit
) {
    Scaffold() {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            KeyRow(keys = listOf("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"))
            KeyRow(keys = listOf("A", "S", "D", "F", "G", "H", "J", "K", "L"))
            KeyRow(keys = listOf("Z", "X", "C", "V", "B", "N", "M"))

        }
    }
}

@Composable
fun Key(modifier: Modifier = Modifier, label: String, onClick: () -> Unit) {
    val shape = RoundedCornerShape(4.dp)
    Box(
        modifier = modifier
            .padding(2.dp)
            .clip(shape)
            .clickable(onClick = onClick)
            .background(Color.White)
            .padding(vertical = 12.dp, horizontal = 8.dp), contentAlignment = Alignment.Center
    ) {
        Text(text = label, fontSize = 20.sp)
    }
}

@Composable
fun KeyRow(keys: List<String>) {
    Row(
        modifier = Modifier.wrapContentWidth()
            .background(color = Color.Gray)
    ) {
        keys.forEach {
            Key(modifier = Modifier.weight(1f, fill = false), label = it, onClick = { })
        }
    }
}