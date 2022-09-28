package com.kagamiapps.tofassistant.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = AquaPastel,
    primaryVariant = AquaDark,
    secondary = AquaLight
)

private val LightColorPalette = lightColors(
    primary = AquaPastelLight,
    primaryVariant = AquaDark,
    secondary = AquaLight

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun TowerOfFantasyAssistantTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
//    I'm not allowing non-dark-mode here
//    val colors = if (darkTheme) {
//        DarkColorPalette
//    } else {
//        LightColorPalette
//    }

    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = AquaDark
    )
}

