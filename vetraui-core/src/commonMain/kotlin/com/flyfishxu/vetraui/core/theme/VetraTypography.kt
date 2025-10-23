package com.flyfishxu.vetraui.core.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Vetra Typography System
 *
 * A modern, elegant typography scale designed for clarity and readability.
 * Uses a harmonious size progression and careful line height ratios.
 *
 * Philosophy:
 * - Clear hierarchy through size and weight
 * - Generous line heights for readability
 * - Careful letter spacing for elegance
 * - Optimized for both display and reading
 *
 * Size Scale (based on major second - 1.125):
 * - 11sp, 12sp, 14sp, 16sp, 18sp, 20sp, 24sp, 28sp, 32sp, 40sp, 48sp
 */
@Immutable
data class VetraTypography(
    /**
     * Display Extra Large - 48sp
     * For hero sections and major headings
     */
    val displayXl: TextStyle,

    /**
     * Display Large - 40sp
     * For page titles and major sections
     */
    val displayLg: TextStyle,

    /**
     * Display Medium - 32sp
     * For prominent headings
     */
    val displayMd: TextStyle,

    /**
     * Display Small - 28sp
     * For section headings
     */
    val displaySm: TextStyle,

    /**
     * Heading Extra Large - 24sp
     * For card/component titles
     */
    val headingXl: TextStyle,

    /**
     * Heading Large - 20sp
     * For subsection headings
     */
    val headingLg: TextStyle,

    /**
     * Heading Medium - 18sp
     * For list headers and labels
     */
    val headingMd: TextStyle,

    /**
     * Heading Small - 16sp
     * For small headings and emphasized text
     */
    val headingSm: TextStyle,

    /**
     * Body Large - 16sp
     * For main content and paragraphs
     */
    val bodyLg: TextStyle,

    /**
     * Body Medium - 14sp
     * For secondary content and descriptions
     */
    val bodyMd: TextStyle,

    /**
     * Body Small - 12sp
     * For captions and helper text
     */
    val bodySm: TextStyle,

    /**
     * Label Large - 14sp Bold
     * For buttons and important labels
     */
    val labelLg: TextStyle,

    /**
     * Label Medium - 12sp Bold
     * For form labels and tags
     */
    val labelMd: TextStyle,

    /**
     * Label Small - 11sp Bold
     * For badges and small labels
     */
    val labelSm: TextStyle,
)

/**
 * Default Vetra Typography
 */
val DefaultVetraTypography = VetraTypography(
    // Display Styles - Bold, tight spacing
    displayXl = TextStyle(
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 56.sp,
        letterSpacing = (-0.5).sp
    ),
    displayLg = TextStyle(
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 48.sp,
        letterSpacing = (-0.5).sp
    ),
    displayMd = TextStyle(
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 40.sp,
        letterSpacing = (-0.25).sp
    ),
    displaySm = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),

    // Heading Styles - SemiBold, balanced
    headingXl = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    headingLg = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    headingMd = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 26.sp,
        letterSpacing = 0.sp
    ),
    headingSm = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    // Body Styles - Regular, generous line height
    bodyLg = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    bodyMd = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    bodySm = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp,
        letterSpacing = 0.1.sp
    ),

    // Label Styles - Medium/SemiBold, uppercase-ready
    labelLg = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMd = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSm = TextStyle(
        fontSize = 11.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
)

/**
 * Local provider for Vetra typography
 */
val LocalVetraTypography = staticCompositionLocalOf { DefaultVetraTypography }

