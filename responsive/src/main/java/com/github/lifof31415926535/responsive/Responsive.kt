package com.github.lifof31415926535.responsive

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

enum class ResponsiveDimension {
    HEIGHT, WIDTH
}

@Composable
fun Responsive(
    modifier: Modifier = Modifier,
    responsiveDimension: ResponsiveDimension = ResponsiveDimension.WIDTH,
    compactContent: @Composable () -> Unit,
    mediumContent: @Composable () -> Unit,
    expandedContent: @Composable () -> Unit
) {
    Box(modifier = modifier) {
        when (responsiveDimension) {
            ResponsiveDimension.WIDTH -> {
                when (LocalWindowSizeClasses.current.widthClass) {
                    WindowWidthClass.Compact -> compactContent()
                    WindowWidthClass.Medium -> mediumContent()
                    WindowWidthClass.Expanded -> expandedContent()
                }
            }

            ResponsiveDimension.HEIGHT -> {
                when (LocalWindowSizeClasses.current.heightClass) {
                    WindowHeightClass.Compact -> compactContent()
                    WindowHeightClass.Medium -> mediumContent()
                    WindowHeightClass.Expanded -> expandedContent()
                }
            }
        }
    }
}

@Composable
fun ResponsiveCompactOrDefault(
    modifier: Modifier = Modifier,
    responsiveDimension: ResponsiveDimension = ResponsiveDimension.WIDTH,
    defaultContent: @Composable () -> Unit,
    compactContent: @Composable () -> Unit,
) {
    Responsive(
        modifier = modifier,
        responsiveDimension = responsiveDimension,
        compactContent = { compactContent() },
        mediumContent = { defaultContent() },
        expandedContent = { defaultContent() }
    )
}

@Composable
fun ResponsiveExpandedOrDefault(
    modifier: Modifier = Modifier,
    responsiveDimension: ResponsiveDimension,
    defaultContent: () -> Unit,
    expandedContent: () -> Unit
) {
    Responsive(
        modifier = modifier,
        responsiveDimension = responsiveDimension,
        compactContent = { defaultContent() },
        mediumContent = { defaultContent() },
        expandedContent = { expandedContent() }
    )
}