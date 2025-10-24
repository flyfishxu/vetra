package com.flyfishxu.vetraui.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.theme.VetraTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Vetra Badge
 *
 * A small, elegant badge component for displaying status, counts, or labels.
 * Designed to be compact and unobtrusive while maintaining clear visibility.
 *
 * Badge Variants:
 * - Standard (VetraBadge): Brand color for default states
 * - Secondary (VetraBadgeSecondary): Accent color for alternative emphasis
 * - Success (VetraBadgeSuccess): Green for positive states
 * - Warning (VetraBadgeWarning): Amber for cautionary states
 * - Danger (VetraBadgeDanger): Red for errors or alerts
 * - Info (VetraBadgeInfo): Cyan for informational states
 * - Outlined (VetraBadgeOutlined): Border only for subtle emphasis
 * - Dot (VetraBadgeDot): Small dot indicator for notifications
 *
 * Design Features:
 * - Compact size with clear readability
 * - Semantic color variants
 * - Consistent with Vetra design system
 * - Perfect for status indicators and counts
 */

private val BadgeHorizontalPadding = 6.dp
private val BadgeVerticalPadding = 2.dp
private val BadgeDotSize = 8.dp

/**
 * Standard Badge - Brand color for default states
 *
 * Use for primary status indicators or counts.
 *
 * @param text Badge text content
 * @param modifier Modifier for the badge
 */
@Composable
fun VetraBadge(
    text: String,
    modifier: Modifier = Modifier
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val typography = VetraTheme.typography

    Box(
        modifier = modifier
            .clip(shapes.xs)
            .background(colors.brand)
            .padding(horizontal = BadgeHorizontalPadding, vertical = BadgeVerticalPadding),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(
            LocalContentColor provides colors.onBrand,
            LocalTextStyle provides typography.labelSm.copy(color = colors.onBrand)
        ) {
            Text(text)
        }
    }
}

/**
 * Secondary Badge - Accent color for alternative emphasis
 *
 * Use for secondary status indicators or alternative categories.
 *
 * @param text Badge text content
 * @param modifier Modifier for the badge
 */
@Composable
fun VetraBadgeSecondary(
    text: String,
    modifier: Modifier = Modifier
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val typography = VetraTheme.typography

    Box(
        modifier = modifier
            .clip(shapes.xs)
            .background(colors.accent)
            .padding(horizontal = BadgeHorizontalPadding, vertical = BadgeVerticalPadding),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(
            LocalContentColor provides colors.onAccent,
            LocalTextStyle provides typography.labelSm.copy(color = colors.onAccent)
        ) {
            Text(text)
        }
    }
}

/**
 * Success Badge - Green for positive states
 *
 * Use for success states, confirmations, or positive indicators.
 *
 * @param text Badge text content
 * @param modifier Modifier for the badge
 */
@Composable
fun VetraBadgeSuccess(
    text: String,
    modifier: Modifier = Modifier
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val typography = VetraTheme.typography

    Box(
        modifier = modifier
            .clip(shapes.xs)
            .background(colors.success)
            .padding(horizontal = BadgeHorizontalPadding, vertical = BadgeVerticalPadding),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(
            LocalContentColor provides colors.onSuccess,
            LocalTextStyle provides typography.labelSm.copy(color = colors.onSuccess)
        ) {
            Text(text)
        }
    }
}

/**
 * Warning Badge - Amber for cautionary states
 *
 * Use for warnings, alerts, or states requiring attention.
 *
 * @param text Badge text content
 * @param modifier Modifier for the badge
 */
@Composable
fun VetraBadgeWarning(
    text: String,
    modifier: Modifier = Modifier
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val typography = VetraTheme.typography

    Box(
        modifier = modifier
            .clip(shapes.xs)
            .background(colors.warning)
            .padding(horizontal = BadgeHorizontalPadding, vertical = BadgeVerticalPadding),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(
            LocalContentColor provides colors.onWarning,
            LocalTextStyle provides typography.labelSm.copy(color = colors.onWarning)
        ) {
            Text(text)
        }
    }
}

/**
 * Danger Badge - Red for errors or alerts
 *
 * Use for error states, critical alerts, or negative indicators.
 *
 * @param text Badge text content
 * @param modifier Modifier for the badge
 */
@Composable
fun VetraBadgeDanger(
    text: String,
    modifier: Modifier = Modifier
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val typography = VetraTheme.typography

    Box(
        modifier = modifier
            .clip(shapes.xs)
            .background(colors.danger)
            .padding(horizontal = BadgeHorizontalPadding, vertical = BadgeVerticalPadding),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(
            LocalContentColor provides colors.onDanger,
            LocalTextStyle provides typography.labelSm.copy(color = colors.onDanger)
        ) {
            Text(text)
        }
    }
}

/**
 * Info Badge - Cyan for informational states
 *
 * Use for informational indicators or neutral states.
 *
 * @param text Badge text content
 * @param modifier Modifier for the badge
 */
