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
 * 1. Balanced blur radius for natural appearance
 * 2. Subtle transparency for elegance
 * 3. Realistic lighting that mimics natural shadows
 *
 * Design principles:
 * - Shadows should be visible but not overwhelming
 * - Heavier at the bottom (natural light from above)
 * - Soft and diffused for comfortable viewing
 * - Transparent enough to feel lightweight
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
    // Do not early-return at 0dp during animated transitions.
    // Let caller control alpha/scale; we still return a no-op shadow for 0dp.
    // This avoids sudden shadow drop/flash when elevation animates to or from 0.
    if (elevation <= 0.dp) {
        return this
    }

    // Balanced diffusion: 1.2x spread provides soft edges without excessive blur
    // This creates natural-looking shadows that don't extend too far
    val softElevation = elevation * 1.2f

    // Refined alpha values for elegant, subtle shadows:
    // - Ambient: 6% provides gentle base shadow without heaviness
    // - Spot: 10% adds definition and depth without harshness
    // These values create visible yet unobtrusive shadows
    val ambientAlpha = 0.06f  // Soft base shadow for subtle depth
    val spotAlpha = 0.10f     // Directional shadow for clear separation

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

