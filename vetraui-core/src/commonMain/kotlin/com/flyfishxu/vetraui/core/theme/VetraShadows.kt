package com.flyfishxu.vetraui.core.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Vetra Shadow System
 *
 * A refined elevation system using soft, natural shadows that create
 * comfortable visual hierarchy without being harsh or distracting.
 *
 * Design Philosophy:
 * - Clear, visible shadows that establish proper depth hierarchy
 * - Multiple blur levels for realistic depth perception
 * - Adaptive transparency for light and dark modes
 * - Optimized for cross-platform rendering
 */
@Immutable
data class VetraShadows(
    /**
     * None - 0dp
     * No elevation, flat surface
     */
    val none: Dp,

    /**
     * XS - 1dp
     * Barely perceptible, for subtle separation
     * Use for: Hovering elements, subtle depth
     */
    val xs: Dp,

    /**
     * SM - 2dp
     * Light elevation for contained elements
     * Use for: Cards, contained buttons
     */
    val sm: Dp,

    /**
     * MD - 4dp
     * Moderate elevation for interactive elements
     * Use for: Raised buttons, floating action buttons
     */
    val md: Dp,

    /**
     * LG - 8dp
     * Strong elevation for prominent elements
     * Use for: Dropdowns, tooltips, popovers
     */
    val lg: Dp,

    /**
     * XL - 16dp
     * Maximum elevation for overlay elements
     * Use for: Dialogs, bottom sheets, navigation drawers
     */
    val xl: Dp,

    /**
     * 2XL - 24dp
     * Dramatic elevation for critical overlays
     * Use for: Modals, critical alerts
     */
    val xxl: Dp,
)

/**
 * Shadow configuration for different theme modes
 *
 * @param ambientAlpha Alpha value for ambient shadow (0.0 - 1.0)
 * @param spotAlpha Alpha value for spot shadow (0.0 - 1.0)
 * @param elevationMultiplier Multiplier for elevation spread (typically 1.0 - 1.5)
 */
@Immutable
data class ShadowConfig(
    val ambientAlpha: Float,
    val spotAlpha: Float,
    val elevationMultiplier: Float = 1.2f
)

/**
 * Default Vetra Shadows
 */
val DefaultVetraShadows = VetraShadows(
    none = 0.dp,
    xs = 1.dp,
    sm = 2.dp,
    md = 4.dp,
    lg = 8.dp,
    xl = 16.dp,
    xxl = 24.dp,
)

/**
 * Light mode shadow configuration
 */
val LightModeShadowConfig = ShadowConfig(
    ambientAlpha = 0.15f,  // 15% - Soft ambient shadow for gentle depth
    spotAlpha = 0.25f,     // 25% - Clear directional shadow for separation
    elevationMultiplier = 1.15f  // Conservative spread for natural appearance
)

/**
 * Dark mode shadow configuration
 */
val DarkModeShadowConfig = ShadowConfig(
    ambientAlpha = 0.25f,  // 25% - Stronger ambient for dark background visibility
    spotAlpha = 0.35f,     // 35% - Clear directional shadow on dark backgrounds
    elevationMultiplier = 1.2f  // Slightly more spread for dark mode visibility
)

/**
 * Soft, natural shadow
 *
 * Design principles:
 * - Shadows should be clearly visible and establish hierarchy
 * - Heavier at the bottom (natural light from above)
 * - Adaptive opacity for different theme modes
 * - Balanced spread for natural appearance
 *
 * @param elevation The elevation level (vertical distance from surface)
 * @param shape The shape of the shadow
 * @param clip Whether to clip the content to the shape
 */
fun Modifier.vetraShadow(
    elevation: Dp,
    shape: Shape = RectangleShape,
    clip: Boolean = false
): Modifier = composed {
    if (elevation <= 0.dp) {
        return@composed this
    }

    val config = LocalVetraShadowConfig.current

    // Apply configured elevation multiplier for natural shadow spread
    val effectiveElevation = elevation * config.elevationMultiplier

    // Use configured alpha values for proper visibility
    val ambientColor = Color.Black.copy(alpha = config.ambientAlpha)
    val spotColor = Color.Black.copy(alpha = config.spotAlpha)

    this.shadow(
        elevation = effectiveElevation,
        shape = shape,
        clip = clip,
        ambientColor = ambientColor,
        spotColor = spotColor
    )
}

/**
 * Local provider for Vetra shadows
 */
val LocalVetraShadows = staticCompositionLocalOf { DefaultVetraShadows }

/**
 * Local provider for shadow configuration
 * Automatically adapts to light/dark mode
 */
val LocalVetraShadowConfig = staticCompositionLocalOf { LightModeShadowConfig }

