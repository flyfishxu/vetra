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
 * @param darkMode Whether to use dark mode
 * @param colors Custom color scheme (defaults to built-in light/dark schemes)
 * @param typography Custom typography (defaults to DefaultVetraTypography)
 * @param shapes Custom shapes (defaults to DefaultVetraShapes)
 * @param shadows Custom shadows (defaults to DefaultVetraShadows)
 * @param shadowConfig Custom shadow configuration (defaults based on darkMode)
 * @param content The content to theme
 */
@Composable
fun VetraTheme(
    darkMode: Boolean = false,
    colors: VetraColorScheme = if (darkMode) VetraDarkColorScheme else VetraLightColorScheme,
    typography: VetraTypography = DefaultVetraTypography,
    shapes: VetraShapes = DefaultVetraShapes,
    shadows: VetraShadows = DefaultVetraShadows,
    shadowConfig: ShadowConfig = if (darkMode) DarkModeShadowConfig else LightModeShadowConfig,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalVetraColorScheme provides colors,
        LocalVetraTypography provides typography,
        LocalVetraShapes provides shapes,
        LocalVetraShadows provides shadows,
        LocalVetraShadowConfig provides shadowConfig,
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

    /**
     * Retrieves the current [ShadowConfig] at the call site's position in the hierarchy.
     */
    val shadowConfig: ShadowConfig
        @Composable
        @ReadOnlyComposable
        get() = LocalVetraShadowConfig.current
}
