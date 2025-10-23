package com.flyfishxu.vetraui.core.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.theme.VetraTheme
import com.flyfishxu.vetraui.core.theme.vetraShadow
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Vetra Navigation Bar
 *
 * A modern bottom navigation bar with elegant animations and clean design.
 * Features smooth transitions and a unique pill-shaped indicator.
 *
 * Design Features:
 * - Animated pill indicator with smooth transitions
 * - Icon scale animations for selected state
 * - Subtle label fade effects
 * - Clean, minimal aesthetic
 * - No heavy shadows, just elevation through color
 */

private val NavigationBarHeight = 72.dp
private val NavigationItemSize = 56.dp
private val IconSize = 24.dp
private val AnimationDuration = 300

/**
 * Navigation Bar Container
 *
 * @param modifier Modifier for the navigation bar
 * @param content Navigation items
 */
@Composable
fun VetraNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val shadows = VetraTheme.shadows

    Box(
        modifier = modifier
            .fillMaxWidth()
            .vetraShadow(
                elevation = shadows.lg,
                shape = shapes.none
            )
            .background(colors.canvasElevated)
            .navigationBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(NavigationBarHeight)
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            content()
        }
    }
}

/**
 * Navigation Bar Item
 *
 * Individual navigation item with animated states.
 *
 * @param selected Whether this item is selected
 * @param onClick Called when the item is clicked
 * @param icon Icon to display
 * @param modifier Modifier for the item
 * @param enabled Whether the item is enabled
 * @param label Optional label text
 * @param selectedIcon Optional different icon when selected
 */
@Composable
fun RowScope.VetraNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    selectedIcon: @Composable (() -> Unit)? = null
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val interactionSource = remember { MutableInteractionSource() }

    // Animated colors
    val iconColor by animateColorAsState(
        targetValue = if (selected) colors.brand else colors.textSecondary,
        animationSpec = tween(durationMillis = AnimationDuration, easing = EaseInOutCubic),
        label = "iconColor"
    )

    val labelColor by animateColorAsState(
        targetValue = if (selected) colors.textPrimary else colors.textTertiary,
        animationSpec = tween(durationMillis = AnimationDuration, easing = EaseInOutCubic),
        label = "labelColor"
    )

    val backgroundColor by animateColorAsState(
        targetValue = if (selected) colors.brandSubtle else androidx.compose.ui.graphics.Color.Transparent,
        animationSpec = tween(durationMillis = AnimationDuration, easing = EaseInOutCubic),
        label = "backgroundColor"
    )

    // Animated scale - subtle icon animation only when selected
    val iconScale by animateFloatAsState(
        targetValue = if (selected) 1.05f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "iconScale"
    )

    // Indicator alpha for smooth appearance/disappearance
    val indicatorAlpha by animateFloatAsState(
        targetValue = if (selected) 1f else 0f,
        animationSpec = tween(durationMillis = AnimationDuration, easing = EaseInOutCubic),
        label = "indicatorAlpha"
    )

    // Label animation
    val labelAlpha by animateFloatAsState(
        targetValue = if (selected) 1f else 0.7f,
        animationSpec = tween(durationMillis = AnimationDuration),
        label = "labelAlpha"
    )

    Box(
        modifier = modifier
            .weight(1f)
            .fillMaxHeight()
            .clip(shapes.lg)
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = null
            ),
        contentAlignment = Alignment.Center
    ) {
        // Background indicator
        Box(
            modifier = Modifier
                .size(
                    width = NavigationItemSize * 1.8f,
                    height = NavigationItemSize * 1.5f
                )
                .alpha(indicatorAlpha)
                .background(
                    color = backgroundColor,
                    shape = shapes.full
                )
        )

        // Content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            // Icon
            Box(
                modifier = Modifier.scale(iconScale),
                contentAlignment = Alignment.Center
            ) {
                CompositionLocalProvider(
                    LocalContentColor provides iconColor
                ) {
                    if (selected && selectedIcon != null) {
                        selectedIcon()
                    } else {
                        icon()
                    }
                }
            }

            // Label
            if (label != null) {
                Spacer(modifier = Modifier.height(4.dp))
                Box(modifier = Modifier.alpha(labelAlpha)) {
                    CompositionLocalProvider(
                        LocalContentColor provides labelColor
                    ) {
                        label()
                    }
                }
            }
        }
    }
}

