package com.flyfishxu.vetraui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import java.awt.Toolkit

/**
 * JVM Desktop implementation: checks system properties
 */
@Composable
actual fun isSystemInDarkTheme(): Boolean {
    return remember {
        try {
            // Try to detect dark mode on macOS
            val osName = System.getProperty("os.name").lowercase()
            when {
                osName.contains("mac") -> {
                    // macOS: check AppleInterfaceStyle
                    val isDark = Runtime.getRuntime()
                        .exec(arrayOf("defaults", "read", "-g", "AppleInterfaceStyle"))
                        .inputStream
                        .bufferedReader()
                        .use { it.readText() }
                        .trim()
                        .equals("Dark", ignoreCase = true)
                    isDark
                }
                osName.contains("win") -> {
                    // Windows: check registry for dark mode
                    try {
                        val process = Runtime.getRuntime().exec(
                            "reg query HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize /v AppsUseLightTheme"
                        )
                        val result = process.inputStream.bufferedReader().use { it.readText() }
                        // If AppsUseLightTheme is 0, dark mode is enabled
                        result.contains("0x0")
                    } catch (e: Exception) {
                        false
                    }
                }
                else -> {
                    // Linux/other: try to detect from GTK theme
                    val gtkTheme = System.getenv("GTK_THEME") ?: ""
                    gtkTheme.contains("dark", ignoreCase = true)
                }
            }
        } catch (e: Exception) {
            // Default to light mode if detection fails
            false
        }
    }
}

