package com.flyfishxu.vetraui.core

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.theme.VetraTheme
import com.flyfishxu.vetraui.core.theme.vetraShadow
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Vetra Chip
 *
 * A modern, elegant chip/tag component for filters, selections, and compact actions.
 * Designed to be more interactive and flexible than badges.
 *
 * Chip Variants:
 * - Standard (VetraChip): Solid background for default states
 * - Outlined (VetraChipOutlined): Border only for subtle emphasis
 * - Elevated (VetraChipElevated): Elevated with shadow for prominence
 * - Assist (VetraChipAssist): Action chip for suggestions
 * - Filter (VetraChipFilter): Filter chip with selection state
 *
 * Design Features:
 * - Interactive with click support
 * - Optional leading icon
 * - Optional trailing action (like close button)
 * - Selection state support
 * - Clean, modern aesthetics
 * - Consistent with Vetra design system
 */

private val ChipHeight = 32.dp
private val ChipHorizontalPadding = 12.dp
private val ChipVerticalPadding = 6.dp
private val ChipIconSize = 18.dp
private val ChipTrailingIconSize = 16.dp

/**
 * Standard Chip - Solid background for default states
 *
 * Use for tags, categories, or selections.
 *
 * @param label Chip text label
 * @param modifier Modifier for the chip
 * @param onClick Optional click handler
 * @param enabled Whether the chip is enabled
 * @param leadingIcon Optional leading icon
 * @param trailingIcon Optional trailing icon or action
 * @param onTrailingIconClick Optional click handler for trailing icon
 */
@Composable
fun VetraChip(
    label: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: (() -> Unit)? = null
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val typography = VetraTheme.typography
    val interactionSource = remember { MutableInteractionSource() }

    val backgroundColor = if (enabled) colors.canvasSubtle else colors.borderSubtle
    val contentColor = if (enabled) colors.textPrimary else colors.textDisabled

    val baseModifier = modifier
        .clip(shapes.xs)
        .background(backgroundColor)

    val finalModifier = if (onClick != null) {
        baseModifier.clickable(
            onClick = onClick,
            enabled = enabled,
            role = Role.Button,
            interactionSource = interactionSource,
            indication = null
        )
    } else {
        baseModifier
    }

    Row(
        modifier = finalModifier
            .padding(horizontal = ChipHorizontalPadding, vertical = ChipVerticalPadding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leadingIcon != null) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                modifier = Modifier.size(ChipIconSize),
                tint = contentColor
            )
            Spacer(modifier = Modifier.width(6.dp))
        }

        CompositionLocalProvider(
            LocalContentColor provides contentColor,
            LocalTextStyle provides typography.labelMd.copy(color = contentColor)
        ) {
            Text(label)
        }

        if (trailingIcon != null) {
            Spacer(modifier = Modifier.width(4.dp))
            val trailingInteractionSource = remember { MutableInteractionSource() }
            val trailingModifier = if (onTrailingIconClick != null) {
                Modifier
                    .size(ChipTrailingIconSize)
                    .clickable(
                        onClick = onTrailingIconClick,
                        enabled = enabled,
                        role = Role.Button,
                        interactionSource = trailingInteractionSource,
                        indication = null
                    )
            } else {
                Modifier.size(ChipTrailingIconSize)
            }

            Icon(
                imageVector = trailingIcon,
                contentDescription = if (onTrailingIconClick != null) "Remove" else null,
                modifier = trailingModifier,
                tint = contentColor
            )
        }
    }
}

/**
 * Outlined Chip - Border only for subtle emphasis
 *
 * Use for subtle tags or when you need lighter visual weight.
 *
 * @param label Chip text label
 * @param modifier Modifier for the chip
 * @param onClick Optional click handler
 * @param enabled Whether the chip is enabled
 * @param leadingIcon Optional leading icon
 * @param trailingIcon Optional trailing icon or action
 * @param onTrailingIconClick Optional click handler for trailing icon
 */
