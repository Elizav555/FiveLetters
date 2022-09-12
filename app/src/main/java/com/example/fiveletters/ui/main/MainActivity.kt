package com.example.fiveletters.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fiveletters.ui.events.MainEvent
import com.example.fiveletters.ui.home.HomeScreen
import com.example.fiveletters.ui.theme.FiveLettersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainActivityContent()
        }
    }
}

@Composable
fun MainActivityContent() {
    val viewModel = hiltViewModel<MainViewModel>()
    val isDarkModeState by viewModel.isDarkModeState.collectAsState()
    val changeTheme = { isDark: Boolean -> viewModel.onEvent(MainEvent.ChangeThemeEvent(isDark)) }
    FiveLettersTheme(darkTheme = isDarkModeState.isDarkMode ?: isSystemInDarkTheme()) {
        HomeScreen(changeTheme)
    }
}