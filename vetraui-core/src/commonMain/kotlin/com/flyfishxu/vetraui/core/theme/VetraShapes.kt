package com.flyfishxu.vetraui.core.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * Vetra Shape System
 *
 * A flexible corner radius system for creating visual hierarchy and comfort.
 * Uses a balanced scale that works across different component sizes.
 *
 * Philosophy:
 * - Smooth, comfortable corners
 * - Clear size-appropriate rounding
 * - Consistent visual rhythm
 * - Elegant without being excessive
 */
@Immutable
data class VetraShapes(
    /**
     * None - 0dp
     * Sharp corners for specific design needs
     */
    val none: Shape,

    /**
     * Extra Small - 4dp
     * For small elements like badges and chips
     */
    val xs: Shape,

    /**
     * Small - 8dp
     * For buttons, inputs, and small components
     */
    val sm: Shape,

    /**
     * Medium - 12dp
     * For cards and medium components
     */
    val md: Shape,

    /**
     * Large - 16dp
     * For large cards and containers
     */
    val lg: Shape,

    /**
     * Extra Large - 24dp
     * For dialogs and bottom sheets
     */
    val xl: Shape,

    /**
     * 2XL - 32dp
     * For full-screen modals
     */
    val xxl: Shape,

    /**
     * Full - 9999dp
     * For circular/pill-shaped elements
     */
    val full: Shape,
)

/**
 * Default Vetra Shapes
 */
val DefaultVetraShapes = VetraShapes(
    none = RoundedCornerShape(0.dp),
    xs = RoundedCornerShape(4.dp),
    sm = RoundedCornerShape(8.dp),
    md = RoundedCornerShape(12.dp),
    lg = RoundedCornerShape(16.dp),
    xl = RoundedCornerShape(24.dp),
    xxl = RoundedCornerShape(32.dp),
    full = RoundedCornerShape(9999.dp),
)

/**
 * Local provider for Vetra shapes
 */
val LocalVetraShapes = staticCompositionLocalOf { DefaultVetraShapes }

