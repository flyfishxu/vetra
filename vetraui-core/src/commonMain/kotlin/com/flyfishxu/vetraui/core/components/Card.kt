package com.flyfishxu.vetraui.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.theme.VetraTheme
import com.flyfishxu.vetraui.core.theme.vetraShadow
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Vetra Card
 *
 * A modern, elegant card component for containing related content.
 * Designed to be more flexible and intuitive than traditional Material cards.
 *
 * Card Variants:
 * - Standard (VetraCard): Elevated card with soft shadow
 * - Flat (VetraFlatCard): No elevation, subtle border
 * - Elevated (VetraElevatedCard): Higher elevation for emphasis
 * - Outlined (VetraOutlinedCard): Border only, no shadow
 *
 * Design Features:
 * - Clean, modern aesthetics
 * - Soft shadows for depth
 * - Optional clickable variant
 * - Flexible content padding
 * - Consistent with Vetra design system
 */

/**
 * Standard Card - Elevated card with soft shadow
 *
 * The default card variant. Use for containing related content
 * and creating visual grouping.
 *
 * @param modifier Modifier for the card
 * @param onClick Optional click handler - makes the card interactive
 * @param content Card content
 */
@Composable
fun VetraCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val shadows = VetraTheme.shadows
    val interactionSource = remember { MutableInteractionSource() }

    val baseModifier = modifier
        .vetraShadow(elevation = shadows.sm, shape = shapes.md)
        .clip(shapes.md)
        // Add subtle border for better definition
        .background(colors.borderSubtle)
        .padding(1.dp)
        .clip(shapes.md)
        .background(colors.canvasElevated)

    val finalModifier = if (onClick != null) {
        baseModifier.clickable(
            onClick = onClick,
            role = Role.Button,
            interactionSource = interactionSource,
            indication = null
        )
    } else {
        baseModifier
    }

    Column(
        modifier = finalModifier.padding(15.dp), // Reduced by 1dp to account for border
        content = content
    )
}

/**
 * Flat Card - No elevation, subtle appearance
 *
 * Use when you need subtle content grouping without strong visual emphasis.
 * Good for dense layouts or nested cards. Pure flat design with no borders or shadows.
 *
 * @param modifier Modifier for the card
 * @param onClick Optional click handler - makes the card interactive
 * @param content Card content
 */
@Composable
fun VetraFlatCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val interactionSource = remember { MutableInteractionSource() }

    val baseModifier = modifier
        .clip(shapes.md)
        .background(colors.canvasSubtle)

    val finalModifier = if (onClick != null) {
        baseModifier.clickable(
            onClick = onClick,
            role = Role.Button,
            interactionSource = interactionSource,
            indication = null
        )
    } else {
        baseModifier
    }

    Column(
        modifier = finalModifier.padding(16.dp),
        content = content
    )
}

/**
 * Elevated Card - Higher elevation for emphasis
 *
 * Use for cards that need extra emphasis or to appear above other content.
 * Good for featured content or important information. Features strong shadow without borders.
 *
 * @param modifier Modifier for the card
 * @param onClick Optional click handler - makes the card interactive
 * @param content Card content
 */
@Composable
fun VetraElevatedCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val shadows = VetraTheme.shadows
    val interactionSource = remember { MutableInteractionSource() }

    val baseModifier = modifier
        .vetraShadow(elevation = shadows.xl, shape = shapes.md)
        .clip(shapes.md)
        .background(colors.canvasElevated)

    val finalModifier = if (onClick != null) {
        baseModifier.clickable(
            onClick = onClick,
            role = Role.Button,
            interactionSource = interactionSource,
            indication = null
        )
    } else {
        baseModifier
    }

    Column(
        modifier = finalModifier.padding(16.dp),
        content = content
    )
}

/**
 * Outlined Card - Border only, no shadow
 *
 * Use when you need clear boundaries without elevation.
 * Good for light themes or minimalist designs.
 *
 * @param modifier Modifier for the card
 * @param onClick Optional click handler - makes the card interactive
 * @param content Card content
 */
