package com.flyfishxu.vetraui.core.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

/**
 * Vetra Theme
 *
 * The main theming composable for Vetra UI. Provides a complete design system
 * independent from Material Design, with its own color, typography, shape, and shadow systems.
 *
 * Philosophy:
 * - Independent design system (not based on Material)
 * - Intuitive, semantic naming
 * - Beautiful by default
 * - Easy to customize
 * - Accessible and modern
 *
 * Usage:
 * ```
 * VetraTheme(darkMode = false) {
 *     // Your app content
 * }
 * ```
 *
 * Custom colors:
 * ```
 * VetraTheme(
 *     colors = VetraLightColorScheme.copy(
 *         brand = Color(0xFF1E40AF)
 *     )
 * ) {
 *     // Your app content
 * }
 * ```
 *
 * @param darkMode Whether to use dark mode
 * @param colors Custom color scheme (defaults to built-in light/dark schemes)
 * @param typography Custom typography (defaults to DefaultVetraTypography)
 * @param shapes Custom shapes (defaults to DefaultVetraShapes)
 * @param shadows Custom shadows (defaults to DefaultVetraShadows)
 * @param content The content to theme
 */
@Composable
fun VetraTheme(
    darkMode: Boolean = false,
    colors: VetraColorScheme = if (darkMode) VetraDarkColorScheme else VetraLightColorScheme,
    typography: VetraTypography = DefaultVetraTypography,
    shapes: VetraShapes = DefaultVetraShapes,
    shadows: VetraShadows = DefaultVetraShadows,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalVetraColorScheme provides colors,
        LocalVetraTypography provides typography,
        LocalVetraShapes provides shapes,
        LocalVetraShadows provides shadows,
        content = content
    )
}

/**
 * Contains functions to access the current theme values
 */
object VetraTheme {
    /**
     * Retrieves the current [VetraColorScheme] at the call site's position in the hierarchy.
     */
    val colors: VetraColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalVetraColorScheme.current

    /**
     * Retrieves the current [VetraTypography] at the call site's position in the hierarchy.
     */
    val typography: VetraTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalVetraTypography.current

    /**
     * Retrieves the current [VetraShapes] at the call site's position in the hierarchy.
     */
    val shapes: VetraShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalVetraShapes.current

    /**
     * Retrieves the current [VetraShadows] at the call site's position in the hierarchy.
     */
    val shadows: VetraShadows
        @Composable
        @ReadOnlyComposable
        get() = LocalVetraShadows.current
}