/**
 * Convenience overload that accepts ImageVector icons
 */
@Composable
fun RowScope.VetraNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
    selectedIcon: ImageVector? = null
) {
    val typography = VetraTheme.typography

    VetraNavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = label,
                modifier = Modifier.size(IconSize)
            )
        },
        modifier = modifier,
        enabled = enabled,
        label = if (label != null) {
            {
                Text(
                    text = label,
                    style = typography.labelSm,
                    maxLines = 1
                )
            }
        } else null,
        selectedIcon = if (selectedIcon != null) {
            {
                Icon(
                    imageVector = selectedIcon,
                    contentDescription = label,
                    modifier = Modifier.size(IconSize)
                )
            }
        } else null
    )
}

// ============================================================================
// Previews
// ============================================================================

@Preview
@Composable
private fun VetraNavigationBarPreview() {
    VetraTheme {
        var selectedItem by remember { mutableStateOf(0) }
        val items = listOf("Home", "Search", "Favorites", "Profile")
        val icons = listOf(
            Icons.Outlined.Home,
            Icons.Outlined.Search,
            Icons.Outlined.FavoriteBorder,
            Icons.Outlined.Person
        )
        val selectedIcons = listOf(
            Icons.Filled.Home,
            Icons.Filled.Search,
            Icons.Filled.Favorite,
            Icons.Filled.Person
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(VetraTheme.colors.canvas)
        ) {
            Spacer(modifier = Modifier.weight(1f))

            VetraNavigationBar {
                items.forEachIndexed { index, item ->
                    VetraNavigationBarItem(
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        icon = icons[index],
                        selectedIcon = selectedIcons[index],
                        label = item
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun VetraNavigationBarDarkPreview() {
    VetraTheme(darkMode = true) {
        var selectedItem by remember { mutableStateOf(1) }
        val items = listOf("Home", "Search", "Favorites", "Profile")
        val icons = listOf(
            Icons.Outlined.Home,
            Icons.Outlined.Search,
            Icons.Outlined.FavoriteBorder,
            Icons.Outlined.Person
        )
        val selectedIcons = listOf(
            Icons.Filled.Home,
            Icons.Filled.Search,
            Icons.Filled.Favorite,
            Icons.Filled.Person
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(VetraTheme.colors.canvas)
        ) {
            Spacer(modifier = Modifier.weight(1f))

            VetraNavigationBar {
                items.forEachIndexed { index, item ->
                    VetraNavigationBarItem(
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        icon = icons[index],
                        selectedIcon = selectedIcons[index],
                        label = item
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun VetraNavigationBarThreeItemsPreview() {
    VetraTheme {
        var selectedItem by remember { mutableStateOf(0) }
        val items = listOf("Home", "Search", "Profile")
        val icons = listOf(
            Icons.Outlined.Home,
            Icons.Outlined.Search,
            Icons.Outlined.Person
        )
        val selectedIcons = listOf(
            Icons.Filled.Home,
            Icons.Filled.Search,
            Icons.Filled.Person
        )

        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
        ) {
            Spacer(modifier = Modifier.weight(1f))

            VetraNavigationBar {
                items.forEachIndexed { index, item ->
                    VetraNavigationBarItem(
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        icon = icons[index],
                        selectedIcon = selectedIcons[index],
                        label = item
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun VetraNavigationBarWithoutLabelsPreview() {
    VetraTheme {
        var selectedItem by remember { mutableStateOf(2) }
        val icons = listOf(
            Icons.Outlined.Home,
            Icons.Outlined.Search,
            Icons.Outlined.FavoriteBorder,
            Icons.Outlined.Person
        )
        val selectedIcons = listOf(
            Icons.Filled.Home,
            Icons.Filled.Search,
            Icons.Filled.Favorite,
            Icons.Filled.Person
        )

        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
        ) {
            Spacer(modifier = Modifier.weight(1f))

            VetraNavigationBar {
                icons.forEachIndexed { index, icon ->
                    VetraNavigationBarItem(
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        icon = icon,
                        selectedIcon = selectedIcons[index]
                    )
                }
            }
        }
    }
}
