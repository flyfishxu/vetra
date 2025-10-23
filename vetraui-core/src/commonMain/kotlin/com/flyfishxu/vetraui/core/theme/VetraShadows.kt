package com.flyfishxu.vetraui.core.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
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
 * - Soft, diffused shadows that mimic natural light
 * - Multiple blur levels for realistic depth
 * - Subtle color tinting for visual warmth
 * - Optimized for both light and dark modes
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
 * Apply a soft, natural shadow to create elegant elevation
 *
 * This shadow system creates depth through:
 * 1. Wider blur radius for softer appearance
 * 2. Subtle color tinting for warmth
 * 3. Layered approach for realistic lighting
 *
 * @param elevation The elevation level (vertical distance from surface)
 * @param shape The shape of the shadow
 * @param clip Whether to clip the content to the shape
 * @param tint Optional tint color for the shadow
 */
fun Modifier.vetraShadow(
    elevation: Dp,
    shape: Shape = RectangleShape,
    clip: Boolean = false,
    tint: Color? = null
): Modifier {
    if (elevation == 0.dp) return this

    // Create a softer shadow by increasing the effective elevation
    // This creates a wider, more diffused shadow
    val softElevation = elevation * 1.4f

    // Shadow colors - soft and subtle
    val ambientAlpha = 0.04f  // Very soft ambient shadow
    val spotAlpha = 0.08f     // Gentle directional shadow

    val ambientColor = tint?.copy(alpha = ambientAlpha)
        ?: Color.Black.copy(alpha = ambientAlpha)
    val spotColor = tint?.copy(alpha = spotAlpha)
        ?: Color.Black.copy(alpha = spotAlpha)

    return this.shadow(
        elevation = softElevation,
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

