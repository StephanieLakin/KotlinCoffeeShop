package com.example.coffeeshop.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Accent,
    secondary = LightBrown,
    tertiary = Brown,
    background = DarkBrown,
    surface = Brown,
    onPrimary = DarkBrown,
    onSecondary = DarkBrown,
    onTertiary = White,
    onBackground = White,
    onSurface = White,
)

private val LightColorScheme = lightColorScheme(
    primary = Brown,
    secondary = LightBrown,
    tertiary = Accent,
    background = Beige,
    surface = White,
    onPrimary = White,
    onSecondary = DarkBrown,
    onTertiary = DarkBrown,
    onBackground = DarkBrown,
    onSurface = DarkBrown,
)

@Composable
fun CoffeeShopTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

    val view = LocalView.current
    val activity = view.context as? Activity

    activity?.let {
        WindowCompat.getInsetsController(it.window, view).isAppearanceLightStatusBars = !darkTheme
    }

    MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography,
      content = content
    )
}