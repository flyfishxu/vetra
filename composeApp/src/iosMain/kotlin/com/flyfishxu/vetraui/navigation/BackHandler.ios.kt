package com.flyfishxu.vetraui.navigation

import androidx.compose.runtime.Composable

/**
 * iOS implementation
 * 
 * iOS uses native gesture-based navigation which is handled by the navigation controller.
 * For SwiftUI integration, this would be handled at the native level.
 * For pure Compose Multiplatform, gesture handling would need to be implemented separately.
 */
@Composable
actual fun BackHandler(enabled: Boolean, onBack: () -> Unit) {
    // iOS back navigation is typically handled by:
    // 1. SwiftUI NavigationView's built-in back button
    // 2. Edge swipe gesture (handled by UINavigationController)
    // 
    // For a pure Compose Multiplatform app, you might want to implement
    // a swipe gesture detector here, but that's beyond the scope of this basic implementation.
    
    // No-op for now - iOS navigation is typically gesture-based
}

