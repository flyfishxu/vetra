package com.flyfishxu.vetraui.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
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
 * Vetra Icon Button
 *
 * A modern icon button component with multiple variants.
 * Designed to be simple and elegant with clear visual states.
 *
 * Button Variants:
 * - Standard: No background, minimal
 * - Filled: Solid background
 * - Outlined: Border only
 *
 * Design Features:
 * - Clean, modern aesthetics
 * - Clear hover/press states
 * - Accessible by default
 * - Consistent sizing
 */

private val IconButtonSize = 40.dp
private val IconSize = 20.dp

/**
 * Standard Icon Button - No background, minimal
 *
 * Use for secondary actions or in toolbars.
 *
 * @param onClick Called when the button is clicked
 * @param modifier Modifier for the button
 * @param enabled Whether the button is enabled
 * @param content Button content, typically an Icon
 */
@Composable
fun VetraIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val interactionSource = remember { MutableInteractionSource() }

    val contentColor = if (enabled) colors.textSecondary else colors.textDisabled

    Box(
        modifier = modifier
            .size(IconButtonSize)
            .clip(shapes.sm)
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = null
            ),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(
            LocalContentColor provides contentColor
        ) {
            content()
        }
    }
}

/**
 * Convenience overload for VetraIconButton that accepts ImageVector
 */
@Composable
fun VetraIconButton(
    onClick: () -> Unit,
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    VetraIconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = Modifier.size(IconSize)
        )
    }
}

/**
 * Filled Icon Button - Solid background for emphasis
 *
 * Use for primary icon actions.
 *
 * @param onClick Called when the button is clicked
 * @param modifier Modifier for the button
 * @param enabled Whether the button is enabled
 * @param content Button content, typically an Icon
 */
@Composable
fun VetraFilledIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val shadows = VetraTheme.shadows
    val interactionSource = remember { MutableInteractionSource() }

    val backgroundColor = if (enabled) colors.brand else colors.canvasSubtle
    val contentColor = if (enabled) colors.onBrand else colors.textDisabled
    val shadow = if (enabled) shadows.xs else shadows.none

    Box(
        modifier = modifier
            .size(IconButtonSize)
            .vetraShadow(elevation = shadow, shape = shapes.sm)
            .clip(shapes.sm)
            .background(backgroundColor)
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = null
            ),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(
            LocalContentColor provides contentColor
        ) {
            content()
        }
    }
}

/**
 * Convenience overload for VetraFilledIconButton that accepts ImageVector
 */
@Composable
fun VetraFilledIconButton(
    onClick: () -> Unit,
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    VetraFilledIconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = Modifier.size(IconSize)
        )
    }
}

/**
 * Outlined Icon Button - Border only
 *
 * Use for tertiary icon actions.
 *
 * @param onClick Called when the button is clicked
 * @param modifier Modifier for the button
 * @param enabled Whether the button is enabled
 * @param content Button content, typically an Icon
 */
@Composable
fun VetraOutlinedIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val interactionSource = remember { MutableInteractionSource() }

    val borderColor = if (enabled) colors.border else colors.borderSubtle
    val contentColor = if (enabled) colors.brand else colors.textDisabled

    Box(
        modifier = modifier
            .size(IconButtonSize)
            .clip(shapes.sm)
            .background(Color.Transparent)
            .then(
                // Border implementation
                Modifier
                    .padding(1.dp)
                    .background(borderColor, shapes.sm)
                    .padding(1.dp)
                    .background(Color.Transparent, shapes.sm)
            )
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = null
            ),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(
            LocalContentColor provides contentColor
        ) {
            content()
        }
    }
}

/**
 * Convenience overload for VetraOutlinedIconButton that accepts ImageVector
 */
@Composable
fun VetraOutlinedIconButton(
    onClick: () -> Unit,
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    VetraOutlinedIconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = Modifier.size(IconSize)
        )
    }
}

// ============================================================================
// Previews
// ============================================================================

@Preview
@Composable
private fun VetraIconButtonPreview() {
    VetraTheme {
        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Icon Buttons",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            // Standard
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                VetraIconButton(
                    onClick = {},
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings"
                )
                VetraIconButton(
                    onClick = {},
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings",
                    enabled = false
                )
            }

            // Filled
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                VetraFilledIconButton(
                    onClick = {},
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Like"
                )
                VetraFilledIconButton(
                    onClick = {},
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Like",
                    enabled = false
                )
            }

            // Outlined
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                VetraOutlinedIconButton(
                    onClick = {},
                    imageVector = Icons.Filled.FavoriteBorder,
                    contentDescription = "Like"
                )
                VetraOutlinedIconButton(
                    onClick = {},
                    imageVector = Icons.Filled.FavoriteBorder,
                    contentDescription = "Like",
                    enabled = false
                )
            }
        }
    }
}
