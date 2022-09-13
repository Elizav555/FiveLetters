package com.example.fiveletters.ui.res.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import com.example.fiveletters.ui.res.values.dark_background
import com.example.fiveletters.ui.res.values.dark_error
import com.example.fiveletters.ui.res.values.dark_errorContainer
import com.example.fiveletters.ui.res.values.dark_inverseOnSurface
import com.example.fiveletters.ui.res.values.dark_inversePrimary
import com.example.fiveletters.ui.res.values.dark_inverseSurface
import com.example.fiveletters.ui.res.values.dark_onBackground
import com.example.fiveletters.ui.res.values.dark_onError
import com.example.fiveletters.ui.res.values.dark_onErrorContainer
import com.example.fiveletters.ui.res.values.dark_onPrimary
import com.example.fiveletters.ui.res.values.dark_onPrimaryContainer
import com.example.fiveletters.ui.res.values.dark_onSecondary
import com.example.fiveletters.ui.res.values.dark_onSecondaryContainer
import com.example.fiveletters.ui.res.values.dark_onSurface
import com.example.fiveletters.ui.res.values.dark_onSurfaceVariant
import com.example.fiveletters.ui.res.values.dark_onTertiary
import com.example.fiveletters.ui.res.values.dark_onTertiaryContainer
import com.example.fiveletters.ui.res.values.dark_outline
import com.example.fiveletters.ui.res.values.dark_primary
import com.example.fiveletters.ui.res.values.dark_primaryContainer
import com.example.fiveletters.ui.res.values.dark_secondary
import com.example.fiveletters.ui.res.values.dark_secondaryContainer
import com.example.fiveletters.ui.res.values.dark_surface
import com.example.fiveletters.ui.res.values.dark_surfaceVariant
import com.example.fiveletters.ui.res.values.dark_tertiary
import com.example.fiveletters.ui.res.values.dark_tertiaryContainer
import com.example.fiveletters.ui.res.values.light_background
import com.example.fiveletters.ui.res.values.light_error
import com.example.fiveletters.ui.res.values.light_errorContainer
import com.example.fiveletters.ui.res.values.light_inverseOnSurface
import com.example.fiveletters.ui.res.values.light_inversePrimary
import com.example.fiveletters.ui.res.values.light_inverseSurface
import com.example.fiveletters.ui.res.values.light_onBackground
import com.example.fiveletters.ui.res.values.light_onError
import com.example.fiveletters.ui.res.values.light_onErrorContainer
import com.example.fiveletters.ui.res.values.light_onPrimary
import com.example.fiveletters.ui.res.values.light_onPrimaryContainer
import com.example.fiveletters.ui.res.values.light_onSecondary
import com.example.fiveletters.ui.res.values.light_onSecondaryContainer
import com.example.fiveletters.ui.res.values.light_onSurface
import com.example.fiveletters.ui.res.values.light_onSurfaceVariant
import com.example.fiveletters.ui.res.values.light_onTertiary
import com.example.fiveletters.ui.res.values.light_onTertiaryContainer
import com.example.fiveletters.ui.res.values.light_outline
import com.example.fiveletters.ui.res.values.light_primary
import com.example.fiveletters.ui.res.values.light_primaryContainer
import com.example.fiveletters.ui.res.values.light_secondary
import com.example.fiveletters.ui.res.values.light_secondaryContainer
import com.example.fiveletters.ui.res.values.light_surface
import com.example.fiveletters.ui.res.values.light_surfaceVariant
import com.example.fiveletters.ui.res.values.light_tertiary
import com.example.fiveletters.ui.res.values.light_tertiaryContainer

private val LightColorScheme = lightColorScheme(
    primary = light_primary,
    onPrimary = light_onPrimary,
    primaryContainer = light_primaryContainer,
    onPrimaryContainer = light_onPrimaryContainer,
    secondary = light_secondary,
    onSecondary = light_onSecondary,
    secondaryContainer = light_secondaryContainer,
    onSecondaryContainer = light_onSecondaryContainer,
    tertiary = light_tertiary,
    onTertiary = light_onTertiary,
    tertiaryContainer = light_tertiaryContainer,
    onTertiaryContainer = light_onTertiaryContainer,
    error = light_error,
    errorContainer = light_errorContainer,
    onError = light_onError,
    onErrorContainer = light_onErrorContainer,
    background = light_background,
    onBackground = light_onBackground,
    surface = light_surface,
    onSurface = light_onSurface,
    surfaceVariant = light_surfaceVariant,
    onSurfaceVariant = light_onSurfaceVariant,
    outline = light_outline,
    inverseOnSurface = light_inverseOnSurface,
    inverseSurface = light_inverseSurface,
    inversePrimary = light_inversePrimary,
    // surfaceTint = light_surfaceTint,
)


private val DarkColorScheme = darkColorScheme(
    primary = dark_primary,
    onPrimary = dark_onPrimary,
    primaryContainer = dark_primaryContainer,
    onPrimaryContainer = dark_onPrimaryContainer,
    secondary = dark_secondary,
    onSecondary = dark_onSecondary,
    secondaryContainer = dark_secondaryContainer,
    onSecondaryContainer = dark_onSecondaryContainer,
    tertiary = dark_tertiary,
    onTertiary = dark_onTertiary,
    tertiaryContainer = dark_tertiaryContainer,
    onTertiaryContainer = dark_onTertiaryContainer,
    error = dark_error,
    errorContainer = dark_errorContainer,
    onError = dark_onError,
    onErrorContainer = dark_onErrorContainer,
    background = dark_background,
    onBackground = dark_onBackground,
    surface = dark_surface,
    onSurface = dark_onSurface,
    surfaceVariant = dark_surfaceVariant,
    onSurfaceVariant = dark_onSurfaceVariant,
    outline = dark_outline,
    inverseOnSurface = dark_inverseOnSurface,
    inverseSurface = dark_inverseSurface,
    inversePrimary = dark_inversePrimary,
    // surfaceTint = dark_surfaceTint,
)

@Composable
fun FiveLettersTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colorScheme = when {
        dynamicColor && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
        dynamicColor && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val commonColors = when {
        darkTheme -> DarkCommonColorScheme
        else -> LightCommonColorScheme
    }

    CompositionLocalProvider(
        LocalCommonColors provides commonColors,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content,
        )
    }
}

object FiveLettersTheme {
    val commonColorScheme: CommonColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalCommonColors.current
}