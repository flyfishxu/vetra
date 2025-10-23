package com.flyfishxu.vetraui.core.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Vetra Color Scheme
 *
 * A modern, elegant color system designed for universal appeal.
 * Unlike Material Design's complex color roles, Vetra uses intuitive semantic naming
 * that makes it easier for developers to understand and use.
 *
 * Design Philosophy:
 * - Semantic naming over technical jargon
 * - Clear visual hierarchy
 * - Accessibility first
 * - Beautiful by default
 *
 * @property brand Primary brand color - used for main interactive elements
 * @property onBrand Content color on brand backgrounds
 * @property brandSubtle Subtle variant of brand color for less emphasis
 * @property onBrandSubtle Content color on subtle brand backgrounds
 *
 * @property accent Secondary accent color - for highlights and special states
 * @property onAccent Content color on accent backgrounds
 * @property accentSubtle Subtle variant of accent color
 * @property onAccentSubtle Content color on subtle accent backgrounds
 *
 * @property canvas Background color - the main canvas of the app
 * @property onCanvas Primary text/content color on canvas
 * @property canvasElevated Elevated surface color (cards, dialogs)
 * @property onCanvasElevated Content color on elevated surfaces
 * @property canvasSubtle Subtle background for less emphasis
 * @property onCanvasSubtle Content color on subtle backgrounds
 *
 * @property textPrimary Primary text color - for main content
 * @property textSecondary Secondary text color - for supporting content
 * @property textTertiary Tertiary text color - for hints and placeholders
 * @property textDisabled Disabled text color
 *
 * @property border Border color for outlines and dividers
 * @property borderSubtle Subtle border color
 * @property borderFocus Focus ring color for accessibility
 *
 * @property success Success state color
 * @property onSuccess Content color on success backgrounds
 * @property warning Warning state color
 * @property onWarning Content color on warning backgrounds
 * @property danger Error/danger state color
 * @property onDanger Content color on danger backgrounds
 * @property info Information state color
 * @property onInfo Content color on info backgrounds
 *
 * @property overlay Overlay/scrim color for modals
 * @property shadow Shadow color
 */
@Immutable
data class VetraColorScheme(
    // Brand Colors
    val brand: Color,
    val onBrand: Color,
    val brandSubtle: Color,
    val onBrandSubtle: Color,

    // Accent Colors
    val accent: Color,
    val onAccent: Color,
    val accentSubtle: Color,
    val onAccentSubtle: Color,

    // Canvas/Background Colors
    val canvas: Color,
    val onCanvas: Color,
    val canvasElevated: Color,
    val onCanvasElevated: Color,
    val canvasSubtle: Color,
    val onCanvasSubtle: Color,

    // Text Colors
    val textPrimary: Color,
    val textSecondary: Color,
    val textTertiary: Color,
    val textDisabled: Color,

    // Border Colors
    val border: Color,
    val borderSubtle: Color,
    val borderFocus: Color,

    // Semantic Colors
    val success: Color,
    val onSuccess: Color,
    val warning: Color,
    val onWarning: Color,
    val danger: Color,
    val onDanger: Color,
    val info: Color,
    val onInfo: Color,

    // Utility Colors
    val overlay: Color,
    val shadow: Color,
)

/**
 * Light color scheme with modern, clean aesthetics
 */
