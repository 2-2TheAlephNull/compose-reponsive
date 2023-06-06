package com.github.lifof31415926535.responsive

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

val LocalWindowSizeClasses = compositionLocalOf { windowSizeClasses() }

@Composable
fun WindowSizeClassesProvider(
    content: @Composable () -> Unit
) {
    val windowSizeClasses = rememberWindowSizeClasses()
    
    CompositionLocalProvider(
        LocalWindowSizeClasses provides windowSizeClasses
    ) {
        content()
    }
}