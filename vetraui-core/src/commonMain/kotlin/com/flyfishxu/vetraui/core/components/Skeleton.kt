package com.flyfishxu.vetraui.core.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.theme.VetraTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Vetra Skeleton System
 *
 * Skeleton loaders (shimmer effect) for content placeholders.
 * Provides smooth, elegant loading states while content is being fetched.
 *
 * Design Features:
 * - Smooth shimmer animation
 * - Multiple shape variants
 * - Customizable colors
 * - Composable skeleton layouts
 */

/**
 * Basic Skeleton Box
 *
 * A skeleton placeholder with shimmer animation.
 *
 * @param modifier Modifier for the skeleton
 * @param shape Shape of the skeleton
 * @param baseColor Base color of the skeleton
 * @param shimmerColor Color of the shimmer effect
 */
@Composable
fun VetraSkeleton(
    modifier: Modifier = Modifier,
    shape: Shape = VetraTheme.shapes.md,
    baseColor: Color = VetraTheme.colors.borderSubtle,
    shimmerColor: Color = VetraTheme.colors.canvasElevated
) {
    val infiniteTransition = rememberInfiniteTransition(label = "skeleton")

    val shimmerTranslate by infiniteTransition.animateFloat(
        initialValue = -1f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer"
    )

    Box(
        modifier = modifier
            .clip(shape)
            .background(
                brush = Brush.linearGradient(
                    0f to baseColor,
                    0.3f to shimmerColor,
                    0.5f to shimmerColor.copy(alpha = 0.5f),
                    0.7f to shimmerColor,
                    1f to baseColor,
                    start = Offset(shimmerTranslate * 1000f, shimmerTranslate * 1000f),
                    end = Offset(shimmerTranslate * 1000f + 500f, shimmerTranslate * 1000f + 500f)
                )
            )
    )
}

/**
 * Skeleton Text Line
 *
 * A skeleton placeholder for text lines.
 *
 * @param modifier Modifier for the skeleton
 * @param width Width of the line (null for fillMaxWidth)
 * @param height Height of the line
 */
@Composable
fun VetraSkeletonText(
    modifier: Modifier = Modifier,
    width: Dp? = null,
    height: Dp = 16.dp
) {
    VetraSkeleton(
        modifier = modifier
            .then(if (width != null) Modifier.width(width) else Modifier.fillMaxWidth())
            .height(height),
        shape = VetraTheme.shapes.sm
    )
}

/**
 * Skeleton Circle
 *
 * A circular skeleton placeholder, perfect for avatars.
 *
 * @param modifier Modifier for the skeleton
 * @param size Size of the circle
 */
@Composable
fun VetraSkeletonCircle(
    modifier: Modifier = Modifier,
    size: Dp = 40.dp
) {
    VetraSkeleton(
        modifier = modifier.size(size),
        shape = CircleShape
    )
}

/**
 * Skeleton Card
 *
 * A pre-composed skeleton for card-like content.
 *
 * @param modifier Modifier for the skeleton
 * @param showImage Whether to show an image placeholder
 * @param imageHeight Height of the image placeholder
 * @param linesCount Number of text lines
 */
@Composable
fun VetraSkeletonCard(
    modifier: Modifier = Modifier,
    showImage: Boolean = true,
    imageHeight: Dp = 200.dp,
    linesCount: Int = 3
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (showImage) {
            VetraSkeleton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight)
            )
        }

        repeat(linesCount) { index ->
            val width = when (index) {
                linesCount - 1 -> 0.6f  // Last line shorter
                else -> if (index % 2 == 0) 1f else 0.9f
            }

            VetraSkeletonText(
                modifier = Modifier.fillMaxWidth(width)
            )
        }
    }
}

/**
 * Skeleton List Item
 *
 * A pre-composed skeleton for list item content.
 *
 * @param modifier Modifier for the skeleton
 * @param showAvatar Whether to show an avatar placeholder
 * @param avatarSize Size of the avatar
 * @param linesCount Number of text lines
 */
@Composable
fun VetraSkeletonListItem(
    modifier: Modifier = Modifier,
    showAvatar: Boolean = true,
    avatarSize: Dp = 48.dp,
    linesCount: Int = 2
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (showAvatar) {
            VetraSkeletonCircle(size = avatarSize)
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(linesCount) { index ->
                val width = when (index) {
                    0 -> 0.7f  // Title
                    else -> if (index == linesCount - 1) 0.4f else 0.9f
                }

                VetraSkeletonText(
                    modifier = Modifier.fillMaxWidth(width),
                    height = if (index == 0) 18.dp else 14.dp
                )
            }
        }
    }
}

/**
 * Skeleton Button
 *
 * A skeleton placeholder for button.
 *
 * @param modifier Modifier for the skeleton
 * @param width Width of the button
 * @param height Height of the button
 */
@Composable
fun VetraSkeletonButton(
    modifier: Modifier = Modifier,
    width: Dp = 120.dp,
    height: Dp = 44.dp
) {
    VetraSkeleton(
        modifier = modifier
            .width(width)
            .height(height),
        shape = VetraTheme.shapes.sm
    )
}

// ============================================================================
// Previews
// ============================================================================

@Preview
@Composable
private fun VetraSkeletonPreview() {
    VetraTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                "Skeleton Components",
                style = VetraTheme.typography.headingLg.copy(color = VetraTheme.colors.textPrimary)
            )

            // Basic skeleton
            VetraSkeleton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )

            // Text lines
            Text(
                "Text Skeleton",
                style = VetraTheme.typography.headingMd.copy(color = VetraTheme.colors.textPrimary)
            )
            VetraSkeletonText()
            VetraSkeletonText(width = 200.dp)
            VetraSkeletonText(width = 150.dp)

            // Circle
            Text(
                "Circle Skeleton",
                style = VetraTheme.typography.headingMd.copy(color = VetraTheme.colors.textPrimary)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                VetraSkeletonCircle(size = 40.dp)
                VetraSkeletonCircle(size = 56.dp)
                VetraSkeletonCircle(size = 72.dp)
            }
        }
    }
}

@Preview
@Composable
private fun VetraSkeletonCardPreview() {
    VetraTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                "Skeleton Card",
                style = VetraTheme.typography.headingLg.copy(color = VetraTheme.colors.textPrimary)
            )

            VetraCard(modifier = Modifier.fillMaxWidth()) {
                VetraSkeletonCard(
                    modifier = Modifier.fillMaxWidth(),
                    showImage = true,
                    imageHeight = 180.dp,
                    linesCount = 4
                )
            }
        }
    }
}

@Preview
@Composable
private fun VetraSkeletonListPreview() {
    VetraTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Skeleton List Items",
                style = VetraTheme.typography.headingLg.copy(color = VetraTheme.colors.textPrimary)
            )

            repeat(3) {
                VetraCard(modifier = Modifier.fillMaxWidth()) {
                    VetraSkeletonListItem(
                        modifier = Modifier.fillMaxWidth(),
                        showAvatar = true,
                        linesCount = 2
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun VetraSkeletonDarkPreview() {
    VetraTheme(darkMode = true) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                "Dark Mode Skeleton",
                style = VetraTheme.typography.headingLg.copy(color = VetraTheme.colors.textPrimary)
            )

            VetraCard(modifier = Modifier.fillMaxWidth()) {
                VetraSkeletonListItem(
                    modifier = Modifier.fillMaxWidth()
                )
            }

            VetraCard(modifier = Modifier.fillMaxWidth()) {
                VetraSkeletonCard(
                    modifier = Modifier.fillMaxWidth(),
                    imageHeight = 150.dp
                )
            }
        }
    }
}

