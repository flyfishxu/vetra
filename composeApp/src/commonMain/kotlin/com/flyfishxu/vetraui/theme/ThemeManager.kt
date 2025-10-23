package com.flyfishxu.vetraui.theme

import androidx.compose.runtime.Composable

/**
 * Theme preference modes
 */
enum class ThemeMode {
    LIGHT,      // Always light
    DARK,       // Always dark
    SYSTEM      // Follow system
}

/**
 * Get system's dark mode state
 *
 * This is a platform-specific implementation that detects
 * whether the system is currently in dark mode.
 */
@Composable
expect fun isSystemInDarkTheme(): Boolean

