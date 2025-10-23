package com.flyfishxu.vetraui.core

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.flyfishxu.vetraui.core.theme.VetraTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Vetra Top App Bar
 *
 * A modern, clean top application bar that embodies Vetra's design philosophy.
 * Features minimal visual weight with elegant transitions.
 *
 * Design Features:
 * - Translucent background with subtle border
 * - Clean typography with generous spacing
 * - Smooth scroll-based transitions
 * - No heavy shadows, just elegant separation
 */

private val TopAppBarHeight = 64.dp
private val LargeTopAppBarCollapsedHeight = 64.dp
private val LargeTopAppBarExpandedHeight = 152.dp

/**
 * Standard Top App Bar - Clean and minimal
 *
 * @param title Title content
 * @param modifier Modifier for the app bar
 * @param navigationIcon Optional navigation icon (back/menu)
 * @param actions Row of action buttons
 * @param scrolled Whether content is scrolled (affects border visibility)
 */
@Composable
fun VetraTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    scrolled: Boolean = false
) {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography

    // Subtle border animation
    val borderAlpha by animateFloatAsState(
        targetValue = if (scrolled) 1f else 0f,
        animationSpec = tween(durationMillis = 200),
        label = "borderAlpha"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.canvasElevated.copy(alpha = 0.98f))
            .statusBarsPadding()
            .drawBehind {
                // Bottom border
                if (borderAlpha > 0f) {
                    val borderWidth = 1.dp.toPx()
                    drawLine(
                        color = colors.borderSubtle.copy(alpha = borderAlpha),
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = borderWidth
                    )
                }
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(TopAppBarHeight)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Navigation icon
            if (navigationIcon != null) {
                Box(contentAlignment = Alignment.Center) {
                    CompositionLocalProvider(
                        LocalContentColor provides colors.textPrimary
                    ) {
                        navigationIcon()
                    }
                }
            }

            // Title
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                CompositionLocalProvider(
                    LocalContentColor provides colors.textPrimary
                ) {
                    CompositionLocalProvider(
                        LocalTextStyle provides typography.headingSm
                    ) {
                        title()
                    }
                }
            }

            // Actions
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CompositionLocalProvider(
                    LocalContentColor provides colors.textSecondary
                ) {
                    actions()
                }
            }
        }
    }
}

/**
 * Large Top App Bar - Collapsible header with scroll effect
 *
 * Features a large title that collapses smoothly as content scrolls.
 *
 * @param title Large title content
 * @param modifier Modifier for the app bar
 * @param navigationIcon Optional navigation icon
 * @param actions Row of action buttons
 * @param scrollProgress Scroll progress (0f = expanded, 1f = collapsed)
 */
@Composable
fun VetraLargeTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    scrollProgress: Float = 0f
) {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography

    // Interpolate height
    val height = lerp(
        LargeTopAppBarExpandedHeight,
        LargeTopAppBarCollapsedHeight,
        scrollProgress
    )

    // Title animations
    val titleAlpha by animateFloatAsState(
        targetValue = 1f - scrollProgress,
        animationSpec = tween(durationMillis = 150),
        label = "titleAlpha"
    )

    val collapsedTitleAlpha by animateFloatAsState(
        targetValue = scrollProgress,
        animationSpec = tween(durationMillis = 150),
        label = "collapsedTitleAlpha"
    )

    val titleScale by animateFloatAsState(
        targetValue = 1f - (scrollProgress * 0.15f),
        animationSpec = tween(durationMillis = 150),
        label = "titleScale"
    )

    // Border animation
    val borderAlpha by animateFloatAsState(
        targetValue = scrollProgress,
        animationSpec = tween(durationMillis = 200),
        label = "borderAlpha"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.canvasElevated.copy(alpha = 0.98f))
            .statusBarsPadding()
            .height(height)
            .drawBehind {
                // Bottom border
                if (borderAlpha > 0f) {
                    val borderWidth = 1.dp.toPx()
                    drawLine(
                        color = colors.borderSubtle.copy(alpha = borderAlpha),
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = borderWidth
                    )
                }
            }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Top bar with icons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(TopAppBarHeight)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Navigation icon
                if (navigationIcon != null) {
                    Box(contentAlignment = Alignment.Center) {
                        CompositionLocalProvider(
                            LocalContentColor provides colors.textPrimary
                        ) {
                            navigationIcon()
                        }
                    }
                }

                // Collapsed title
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .alpha(collapsedTitleAlpha),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = title,
                        style = typography.headingSm.copy(color = colors.textPrimary),
                        maxLines = 1
                    )
                }

                // Actions
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CompositionLocalProvider(
                        LocalContentColor provides colors.textSecondary
                    ) {
                        actions()
                    }
                }
            }

            // Large title area
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 20.dp)
                    .alpha(titleAlpha)
                    .scale(titleScale),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = title,
                    style = typography.displaySm.copy(color = colors.textPrimary),
                    maxLines = 2
                )
            }
        }
    }
}

