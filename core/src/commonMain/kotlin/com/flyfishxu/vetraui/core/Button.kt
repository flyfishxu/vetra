package com.flyfishxu.vetraui.core

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.theme.VetraTheme
import com.flyfishxu.vetraui.core.theme.vetraShadow
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Vetra Button
 *
 * A modern, elegant button component with multiple variants.
 * Designed to be more intuitive and flexible than traditional Material buttons.
 *
 * Button Variants:
 * - Primary (VetraButton): Solid brand color, for main actions
 * - Secondary (VetraSecondaryButton): Solid accent color, for secondary actions
 * - Outlined (VetraOutlinedButton): Border only, for tertiary actions
 * - Ghost (VetraGhostButton): No background, minimal emphasis
 * - Danger (VetraDangerButton): For destructive actions
 *
 * Design Features:
 * - Clean, modern aesthetics
 * - Soft shadows for depth
 * - Clear hover/press states
 * - Accessible by default
 * - Consistent sizing
 */

private val ButtonHeight = 44.dp
private val ButtonMinWidth = 88.dp
private val ButtonHorizontalPadding = 20.dp
private val ButtonVerticalPadding = 12.dp

/**
 * Primary Button - Solid brand color for main actions
 *
 * Use for the most important action on a screen.
 * Limit to one primary button per section for clear hierarchy.
 *
 * @param onClick Called when the button is clicked
 * @param modifier Modifier for the button
 * @param enabled Whether the button is enabled
 * @param content Button content, typically text or text with icon
 */
@Composable
fun VetraButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val shadows = VetraTheme.shadows
    val interactionSource = remember { MutableInteractionSource() }

    val backgroundColor = if (enabled) colors.brand else colors.brandDisabled
    val contentColor = if (enabled) colors.onBrand else colors.onBrandDisabled
    val shadow = if (enabled) shadows.sm else shadows.none

    Box(
        modifier = modifier
            .defaultMinSize(minWidth = ButtonMinWidth, minHeight = ButtonHeight)
            .vetraShadow(elevation = shadow, shape = shapes.sm)
            .clip(shapes.sm)
            .background(backgroundColor)
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = ripple(color = Color.White.copy(alpha = 0.3f))
            )
            .padding(horizontal = ButtonHorizontalPadding, vertical = ButtonVerticalPadding),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CompositionLocalProvider(
                LocalContentColor provides contentColor,
                LocalTextStyle provides VetraTheme.typography.labelLg.copy(color = contentColor)
            ) {
                content()
            }
        }
    }
}

/**
 * Secondary Button - Solid accent color for secondary actions
 *
 * Use for important but not primary actions.
 * Can have multiple secondary buttons on a screen.
 *
 * @param onClick Called when the button is clicked
 * @param modifier Modifier for the button
 * @param enabled Whether the button is enabled
 * @param content Button content, typically text or text with icon
 */
@Composable
fun VetraSecondaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val shadows = VetraTheme.shadows
    val interactionSource = remember { MutableInteractionSource() }

    val backgroundColor = if (enabled) colors.accent else colors.accentDisabled
    val contentColor = if (enabled) colors.onAccent else colors.onAccentDisabled
    val shadow = if (enabled) shadows.sm else shadows.none

    Box(
        modifier = modifier
            .defaultMinSize(minWidth = ButtonMinWidth, minHeight = ButtonHeight)
            .vetraShadow(elevation = shadow, shape = shapes.sm)
            .clip(shapes.sm)
            .background(backgroundColor)
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = ripple(color = Color.White.copy(alpha = 0.3f))
            )
            .padding(horizontal = ButtonHorizontalPadding, vertical = ButtonVerticalPadding),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CompositionLocalProvider(
                LocalContentColor provides contentColor,
                LocalTextStyle provides VetraTheme.typography.labelLg.copy(color = contentColor)
            ) {
                content()
            }
        }
    }
}

/**
 * Outlined Button - Border only for tertiary actions
 *
 * Use for less important actions or when you need many buttons in close proximity.
 * More subtle than solid buttons.
 *
 * @param onClick Called when the button is clicked
 * @param modifier Modifier for the button
 * @param enabled Whether the button is enabled
 * @param content Button content, typically text or text with icon
 */
@Composable
fun VetraOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val interactionSource = remember { MutableInteractionSource() }

    val borderColor = if (enabled) colors.border else colors.borderSubtle
    val contentColor = if (enabled) colors.brand else colors.textDisabled

    Box(
        modifier = modifier
            .defaultMinSize(minWidth = ButtonMinWidth, minHeight = ButtonHeight)
            .clip(shapes.sm)
            .background(Color.Transparent)
            .then(
                // Border implementation
                Modifier
                    .padding(1.dp)
                    .background(borderColor, shapes.sm)
                    .padding(1.dp)
                    .background(colors.canvasElevated, shapes.sm)
            )
            .clickable(
                enabled = enabled,
                onClick = onClick,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = ripple(color = colors.brand.copy(alpha = 0.2f))
            )
            .padding(
                horizontal = ButtonHorizontalPadding - 2.dp,
                vertical = ButtonVerticalPadding - 2.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CompositionLocalProvider(
                LocalContentColor provides contentColor,
                LocalTextStyle provides VetraTheme.typography.labelLg.copy(color = contentColor)
            ) {
                content()
            }
        }
    }
}