@Composable
fun VetraBadgeInfo(
    text: String,
    modifier: Modifier = Modifier
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val typography = VetraTheme.typography

    Box(
        modifier = modifier
            .clip(shapes.xs)
            .background(colors.info)
            .padding(horizontal = BadgeHorizontalPadding, vertical = BadgeVerticalPadding),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(
            LocalContentColor provides colors.onInfo,
            LocalTextStyle provides typography.labelSm.copy(color = colors.onInfo)
        ) {
            Text(text)
        }
    }
}

/**
 * Outlined Badge - Border only for subtle emphasis
 *
 * Use for subtle status indicators or when you need a lighter visual weight.
 *
 * @param text Badge text content
 * @param modifier Modifier for the badge
 */
@Composable
fun VetraBadgeOutlined(
    text: String,
    modifier: Modifier = Modifier
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val typography = VetraTheme.typography

    Box(
        modifier = modifier
            .clip(shapes.xs)
            .background(Color.Transparent)
            .then(
                // Border implementation
                Modifier
                    .padding(1.dp)
                    .background(colors.border, shapes.xs)
                    .padding(1.dp)
                    .clip(shapes.xs)
                    .background(Color.Transparent, shapes.xs)
            )
            .padding(
                horizontal = BadgeHorizontalPadding - 2.dp,
                vertical = BadgeVerticalPadding
            ),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(
            LocalContentColor provides colors.brand,
            LocalTextStyle provides typography.labelSm.copy(color = colors.brand)
        ) {
            Text(text)
        }
    }
}

/**
 * Badge Dot - Small dot indicator for notifications
 *
 * Use for notification indicators or status dots. Can display a count or be empty.
 *
 * @param modifier Modifier for the badge dot
 * @param count Optional count to display (null for empty dot)
 * @param color Optional color (defaults to danger color)
 */
@Composable
fun VetraBadgeDot(
    modifier: Modifier = Modifier,
    count: Int? = null,
    color: Color? = null
) {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography
    val badgeColor = color ?: colors.danger

    if (count != null && count > 0) {
        // Badge with count
        Box(
            modifier = modifier
                .clip(CircleShape)
                .background(badgeColor)
                .padding(horizontal = 4.dp, vertical = 2.dp),
            contentAlignment = Alignment.Center
        ) {
            CompositionLocalProvider(
                LocalContentColor provides colors.onDanger,
                LocalTextStyle provides typography.labelSm.copy(color = colors.onDanger)
            ) {
                Text(if (count > 99) "99+" else count.toString())
            }
        }
    } else {
        // Empty dot
        Box(
            modifier = modifier
                .size(BadgeDotSize)
                .clip(CircleShape)
                .background(badgeColor)
        )
    }
}

// ============================================================================
// Previews
// ============================================================================

@Preview
@Composable
private fun VetraBadgePreview() {
    VetraTheme {
        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Solid Badges
            Text(
                "Solid Badges",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                VetraBadge("New")
                VetraBadgeSecondary("Featured")
                VetraBadgeSuccess("Active")
                VetraBadgeWarning("Pending")
                VetraBadgeDanger("Error")
                VetraBadgeInfo("Info")
            }

            // Number Badges
            Text(
                "Count Badges",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                VetraBadge("1")
                VetraBadge("12")
                VetraBadge("99+")
                VetraBadgeSuccess("5")
                VetraBadgeDanger("3")
            }

            // Outlined Badges
            Text(
                "Outlined Badges",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                VetraBadgeOutlined("Beta")
                VetraBadgeOutlined("v2.0")
                VetraBadgeOutlined("Pro")
            }

            // Dot Badges
            Text(
                "Dot Badges",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                VetraBadgeDot()
                VetraBadgeDot(count = 5)
                VetraBadgeDot(count = 12)
                VetraBadgeDot(count = 99)
                VetraBadgeDot(count = 150)
                VetraBadgeDot(color = VetraTheme.colors.success)
            }

            // Usage Examples
            Text(
                "Usage Examples",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Notifications",
                        style = VetraTheme.typography.bodyMd.copy(color = VetraTheme.colors.textPrimary)
                    )
                    VetraBadgeDot(count = 3)
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Status: ",
                        style = VetraTheme.typography.bodyMd.copy(color = VetraTheme.colors.textPrimary)
                    )
                    VetraBadgeSuccess("Online")
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Version: ",
                        style = VetraTheme.typography.bodyMd.copy(color = VetraTheme.colors.textPrimary)
                    )
                    VetraBadgeOutlined("Beta")
                }
            }
        }
    }
}

@Preview
@Composable
private fun VetraBadgeDarkPreview() {
    VetraTheme(darkMode = true) {
        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                VetraBadge("New")
                VetraBadgeSecondary("Featured")
                VetraBadgeSuccess("Active")
                VetraBadgeWarning("Pending")
                VetraBadgeDanger("Error")
                VetraBadgeInfo("Info")
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                VetraBadgeOutlined("Beta")
                VetraBadgeDot()
                VetraBadgeDot(count = 5)
            }
        }
    }
}

