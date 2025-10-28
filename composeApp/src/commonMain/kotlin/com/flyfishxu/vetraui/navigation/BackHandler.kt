package com.flyfishxu.vetraui.navigation

import androidx.compose.runtime.Composable

/**
 * Platform-specific back handler
 *
 * Common expect declaration
 */
@Composable
expect fun BackHandler(enabled: Boolean = true, onBack: () -> Unit)

