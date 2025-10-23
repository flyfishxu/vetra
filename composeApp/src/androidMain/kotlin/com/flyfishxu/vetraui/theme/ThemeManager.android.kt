package com.flyfishxu.vetraui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable

/**
 * Android implementation: uses Compose's isSystemInDarkTheme()
 */
@Composable
actual fun isSystemInDarkTheme(): Boolean = isSystemInDarkTheme()

