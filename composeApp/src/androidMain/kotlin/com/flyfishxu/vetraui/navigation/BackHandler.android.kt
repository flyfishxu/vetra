package com.flyfishxu.vetraui.navigation

import androidx.compose.runtime.Composable
import androidx.activity.compose.BackHandler as AndroidBackHandler

/**
 * Android implementation using Activity BackHandler
 */
@Composable
actual fun BackHandler(enabled: Boolean, onBack: () -> Unit) {
    AndroidBackHandler(enabled = enabled, onBack = onBack)
}

