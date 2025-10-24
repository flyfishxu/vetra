package com.flyfishxu.vetraui.theme

import androidx.compose.runtime.*
import kotlinx.browser.window
import org.w3c.dom.events.Event

/**
 * WasmJS implementation: uses CSS media query to detect dark mode
 *
 * This implementation:
 * - Checks the browser's `prefers-color-scheme` media query
 * - Automatically updates when system theme changes
 * - Returns true if system is in dark mode, false otherwise
 * - Compatible with Wasm restrictions (no dynamic types)
 */
@Composable
actual fun isSystemInDarkTheme(): Boolean {
    var isDark by remember { mutableStateOf(checkSystemDarkMode()) }

    DisposableEffect(Unit) {
        val mediaQuery = window.matchMedia("(prefers-color-scheme: dark)")
        
        // Initial check
        isDark = mediaQuery.matches
        
        // Create listener for theme changes
        val listener: (Event) -> Unit = { _ ->
            // Re-check the media query when the event fires
            isDark = window.matchMedia("(prefers-color-scheme: dark)").matches
        }
        
        // Add listener
        mediaQuery.addEventListener("change", listener)
        
        onDispose {
            // Remove listener
            mediaQuery.removeEventListener("change", listener)
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