val VetraLightColorScheme = VetraColorScheme(
    // Brand Colors - Modern Blue
    brand = Color(0xFF2563EB),           // Vibrant, trustworthy blue
    onBrand = Color(0xFFFFFFFF),
    brandSubtle = Color(0xFFEFF6FF),     // Very light blue
    onBrandSubtle = Color(0xFF1E40AF),

    // Accent Colors - Elegant Purple
    accent = Color(0xFF7C3AED),          // Rich purple for highlights
    onAccent = Color(0xFFFFFFFF),
    accentSubtle = Color(0xFFF5F3FF),    // Very light purple
    onAccentSubtle = Color(0xFF5B21B6),

    // Canvas Colors - Neutral Grays
    canvas = Color(0xFFFAFAFA),          // Soft off-white
    onCanvas = Color(0xFF0A0A0A),
    canvasElevated = Color(0xFFFFFFFF),  // Pure white for cards
    onCanvasElevated = Color(0xFF0A0A0A),
    canvasSubtle = Color(0xFFF5F5F5),    // Subtle gray
    onCanvasSubtle = Color(0xFF171717),

    // Text Colors
    textPrimary = Color(0xFF0A0A0A),     // Near black for readability
    textSecondary = Color(0xFF525252),   // Medium gray
    textTertiary = Color(0xFFA3A3A3),    // Light gray
    textDisabled = Color(0xFFD4D4D4),    // Very light gray

    // Border Colors
    border = Color(0xFFE5E5E5),          // Light gray border
    borderSubtle = Color(0xFFF5F5F5),    // Very subtle border
    borderFocus = Color(0xFF3B82F6),     // Blue focus ring

    // Semantic Colors
    success = Color(0xFF10B981),         // Fresh green
    onSuccess = Color(0xFFFFFFFF),
    warning = Color(0xFFF59E0B),         // Warm amber
    onWarning = Color(0xFF000000),
    danger = Color(0xFFEF4444),          // Clear red
    onDanger = Color(0xFFFFFFFF),
    info = Color(0xFF06B6D4),            // Bright cyan
    onInfo = Color(0xFFFFFFFF),

    // Utility Colors
    overlay = Color(0x80000000),         // 50% black
    shadow = Color(0x1A000000),          // 10% black
)

/**
 * Dark color scheme with elegant, comfortable aesthetics
 */
val VetraDarkColorScheme = VetraColorScheme(
    // Brand Colors - Brighter for dark mode
    brand = Color(0xFF3B82F6),           // Slightly brighter blue
    onBrand = Color(0xFFFFFFFF),
    brandSubtle = Color(0xFF1E293B),     // Dark blue-gray
    onBrandSubtle = Color(0xFF60A5FA),

    // Accent Colors - Vivid for dark mode
    accent = Color(0xFF8B5CF6),          // Brighter purple
    onAccent = Color(0xFFFFFFFF),
    accentSubtle = Color(0xFF2E1065),    // Dark purple
    onAccentSubtle = Color(0xFFA78BFA),

    // Canvas Colors - Rich blacks
    canvas = Color(0xFF0A0A0A),          // Deep black
    onCanvas = Color(0xFFFAFAFA),
    canvasElevated = Color(0xFF171717),  // Elevated black
    onCanvasElevated = Color(0xFFFAFAFA),
    canvasSubtle = Color(0xFF000000),    // Pure black
    onCanvasSubtle = Color(0xFFE5E5E5),

    // Text Colors
    textPrimary = Color(0xFFFAFAFA),     // Near white for readability
    textSecondary = Color(0xFFA3A3A3),   // Medium gray
    textTertiary = Color(0xFF525252),    // Darker gray
    textDisabled = Color(0xFF404040),    // Very dark gray

    // Border Colors
    border = Color(0xFF262626),          // Dark gray border
    borderSubtle = Color(0xFF171717),    // Very subtle border
    borderFocus = Color(0xFF60A5FA),     // Brighter blue focus ring

    // Semantic Colors - Adjusted for dark mode
    success = Color(0xFF34D399),         // Brighter green
    onSuccess = Color(0xFF000000),
    warning = Color(0xFFFBBF24),         // Brighter amber
    onWarning = Color(0xFF000000),
    danger = Color(0xFFF87171),          // Softer red
    onDanger = Color(0xFF000000),
    info = Color(0xFF22D3EE),            // Brighter cyan
    onInfo = Color(0xFF000000),

    // Utility Colors
    overlay = Color(0xCC000000),         // 80% black (darker for dark mode)
    shadow = Color(0x4D000000),          // 30% black (more visible)
)

/**
 * Local provider for Vetra color scheme
 */
val LocalVetraColorScheme = staticCompositionLocalOf { VetraLightColorScheme }