@Composable
fun VetraChipOutlined(
    label: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: (() -> Unit)? = null
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val typography = VetraTheme.typography
    val interactionSource = remember { MutableInteractionSource() }

    val borderColor = if (enabled) colors.border else colors.borderSubtle
    val contentColor = if (enabled) colors.textPrimary else colors.textDisabled

    val baseModifier = modifier
        .clip(shapes.xs)
        .background(Color.Transparent)
        .then(
            // Border implementation
            Modifier
                .padding(1.dp)
                .background(borderColor, shapes.xs)
                .padding(1.dp)
                .clip(shapes.xs)
                .background(Color.Transparent, shapes.xs)
        )

    val finalModifier = if (onClick != null) {
        baseModifier.clickable(
            onClick = onClick,
            enabled = enabled,
            role = Role.Button,
            interactionSource = interactionSource,
            indication = null
        )
    } else {
        baseModifier
    }

    Row(
        modifier = finalModifier
            .padding(
                horizontal = ChipHorizontalPadding - 2.dp,
                vertical = ChipVerticalPadding - 2.dp
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leadingIcon != null) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                modifier = Modifier.size(ChipIconSize),
                tint = contentColor
            )
            Spacer(modifier = Modifier.width(6.dp))
        }

        CompositionLocalProvider(
            LocalContentColor provides contentColor,
            LocalTextStyle provides typography.labelMd.copy(color = contentColor)
        ) {
            Text(label)
        }

        if (trailingIcon != null) {
            Spacer(modifier = Modifier.width(4.dp))
            val trailingInteractionSource = remember { MutableInteractionSource() }
            val trailingModifier = if (onTrailingIconClick != null) {
                Modifier
                    .size(ChipTrailingIconSize)
                    .clickable(
                        onClick = onTrailingIconClick,
                        enabled = enabled,
                        role = Role.Button,
                        interactionSource = trailingInteractionSource,
                        indication = null
                    )
            } else {
                Modifier.size(ChipTrailingIconSize)
            }

            Icon(
                imageVector = trailingIcon,
                contentDescription = if (onTrailingIconClick != null) "Remove" else null,
                modifier = trailingModifier,
                tint = contentColor
            )
        }
    }
}

/**
 * Elevated Chip - Elevated with shadow for prominence
 *
 * Use for prominent tags or selections that need emphasis.
 *
 * @param label Chip text label
 * @param modifier Modifier for the chip
 * @param onClick Optional click handler
 * @param enabled Whether the chip is enabled
 * @param leadingIcon Optional leading icon
 * @param trailingIcon Optional trailing icon or action
 * @param onTrailingIconClick Optional click handler for trailing icon
 */
@Composable
fun VetraChipElevated(
    label: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: (() -> Unit)? = null
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val shadows = VetraTheme.shadows
    val typography = VetraTheme.typography
    val interactionSource = remember { MutableInteractionSource() }

    val backgroundColor = if (enabled) colors.canvasElevated else colors.borderSubtle
    val contentColor = if (enabled) colors.textPrimary else colors.textDisabled
    val shadow = if (enabled) shadows.sm else shadows.none

    val baseModifier = modifier
        .vetraShadow(elevation = shadow, shape = shapes.xs)
        .clip(shapes.xs)
        .background(backgroundColor)

    val finalModifier = if (onClick != null) {
        baseModifier.clickable(
            onClick = onClick,
            enabled = enabled,
            role = Role.Button,
            interactionSource = interactionSource,
            indication = null
        )
    } else {
        baseModifier
    }

    Row(
        modifier = finalModifier
            .padding(horizontal = ChipHorizontalPadding, vertical = ChipVerticalPadding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leadingIcon != null) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                modifier = Modifier.size(ChipIconSize),
                tint = contentColor
            )
            Spacer(modifier = Modifier.width(6.dp))
        }

        CompositionLocalProvider(
            LocalContentColor provides contentColor,
            LocalTextStyle provides typography.labelMd.copy(color = contentColor)
        ) {
            Text(label)
        }

        if (trailingIcon != null) {
            Spacer(modifier = Modifier.width(4.dp))
            val trailingInteractionSource = remember { MutableInteractionSource() }
            val trailingModifier = if (onTrailingIconClick != null) {
                Modifier
                    .size(ChipTrailingIconSize)
                    .clickable(
                        onClick = onTrailingIconClick,
                        enabled = enabled,
                        role = Role.Button,
                        interactionSource = trailingInteractionSource,
                        indication = null
                    )
            } else {
                Modifier.size(ChipTrailingIconSize)
            }

            Icon(
                imageVector = trailingIcon,
                contentDescription = if (onTrailingIconClick != null) "Remove" else null,
                modifier = trailingModifier,
                tint = contentColor
            )
        }
    }
}

/**
 * Assist Chip - Action chip for suggestions or quick actions
 *
 * Use for action suggestions, shortcuts, or smart replies.
 *
 * @param label Chip text label
 * @param modifier Modifier for the chip
 * @param onClick Click handler for the action
 * @param enabled Whether the chip is enabled
 * @param leadingIcon Optional leading icon
 */
@Composable
fun VetraChipAssist(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val typography = VetraTheme.typography
    val interactionSource = remember { MutableInteractionSource() }

    val backgroundColor = if (enabled) colors.brandSubtle else colors.borderSubtle
    val contentColor = if (enabled) colors.onBrandSubtle else colors.textDisabled

    Row(
        modifier = modifier
            .clip(shapes.xs)
            .background(backgroundColor)
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = null
            )
            .padding(horizontal = ChipHorizontalPadding, vertical = ChipVerticalPadding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leadingIcon != null) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                modifier = Modifier.size(ChipIconSize),
                tint = contentColor
            )
            Spacer(modifier = Modifier.width(6.dp))
        }

        CompositionLocalProvider(
            LocalContentColor provides contentColor,
            LocalTextStyle provides typography.labelMd.copy(color = contentColor)
        ) {
            Text(label)
        }
    }
}

