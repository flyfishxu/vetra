package com.flyfishxu.vetraui.core.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.FolderOpen
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.flyfishxu.vetraui.core.theme.VetraTheme
import com.flyfishxu.vetraui.core.theme.vetraShadow
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Vetra UI Menu System
 *
 * A refined dropdown menu system with smooth animations and elegant styling.
 * Designed to feel lightweight and natural, with careful attention to timing and easing.
 */

// Constants
private val MenuMinWidth = 180.dp
private val MenuMaxWidth = 280.dp
private val MenuItemHeight = 48.dp
private val MenuItemPadding = 16.dp
private val MenuItemIconSize = 20.dp
private val MenuItemSpacing = 8.dp
private val MenuDividerHeight = 1.dp
private val MenuDividerMargin = 8.dp
private val MenuPadding = 8.dp
private val MenuIconSize = 20.dp
private const val MenuAnimationDuration = 250
private const val MenuItemAnimationDuration = 150

/**
 * VetraDropdownMenu
 *
 * A dropdown menu that appears in a popup.
 * Use with VetraMenuItem to create menu items.
 *
 * @param expanded Whether the menu is currently visible
 * @param onDismissRequest Called when the user requests to dismiss the menu
 * @param modifier Modifier for the menu container
 * @param offset Offset from the anchor point in pixels
 * @param content Menu content, typically VetraMenuItem composables
 */
@Composable
fun VetraDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    offset: IntOffset = IntOffset(0, 0),
    content: @Composable ColumnScope.() -> Unit
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val shadows = VetraTheme.shadows

    if (expanded) {
        Popup(
            onDismissRequest = onDismissRequest,
            offset = offset,
            properties = PopupProperties(
                focusable = true,
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            // Animated appearance
            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn(
                    animationSpec = tween(
                        durationMillis = MenuAnimationDuration,
                        easing = EaseOut
                    )
                ) + expandVertically(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMedium
                    ),
                    expandFrom = Alignment.Top
                ),
                exit = fadeOut(
                    animationSpec = tween(
                        durationMillis = MenuAnimationDuration / 2,
                        easing = EaseIn
                    )
                ) + shrinkVertically(
                    animationSpec = tween(
                        durationMillis = MenuAnimationDuration / 2,
                        easing = EaseIn
                    ),
                    shrinkTowards = Alignment.Top
                )
            ) {
                Column(
                    modifier = modifier
                        .widthIn(min = MenuMinWidth, max = MenuMaxWidth)
                        .vetraShadow(elevation = shadows.lg, shape = shapes.md)
                        .clip(shapes.md)
                        .background(colors.canvasElevated)
                        .padding(vertical = MenuPadding),
                    content = content
                )
            }
        }
    }
}

/**
 * VetraMenuItem
 *
 * A single item in a VetraDropdownMenu.
 *
 * @param text The text to display
 * @param onClick Called when the item is clicked
 * @param modifier Modifier for the item
 * @param leadingIcon Optional icon to display before the text
 * @param trailingIcon Optional icon to display after the text
 * @param enabled Whether the item is enabled
 * @param contentColor Color for the text and icons
 */
@Composable
fun VetraMenuItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    enabled: Boolean = true,
    contentColor: Color = VetraTheme.colors.textPrimary
) {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography
    val shapes = VetraTheme.shapes

    val finalContentColor = if (enabled) contentColor else colors.textDisabled

    // Hover state for desktop
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    // Animated background color on hover
    val backgroundColor by animateColorAsState(
        targetValue = when {
            !enabled -> Color.Transparent
            isHovered -> colors.canvasSubtle
            else -> Color.Transparent
        },
        animationSpec = tween(durationMillis = MenuItemAnimationDuration),
        label = "menuItemBackground"
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(MenuItemHeight)
            .clip(shapes.xs)
            .background(backgroundColor)
            .hoverable(interactionSource = interactionSource)
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.DropdownList,
                interactionSource = interactionSource,
                indication = null
            )
            .padding(horizontal = MenuItemPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MenuItemSpacing)
    ) {
        // Leading icon
        if (leadingIcon != null) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                modifier = Modifier.size(MenuItemIconSize),
                tint = finalContentColor
            )
        }

        // Text
        Text(
            text = text,
            style = typography.bodyMd,
            color = finalContentColor,
            modifier = Modifier.weight(1f)
        )

        // Trailing icon
        if (trailingIcon != null) {
            Icon(
                imageVector = trailingIcon,
                contentDescription = null,
                modifier = Modifier.size(MenuItemIconSize),
                tint = finalContentColor
            )
        }
    }
}

