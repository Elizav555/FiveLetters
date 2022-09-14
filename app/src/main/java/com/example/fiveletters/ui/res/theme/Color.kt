package com.example.fiveletters.ui.res.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
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
import com.example.fiveletters.ui.res.values.dark_shadow
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
import com.example.fiveletters.ui.res.values.light_shadow
import com.example.fiveletters.ui.res.values.light_surface
import com.example.fiveletters.ui.res.values.light_surfaceVariant
import com.example.fiveletters.ui.res.values.light_tertiary
import com.example.fiveletters.ui.res.values.light_tertiaryContainer


data class CommonColorScheme(
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val primaryInverse: Color,

    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,

    val tertiary: Color,
    val onTertiary: Color,
    val tertiaryContainer: Color,
    val onTertiaryContainer: Color,

    val error: Color,
    val onError: Color,
    val errorContainer: Color,
    val onErrorContainer: Color,

    val outline: Color,

    val background: Color,
    val onBackground: Color,

    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val surfaceInverse: Color,
    val onSurfaceInverse: Color,
    val surfaceTint: Color = primary,

    val transparent: Color = Color(0x00000000),

    val divider: Color = Color(0xFF373C49),

    val scrim: Color,
    val outlineVariant: Color,

    val inverseOnSurface: Color,
    val inverseSurface: Color,
    val inversePrimary: Color,

    //lettersBoxes colors
    val defaultBoxColor: Color = Color.Transparent,
    val correctBoxColor: Color = Color(0xC069E10F),
    val wrongPositionBoxColor: Color = Color(0xBFE1DE0F),
    val wrongBoxColor: Color = Color(0xBF373837),
) {

    fun toColorScheme() = ColorScheme(
        primary = primary,
        onPrimary = onPrimary,
        primaryContainer = primaryContainer,
        onPrimaryContainer = onPrimaryContainer,
        inversePrimary = primaryInverse,

        secondary = secondary,
        onSecondary = onSecondary,
        secondaryContainer = secondaryContainer,
        onSecondaryContainer = onSecondaryContainer,

        tertiary = tertiary,
        onTertiary = onTertiary,
        tertiaryContainer = tertiaryContainer,
        onTertiaryContainer = onTertiaryContainer,

        background = background,
        onBackground = onBackground,
        surface = surface,
        onSurface = onSurface,
        surfaceVariant = surfaceVariant,
        onSurfaceVariant = onSurfaceVariant,
        inverseSurface = surfaceInverse,
        inverseOnSurface = onSurfaceInverse,
        surfaceTint = surfaceTint,

        error = error,
        onError = onError,
        errorContainer = errorContainer,
        onErrorContainer = onErrorContainer,

        outline = outline,
        scrim = scrim,
        outlineVariant = outlineVariant
    )
}

internal val LocalCommonColors = staticCompositionLocalOf { LightCommonColorScheme }

internal val LightCommonColorScheme = CommonColorScheme(
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
    onSurfaceInverse = light_onSurfaceVariant,
    outlineVariant = light_outline,
    primaryInverse = light_inversePrimary,
    scrim = light_shadow,
    surfaceInverse = light_inverseSurface
)

internal val DarkCommonColorScheme = CommonColorScheme(

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
    onSurfaceInverse = dark_onSurfaceVariant,
    outlineVariant = dark_outline,
    primaryInverse = dark_inversePrimary,
    scrim = dark_shadow,
    surfaceInverse = dark_inverseSurface
)