/**
 * Filter Chip - Filter chip with selection state
 *
 * Use for filter selections or toggleable options.
 *
 * @param label Chip text label
 * @param selected Whether the chip is selected
 * @param onClick Click handler to toggle selection
 * @param modifier Modifier for the chip
 * @param enabled Whether the chip is enabled
 * @param leadingIcon Optional leading icon (shown when not selected)
 */
@Composable
fun VetraChipFilter(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val typography = VetraTheme.typography
    val interactionSource = remember { MutableInteractionSource() }

    val backgroundColor = when {
        !enabled -> colors.borderSubtle
        selected -> colors.brand
        else -> colors.canvasSubtle
    }

    val contentColor = when {
        !enabled -> colors.textDisabled
        selected -> colors.onBrand
        else -> colors.textPrimary
    }

    Row(
        modifier = modifier
            .clip(shapes.xs)
            .background(backgroundColor)
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Checkbox,
                interactionSource = interactionSource,
                indication = null
            )
            .padding(horizontal = ChipHorizontalPadding, vertical = ChipVerticalPadding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (selected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Selected",
                modifier = Modifier.size(ChipIconSize),
                tint = contentColor
            )
            Spacer(modifier = Modifier.width(6.dp))
        } else if (leadingIcon != null) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                modifier = Modifier.size(ChipIconSize),
                tint = contentColor
            )
            Spacer(modifier = Modifier.width(6.dp))
        }

        CompositionLocalProvider(
            LocalContentColor provides contentColor,
            LocalTextStyle provides typography.labelMd.copy(color = contentColor)
        ) {
            Text(label)
        }
    }
}

// ============================================================================
// Previews
// ============================================================================

@Preview
@Composable
private fun VetraChipPreview() {
    VetraTheme {
        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Standard Chips
            Text(
                "Standard Chips",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                VetraChip(label = "Simple")
                VetraChip(label = "With Icon", leadingIcon = Icons.Default.Add)
                VetraChip(
                    label = "Removable",
                    trailingIcon = Icons.Default.Close,
                    onTrailingIconClick = {}
                )
                VetraChip(label = "Disabled", enabled = false)
            }

            // Outlined Chips
            Text(
                "Outlined Chips",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                VetraChipOutlined(label = "Kotlin")
                VetraChipOutlined(label = "Compose", leadingIcon = Icons.Default.Add)
                VetraChipOutlined(
                    label = "Android",
                    trailingIcon = Icons.Default.Close,
                    onTrailingIconClick = {}
                )
            }

            // Elevated Chips
            Text(
                "Elevated Chips",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                VetraChipElevated(label = "Elevated")
                VetraChipElevated(label = "Featured", leadingIcon = Icons.Default.Add)
                VetraChipElevated(
                    label = "Premium",
                    trailingIcon = Icons.Default.Close,
                    onTrailingIconClick = {}
                )
            }

            // Assist Chips
            Text(
                "Assist Chips",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                VetraChipAssist(label = "Add to cart", onClick = {})
                VetraChipAssist(label = "Share", onClick = {}, leadingIcon = Icons.Default.Add)
                VetraChipAssist(label = "Bookmark", onClick = {}, leadingIcon = Icons.Default.Add)
            }

            // Filter Chips
            Text(
                "Filter Chips",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                VetraChipFilter(label = "All", selected = true, onClick = {})
                VetraChipFilter(label = "Active", selected = false, onClick = {})
                VetraChipFilter(label = "Completed", selected = false, onClick = {})
                VetraChipFilter(
                    label = "Archived",
                    selected = false,
                    onClick = {},
                    leadingIcon = Icons.Default.Add
                )
            }

            // Usage Example
            Text(
                "Usage Example",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    "Selected Tags:",
                    style = VetraTheme.typography.bodyMd.copy(color = VetraTheme.colors.textSecondary)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    VetraChip(
                        label = "Design",
                        trailingIcon = Icons.Default.Close,
                        onTrailingIconClick = {}
                    )
                    VetraChip(
                        label = "Development",
                        trailingIcon = Icons.Default.Close,
                        onTrailingIconClick = {}
                    )
                    VetraChip(
                        label = "UI/UX",
                        trailingIcon = Icons.Default.Close,
                        onTrailingIconClick = {}
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun VetraChipDarkPreview() {
    VetraTheme(darkMode = true) {
        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                VetraChip(label = "Standard")
                VetraChipOutlined(label = "Outlined")
                VetraChipElevated(label = "Elevated")
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                VetraChipAssist(label = "Assist", onClick = {})
                VetraChipFilter(label = "Selected", selected = true, onClick = {})
                VetraChipFilter(label = "Unselected", selected = false, onClick = {})
            }
        }
    }
}

