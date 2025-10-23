package com.flyfishxu.vetraui.theme

import androidx.compose.runtime.Composable
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

/**
 * iOS implementation: checks UIScreen's traitCollection
 */
@Composable
actual fun isSystemInDarkTheme(): Boolean {
    return UIScreen.mainScreen.traitCollection.userInterfaceStyle == 
        UIUserInterfaceStyle.UIUserInterfaceStyleDark
}