/**
 * VetraMenuDivider
 *
 * A horizontal divider line for separating menu sections.
 */
@Composable
fun VetraMenuDivider(
    modifier: Modifier = Modifier
) {
    val colors = VetraTheme.colors

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = MenuDividerMargin, vertical = MenuDividerMargin / 2)
            .height(MenuDividerHeight)
            .background(colors.borderSubtle)
    )
}

/**
 * VetraMenuLabel
 *
 * A non-interactive label for menu sections.
 *
 * @param text The label text
 * @param modifier Modifier for the label
 */
@Composable
fun VetraMenuLabel(
    text: String,
    modifier: Modifier = Modifier
) {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = MenuItemPadding, vertical = 8.dp)
    ) {
        Text(
            text = text,
            style = typography.labelSm,
            color = colors.textTertiary
        )
    }
}

/**
 * VetraContextMenu
 *
 * A context menu that can be triggered by right-click or long-press.
 * This is a convenience wrapper around VetraDropdownMenu.
 *
 * @param expanded Whether the menu is visible
 * @param onDismissRequest Called when the menu should be dismissed
 * @param modifier Modifier for the menu
 * @param content Menu content
 */
@Composable
fun VetraContextMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    VetraDropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        offset = IntOffset(0, 0),
        content = content
    )
}

/**
 * VetraMenuButton
 *
 * A button that triggers a dropdown menu.
 * Combines a VetraButton with a VetraDropdownMenu for easy use.
 *
 * @param text Button text
 * @param modifier Modifier for the button
 * @param enabled Whether the button is enabled
 * @param leadingIcon Optional leading icon
 * @param content Menu content
 */
@Composable
fun VetraMenuButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        VetraButton(
            onClick = { expanded = true },
            modifier = modifier,
            enabled = enabled
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                }
                Text(text)
            }
        }

        VetraDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            content = content
        )
    }
}

/**
 * VetraIconMenuButton
 *
 * An icon button that triggers a dropdown menu.
 *
 * @param icon The icon to display
 * @param contentDescription Content description for accessibility
 * @param modifier Modifier for the button
 * @param enabled Whether the button is enabled
 * @param content Menu content
 */
@Composable
fun VetraIconMenuButton(
    icon: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        VetraIconButton(
            onClick = { expanded = true },
            modifier = modifier,
            enabled = enabled
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                modifier = Modifier.size(MenuIconSize)
            )
        }

        VetraDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            content = content
        )
    }
}

// ============================================================================
// Previews
// ============================================================================

@Preview
@Composable
private fun MenuPreview() {
    VetraTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            VetraMenuButton(text = "File Menu") {
                VetraMenuItem(
                    text = "New",
                    onClick = {},
                    leadingIcon = Icons.Outlined.Add
                )
                VetraMenuItem(
                    text = "Open",
                    onClick = {},
                    leadingIcon = Icons.Outlined.FolderOpen
                )
                VetraMenuDivider()
                VetraMenuItem(
                    text = "Exit",
                    onClick = {},
                    leadingIcon = Icons.AutoMirrored.Outlined.ExitToApp
                )
            }
        }
    }
}

@Preview
@Composable
private fun MenuDarkPreview() {
    VetraTheme(darkMode = true) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            VetraIconMenuButton(
                icon = Icons.Filled.MoreVert,
                contentDescription = "More"
            ) {
                VetraMenuLabel("Actions")
                VetraMenuItem(
                    text = "Settings",
                    onClick = {},
                    leadingIcon = Icons.Outlined.Settings
                )
                VetraMenuItem(
                    text = "Profile",
                    onClick = {},
                    leadingIcon = Icons.Outlined.Person
                )
                VetraMenuDivider()
                VetraMenuItem(
                    text = "Logout",
                    onClick = {},
                    leadingIcon = Icons.AutoMirrored.Outlined.Logout,
                    contentColor = VetraTheme.colors.danger
                )
            }
        }
    }
}
