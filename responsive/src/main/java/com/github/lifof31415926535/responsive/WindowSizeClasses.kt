package com.github.lifof31415926535.responsive

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private fun isInRange(value: Dp, min: Dp?, max: Dp?): Boolean {
    if (min == null && max == null) throw Exception("The arguments for params 'min' and 'max' cannot both be null.")

    if (min == null) return value <= max as Dp

    if (max == null) return value >= min

    return min <= value && value <= max
}


sealed class WindowWidthClass(
    val minWidth: Dp? = null, val maxWidth: Dp? = null
) {
    object Compact : WindowWidthClass(maxWidth = 599.9999999999.dp)
    object Medium : WindowWidthClass(minWidth = 600.dp, maxWidth = 839.999999999.dp)
    object Expanded : WindowWidthClass(minWidth = 840.dp)

    companion object {
        private val windowWidthClasses = listOf(Compact, Medium, Expanded)

        internal fun current(currentWidowWidth: Dp): WindowWidthClass {
            for (widthClass in windowWidthClasses) {
                if (isInRange(
                        currentWidowWidth,
                        widthClass.minWidth,
                        widthClass.maxWidth
                    )
                ) return widthClass
            }

            return Medium

        }

    }
}

sealed class WindowHeightClass(
    val minHeight: Dp? = null, val maxHeight: Dp? = null
) {
    object Compact : WindowHeightClass(maxHeight = 479.999999999.dp)
    object Medium : WindowHeightClass(minHeight = 480.dp, maxHeight = 899.99999999.dp)
    object Expanded : WindowHeightClass(minHeight = 900.dp)

    companion object {
        private val windowHeightClasses = listOf(Compact, Medium, Expanded)

        internal fun current(currentWindowHeight: Dp): WindowHeightClass {
            for (heightClass in windowHeightClasses) {
                if (isInRange(
                        currentWindowHeight,
                        heightClass.minHeight,
                        heightClass.maxHeight
                    )
                ) return heightClass
            }

            return Medium
        }
    }
}

data class WindowSizeClasses(
    val heightClass: WindowHeightClass,
    val widthClass: WindowWidthClass
)

internal fun windowSizeClasses(
    currentWindowHeight: Dp = 600.dp,
    currentWidowWidth: Dp = 480.dp
): WindowSizeClasses =
    WindowSizeClasses(
        WindowHeightClass.current(currentWindowHeight),
        WindowWidthClass.current(currentWidowWidth)
    )

@Composable
internal fun rememberWindowSizeClasses(
    currentWindowHeight: Dp = LocalConfiguration.current.screenHeightDp.dp,
    currentWidowWidth: Dp = LocalConfiguration.current.screenWidthDp.dp
) = remember(currentWindowHeight, currentWidowWidth) {
    windowSizeClasses(
        currentWindowHeight,
        currentWidowWidth
    )
}





