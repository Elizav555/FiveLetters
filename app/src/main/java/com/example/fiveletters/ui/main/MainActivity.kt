package com.example.fiveletters.ui.main

import Localization
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
import com.example.fiveletters.ui.res.theme.FiveLettersTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

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
    val settingsState by viewModel.settingsState.collectAsState()
    val changeTheme = { isDark: Boolean -> viewModel.onEvent(MainEvent.ChangeThemeEvent(isDark)) }
    val changeLocale = { locale: Locale -> viewModel.onEvent(MainEvent.ChangeLocaleEvent(locale)) }
    FiveLettersTheme(darkTheme = settingsState.isDarkMode ?: isSystemInDarkTheme()) {
        Localization(locale = settingsState.locale) {
            HomeScreen(changeTheme, changeLocale, settingsState)
        }
    }
}