@Composable
fun VetraOutlinedCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val interactionSource = remember { MutableInteractionSource() }

    val baseModifier = modifier
        .clip(shapes.md)
        .background(Color.Transparent)
        .then(
            // Border implementation
            Modifier
                .padding(1.dp)
                .background(colors.border, shapes.md)
                .padding(1.dp)
                .clip(shapes.md)
                .background(colors.canvasElevated, shapes.md)
        )

    val finalModifier = if (onClick != null) {
        baseModifier.clickable(
            onClick = onClick,
            role = Role.Button,
            interactionSource = interactionSource,
            indication = null
        )
    } else {
        baseModifier
    }

    Column(
        modifier = finalModifier.padding(14.dp), // Reduced to account for border
        content = content
    )
}

/**
 * Brand Card - Card with subtle brand color accent
 *
 * Use to highlight special or important content with your brand color.
 *
 * @param modifier Modifier for the card
 * @param onClick Optional click handler - makes the card interactive
 * @param content Card content
 */
@Composable
fun VetraBrandCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val shadows = VetraTheme.shadows
    val interactionSource = remember { MutableInteractionSource() }

    val baseModifier = modifier
        .vetraShadow(elevation = shadows.sm, shape = shapes.md)
        .clip(shapes.md)
        .background(colors.brandSubtle)

    val finalModifier = if (onClick != null) {
        baseModifier.clickable(
            onClick = onClick,
            role = Role.Button,
            interactionSource = interactionSource,
            indication = null
        )
    } else {
        baseModifier
    }

    Column(
        modifier = finalModifier.padding(16.dp),
        content = content
    )
}

// ============================================================================
// Previews
// ============================================================================

@Preview
@Composable
private fun VetraCardPreview() {
    VetraTheme {
        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Standard Card
            Text(
                "Standard Card",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Card Title",
                    style = VetraTheme.typography.headingMd.copy(color = VetraTheme.colors.textPrimary)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "This is a standard Vetra card with soft elevation and elegant shadows.",
                    style = VetraTheme.typography.bodyMd.copy(color = VetraTheme.colors.textSecondary)
                )
            }

            // Flat Card
            Text(
                "Flat Card",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )
            VetraFlatCard(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Subtle Card",
                    style = VetraTheme.typography.headingMd.copy(color = VetraTheme.colors.textPrimary)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "A flat card without elevation, perfect for subtle grouping.",
                    style = VetraTheme.typography.bodyMd.copy(color = VetraTheme.colors.textSecondary)
                )
            }

            // Elevated Card
            Text(
                "Elevated Card",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )
            VetraElevatedCard(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Important Content",
                    style = VetraTheme.typography.headingMd.copy(color = VetraTheme.colors.textPrimary)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "An elevated card with stronger shadow for emphasis.",
                    style = VetraTheme.typography.bodyMd.copy(color = VetraTheme.colors.textSecondary)
                )
            }

            // Outlined Card
            Text(
                "Outlined Card",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )
            VetraOutlinedCard(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Bordered Card",
                    style = VetraTheme.typography.headingMd.copy(color = VetraTheme.colors.textPrimary)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "A card with border outline and no shadow.",
                    style = VetraTheme.typography.bodyMd.copy(color = VetraTheme.colors.textSecondary)
                )
            }

            // Brand Card
            Text(
                "Brand Card",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )
            VetraBrandCard(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Featured Content",
                    style = VetraTheme.typography.headingMd.copy(color = VetraTheme.colors.onBrandSubtle)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "A card with subtle brand color for highlighting special content.",
                    style = VetraTheme.typography.bodyMd.copy(color = VetraTheme.colors.onBrandSubtle)
                )
            }
        }
    }
}

@Preview
@Composable
private fun VetraCardClickablePreview() {
    VetraTheme {
        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            VetraCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = { /* Handle click */ }
            ) {
                Text(
                    "Clickable Card",
                    style = VetraTheme.typography.headingMd.copy(color = VetraTheme.colors.textPrimary)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "This card is interactive and can be clicked.",
                    style = VetraTheme.typography.bodyMd.copy(color = VetraTheme.colors.textSecondary)
                )
            }
        }
    }
}

@Preview
@Composable
private fun VetraCardDarkPreview() {
    VetraTheme(darkMode = true) {
        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Dark Mode Card",
                    style = VetraTheme.typography.headingMd.copy(color = VetraTheme.colors.textPrimary)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Cards look great in dark mode too.",
                    style = VetraTheme.typography.bodyMd.copy(color = VetraTheme.colors.textSecondary)
                )
            }
        }
    }
}
