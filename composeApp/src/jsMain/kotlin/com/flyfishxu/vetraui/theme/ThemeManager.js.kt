package com.flyfishxu.vetraui.theme

import androidx.compose.runtime.*
import kotlinx.browser.window

/**
 * JS implementation: uses CSS media query to detect dark mode
 *
 * This implementation:
 * - Checks the browser's `prefers-color-scheme` media query
 * - Automatically updates when system theme changes
 * - Returns true if system is in dark mode, false otherwise
 */
@Composable
actual fun isSystemInDarkTheme(): Boolean {
    var isDark by remember { mutableStateOf(checkSystemDarkMode()) }

    DisposableEffect(Unit) {
        val mediaQuery = window.matchMedia("(prefers-color-scheme: dark)")
        
        // Initial check
        isDark = mediaQuery.matches
        
        // Create listener for theme changes
        val listener: (dynamic) -> Unit = { event ->
            isDark = event.matches as Boolean
        }
        
        // Add listener (modern approach)
        try {
            mediaQuery.addEventListener("change", listener)
        } catch (e: Exception) {
            // Fallback for older browsers
            mediaQuery.asDynamic().addListener(listener)
        }
        
        onDispose {
            // Remove listener
            try {
                mediaQuery.removeEventListener("change", listener)
            } catch (e: Exception) {
                // Fallback for older browsers
                mediaQuery.asDynamic().removeListener(listener)
            }
        }
    }

    return isDark
}

/**
 * Check if system is currently in dark mode
 */
private fun checkSystemDarkMode(): Boolean {
    return try {
        window.matchMedia("(prefers-color-scheme: dark)").matches
    } catch (e: Exception) {
        false
    }
}

