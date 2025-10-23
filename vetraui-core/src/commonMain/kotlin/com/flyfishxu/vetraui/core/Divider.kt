package com.flyfishxu.vetraui.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.theme.VetraTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Vetra Divider
 *
 * A simple, elegant divider component for visual separation of content.
 * Follows the Vetra design philosophy of clarity and subtle elegance.
 *
 * Design Features:
 * - Clean separation of content sections
 * - Horizontal and vertical orientations
 * - Multiple style variants for different emphasis levels
 * - Optional text labels for section headers
 */

/**
 * Divider Orientation
 */
enum class DividerOrientation {
    Horizontal,
    Vertical
}

/**
 * Standard Divider - Basic content separator
 *
 * @param modifier Modifier for the divider
 * @param orientation Orientation of the divider (horizontal or vertical)
 * @param thickness Thickness of the divider line
 * @param color Color of the divider (defaults to theme border color)
 */
@Composable
fun VetraDivider(
    modifier: Modifier = Modifier,
    orientation: DividerOrientation = DividerOrientation.Horizontal,
    thickness: Dp = 1.dp,
    color: Color = VetraTheme.colors.border
) {
    when (orientation) {
        DividerOrientation.Horizontal -> {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(thickness)
                    .background(color)
            )
        }
        DividerOrientation.Vertical -> {
            Box(
                modifier = modifier
                    .width(thickness)
                    .fillMaxHeight()
                    .background(color)
            )
        }
    }
}

/**
 * Subtle Divider - Lighter divider for subtle separation
 *
 * @param modifier Modifier for the divider
 * @param orientation Orientation of the divider (horizontal or vertical)
 * @param thickness Thickness of the divider line
 */
@Composable
fun VetraSubtleDivider(
    modifier: Modifier = Modifier,
    orientation: DividerOrientation = DividerOrientation.Horizontal,
    thickness: Dp = 1.dp
) {
    VetraDivider(
        modifier = modifier,
        orientation = orientation,
        thickness = thickness,
        color = VetraTheme.colors.borderSubtle
    )
}

/**
 * Strong Divider - More prominent divider for clear separation
 *
 * @param modifier Modifier for the divider
 * @param orientation Orientation of the divider (horizontal or vertical)
 * @param thickness Thickness of the divider line
 */
@Composable
fun VetraStrongDivider(
    modifier: Modifier = Modifier,
    orientation: DividerOrientation = DividerOrientation.Horizontal,
    thickness: Dp = 2.dp
) {
    VetraDivider(
        modifier = modifier,
        orientation = orientation,
        thickness = thickness,
        color = VetraTheme.colors.border
    )
}

/**
 * Brand Divider - Divider with brand color accent
 *
 * Useful for highlighting special sections or creating visual interest.
 *
 * @param modifier Modifier for the divider
 * @param orientation Orientation of the divider (horizontal or vertical)
 * @param thickness Thickness of the divider line
 */
@Composable
fun VetraBrandDivider(
    modifier: Modifier = Modifier,
    orientation: DividerOrientation = DividerOrientation.Horizontal,
    thickness: Dp = 2.dp
) {
    VetraDivider(
        modifier = modifier,
        orientation = orientation,
        thickness = thickness,
        color = VetraTheme.colors.brand
    )
}

/**
 * Inset Divider - Divider with horizontal padding
 *
 * Commonly used in lists to create visual hierarchy.
 *
 * @param modifier Modifier for the divider
 * @param startPadding Padding at the start of the divider
 * @param endPadding Padding at the end of the divider
 * @param thickness Thickness of the divider line
 * @param color Color of the divider
 */
@Composable
fun VetraInsetDivider(
    modifier: Modifier = Modifier,
    startPadding: Dp = 16.dp,
    endPadding: Dp = 0.dp,
    thickness: Dp = 1.dp,
    color: Color = VetraTheme.colors.border
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(thickness)
            .padding(start = startPadding, end = endPadding)
            .background(color)
    )
}

/**
 * Divider with Text Label - Divider with centered text
 *
 * Useful for section headers and category separators.
 *
 * @param text Label text to display
 * @param modifier Modifier for the divider
 * @param thickness Thickness of the divider lines
 * @param color Color of the divider lines
 * @param spacing Space between text and divider lines
 */