/**
 * Ghost Button - Minimal button with no background
 *
 * Use for the least important actions or in dense UIs.
 * Most subtle button variant.
 *
 * @param onClick Called when the button is clicked
 * @param modifier Modifier for the button
 * @param enabled Whether the button is enabled
 * @param content Button content, typically text or text with icon
 */
@Composable
fun VetraGhostButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val interactionSource = remember { MutableInteractionSource() }

    val contentColor = if (enabled) colors.brand else colors.textDisabled

    Box(
        modifier = modifier
            .defaultMinSize(minWidth = ButtonMinWidth, minHeight = ButtonHeight)
            .clip(shapes.sm)
            .background(Color.Transparent)
            .clickable(
                enabled = enabled,
                onClick = onClick,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = ripple(color = colors.brand.copy(alpha = 0.15f))
            )
            .padding(horizontal = ButtonHorizontalPadding, vertical = ButtonVerticalPadding),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CompositionLocalProvider(
                LocalContentColor provides contentColor,
                LocalTextStyle provides VetraTheme.typography.labelLg.copy(color = contentColor)
            ) {
                content()
            }
        }
    }
}

/**
 * Danger Button - For destructive actions
 *
 * Use for actions that delete, remove, or destroy data.
 * The red color signals caution to users.
 *
 * @param onClick Called when the button is clicked
 * @param modifier Modifier for the button
 * @param enabled Whether the button is enabled
 * @param content Button content, typically text or text with icon
 */
@Composable
fun VetraDangerButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val shadows = VetraTheme.shadows
    val interactionSource = remember { MutableInteractionSource() }

    val backgroundColor = if (enabled) colors.danger else colors.dangerDisabled
    val contentColor = if (enabled) colors.onDanger else colors.onDangerDisabled
    val shadow = if (enabled) shadows.sm else shadows.none

    Box(
        modifier = modifier
            .defaultMinSize(minWidth = ButtonMinWidth, minHeight = ButtonHeight)
            .vetraShadow(elevation = shadow, shape = shapes.sm)
            .clip(shapes.sm)
            .background(backgroundColor)
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = ripple(color = Color.White.copy(alpha = 0.3f))
            )
            .padding(horizontal = ButtonHorizontalPadding, vertical = ButtonVerticalPadding),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CompositionLocalProvider(
                LocalContentColor provides contentColor,
                LocalTextStyle provides VetraTheme.typography.labelLg.copy(color = contentColor)
            ) {
                content()
            }
        }
    }
}

// ============================================================================
// Previews
// ============================================================================

@Preview
@Composable
private fun VetraButtonPreview() {
    VetraTheme {
        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Primary Buttons
            Text(
                "Primary Buttons",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                VetraButton(onClick = {}) {
                    Text("Button")
                }
                VetraButton(onClick = {}, enabled = false) {
                    Text("Disabled")
                }
            }

            // Secondary Buttons
            Text(
                "Secondary Buttons",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                VetraSecondaryButton(onClick = {}) {
                    Text("Secondary")
                }
                VetraSecondaryButton(onClick = {}, enabled = false) {
                    Text("Disabled")
                }
            }

            // Outlined Buttons
            Text(
                "Outlined Buttons",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                VetraOutlinedButton(onClick = {}) {
                    Text("Outlined")
                }
                VetraOutlinedButton(onClick = {}, enabled = false) {
                    Text("Disabled")
                }
            }

            // Ghost Buttons
            Text(
                "Ghost Buttons",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                VetraGhostButton(onClick = {}) {
                    Text("Ghost")
                }
                VetraGhostButton(onClick = {}, enabled = false) {
                    Text("Disabled")
                }
            }

            // Danger Buttons
            Text(
                "Danger Buttons",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                VetraDangerButton(onClick = {}) {
                    Text("Delete")
                }
                VetraDangerButton(onClick = {}, enabled = false) {
                    Text("Disabled")
                }
            }
        }
    }
}

@Preview
@Composable
private fun VetraButtonDarkPreview() {
    VetraTheme(darkMode = true) {
        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                VetraButton(onClick = {}) {
                    Text("Primary")
                }
                VetraSecondaryButton(onClick = {}) {
                    Text("Secondary")
                }
                VetraOutlinedButton(onClick = {}) {
                    Text("Outlined")
                }
            }
        }
    }
}
