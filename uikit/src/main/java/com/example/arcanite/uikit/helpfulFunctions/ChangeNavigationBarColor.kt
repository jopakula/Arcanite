package com.example.arcanite.uikit.helpfulFunctions

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun ChangeNavigationBarColor(color: Color) {
    val view = LocalView.current
    val isDarkTheme = isSystemInDarkTheme()
    SideEffect {
        val window = view.context.findActivity().window
        window.navigationBarColor = color.toArgb()
        WindowCompat.getInsetsController(window, view)
            .isAppearanceLightNavigationBars = !isDarkTheme
    }
}

fun Context.findActivity(): Activity = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> throw IllegalStateException("Context is not an Activity")
}