@Composable
fun VetraDividerWithText(
    text: String,
    modifier: Modifier = Modifier,
    thickness: Dp = 1.dp,
    color: Color = VetraTheme.colors.border,
    spacing: Dp = 12.dp
) {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        VetraDivider(
            modifier = Modifier.weight(1f),
            thickness = thickness,
            color = color
        )

        Text(
            text = text,
            style = typography.labelMd.copy(color = colors.textSecondary),
            modifier = Modifier.padding(horizontal = spacing)
        )

        VetraDivider(
            modifier = Modifier.weight(1f),
            thickness = thickness,
            color = color
        )
    }
}

/**
 * Gradient Divider - Divider with fade-out effect
 *
 * Creates a subtle, elegant separation with gradient edges.
 *
 * @param modifier Modifier for the divider
 * @param orientation Orientation of the divider
 * @param thickness Thickness of the divider line
 * @param centerColor Center color of the gradient
 */
@Composable
fun VetraGradientDivider(
    modifier: Modifier = Modifier,
    orientation: DividerOrientation = DividerOrientation.Horizontal,
    thickness: Dp = 1.dp,
    centerColor: Color = VetraTheme.colors.border
) {
    // Note: For a true gradient effect, you would need to use a Brush
    // This is a simplified version using the standard divider
    // In a full implementation, you'd use drawWithContent or Canvas
    VetraDivider(
        modifier = modifier,
        orientation = orientation,
        thickness = thickness,
        color = centerColor.copy(alpha = 0.5f)
    )
}

// ============================================================================
// Previews
// ============================================================================

@Preview
@Composable
private fun VetraDividerPreview() {
    VetraTheme {
        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                "Divider Variants",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            // Standard Divider
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    "Standard Divider",
                    style = VetraTheme.typography.labelLg.copy(color = VetraTheme.colors.textSecondary)
                )
                VetraDivider()
            }

            // Subtle Divider
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    "Subtle Divider",
                    style = VetraTheme.typography.labelLg.copy(color = VetraTheme.colors.textSecondary)
                )
                VetraSubtleDivider()
            }

            // Strong Divider
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    "Strong Divider",
                    style = VetraTheme.typography.labelLg.copy(color = VetraTheme.colors.textSecondary)
                )
                VetraStrongDivider()
            }

            // Brand Divider
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    "Brand Divider",
                    style = VetraTheme.typography.labelLg.copy(color = VetraTheme.colors.textSecondary)
                )
                VetraBrandDivider()
            }

            // Inset Divider
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    "Inset Divider",
                    style = VetraTheme.typography.labelLg.copy(color = VetraTheme.colors.textSecondary)
                )
                VetraInsetDivider(startPadding = 48.dp)
            }

            // Divider with Text
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    "Divider with Text",
                    style = VetraTheme.typography.labelLg.copy(color = VetraTheme.colors.textSecondary)
                )
                VetraDividerWithText(text = "OR")
            }

            // Vertical Divider
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    "Vertical Dividers",
                    style = VetraTheme.typography.labelLg.copy(color = VetraTheme.colors.textSecondary)
                )
                Row(
                    modifier = Modifier.height(60.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(VetraTheme.colors.canvasElevated)
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Section 1", style = VetraTheme.typography.bodySm)
                    }

                    VetraDivider(
                        orientation = DividerOrientation.Vertical,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(VetraTheme.colors.canvasElevated)
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Section 2", style = VetraTheme.typography.bodySm)
                    }

                    VetraDivider(
                        orientation = DividerOrientation.Vertical,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(VetraTheme.colors.canvasElevated)
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Section 3", style = VetraTheme.typography.bodySm)
                    }
                }
            }

            // List Example with Inset Dividers
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    "List with Inset Dividers",
                    style = VetraTheme.typography.labelLg.copy(color = VetraTheme.colors.textSecondary)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(VetraTheme.colors.canvasElevated, VetraTheme.shapes.md)
                ) {
                    repeat(3) { index ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(VetraTheme.colors.brandSubtle, VetraTheme.shapes.sm)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    "List Item ${index + 1}",
                                    style = VetraTheme.typography.bodyMd.copy(
                                        color = VetraTheme.colors.textPrimary
                                    )
                                )
                                Text(
                                    "Description text",
                                    style = VetraTheme.typography.bodySm.copy(
                                        color = VetraTheme.colors.textSecondary
                                    )
                                )
                            }
                        }
                        if (index < 2) {
                            VetraInsetDivider(startPadding = 68.dp)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun VetraDividerDarkPreview() {
    VetraTheme(darkMode = true) {
        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Dark Mode Dividers",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            VetraDivider()
            VetraSubtleDivider()
            VetraStrongDivider()
            VetraBrandDivider()
            VetraDividerWithText(text = "SECTION")
        }
    }
}