/**
 * Center Aligned Top App Bar - Symmetrical layout
 *
 * @param title Title content (centered)
 * @param modifier Modifier for the app bar
 * @param navigationIcon Optional navigation icon
 * @param actions Row of action buttons
 * @param scrolled Whether content is scrolled
 */
@Composable
fun VetraCenterTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    scrolled: Boolean = false
) {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography

    val borderAlpha by animateFloatAsState(
        targetValue = if (scrolled) 1f else 0f,
        animationSpec = tween(durationMillis = 200),
        label = "borderAlpha"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.canvasElevated.copy(alpha = 0.98f))
            .drawBehind {
                if (borderAlpha > 0f) {
                    val borderWidth = 1.dp.toPx()
                    drawLine(
                        color = colors.borderSubtle.copy(alpha = borderAlpha),
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = borderWidth
                    )
                }
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(TopAppBarHeight)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left side - Navigation
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                if (navigationIcon != null) {
                    CompositionLocalProvider(
                        LocalContentColor provides colors.textPrimary
                    ) {
                        navigationIcon()
                    }
                }
            }

            // Center - Title
            Box(contentAlignment = Alignment.Center) {
                CompositionLocalProvider(
                    LocalContentColor provides colors.textPrimary
                ) {
                    CompositionLocalProvider(
                        LocalTextStyle provides typography.headingSm
                    ) {
                        title()
                    }
                }
            }

            // Right side - Actions
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterEnd
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CompositionLocalProvider(
                        LocalContentColor provides colors.textSecondary
                    ) {
                        actions()
                    }
                }
            }
        }
    }
}

// ============================================================================
// Previews
// ============================================================================

@Preview
@Composable
private fun VetraTopAppBarPreview() {
    VetraTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(VetraTheme.colors.canvas)
        ) {
            // Standard
            VetraTopAppBar(
                title = { Text("Standard Title") },
                navigationIcon = {
                    VetraIconButton(
                        onClick = {},
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                },
                actions = {
                    VetraIconButton(
                        onClick = {},
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search"
                    )
                    VetraIconButton(
                        onClick = {},
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "More"
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Scrolled state
            VetraTopAppBar(
                title = { Text("Scrolled State") },
                scrolled = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Center aligned
            VetraCenterTopAppBar(
                title = { Text("Center Title") },
                navigationIcon = {
                    VetraIconButton(
                        onClick = {},
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu"
                    )
                },
                actions = {
                    VetraIconButton(
                        onClick = {},
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search"
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun VetraLargeTopAppBarPreview() {
    VetraTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(VetraTheme.colors.canvas)
        ) {
            // Expanded
            VetraLargeTopAppBar(
                title = "Large Title",
                navigationIcon = {
                    VetraIconButton(
                        onClick = {},
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                },
                actions = {
                    VetraIconButton(
                        onClick = {},
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search"
                    )
                },
                scrollProgress = 0f
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Partially collapsed
            VetraLargeTopAppBar(
                title = "Large Title",
                navigationIcon = {
                    VetraIconButton(
                        onClick = {},
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                },
                actions = {
                    VetraIconButton(
                        onClick = {},
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search"
                    )
                },
                scrollProgress = 0.5f
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Fully collapsed
            VetraLargeTopAppBar(
                title = "Large Title",
                navigationIcon = {
                    VetraIconButton(
                        onClick = {},
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                },
                actions = {
                    VetraIconButton(
                        onClick = {},
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search"
                    )
                },
                scrollProgress = 1f
            )
        }
    }
}

@Preview
@Composable
private fun VetraTopAppBarDarkPreview() {
    VetraTheme(darkMode = true) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(VetraTheme.colors.canvas)
        ) {
            VetraTopAppBar(
                title = { Text("Dark Mode") },
                navigationIcon = {
                    VetraIconButton(
                        onClick = {},
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu"
                    )
                },
                actions = {
                    VetraIconButton(
                        onClick = {},
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search"
                    )
                }
            )
        }
    }
}
