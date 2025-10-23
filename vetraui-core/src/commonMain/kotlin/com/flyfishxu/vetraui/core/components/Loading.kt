package com.flyfishxu.vetraui.core.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.theme.VetraTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Vetra Loading System
 *
 * A modern, elegant loading indicator system with smooth animations.
 * Features circular spinners, linear progress bars, and skeleton loaders.
 *
 * Design Features:
 * - Smooth, continuous animations
 * - Multiple size variants
 * - Customizable colors
 * - Minimal CPU usage
 * - Elegant motion design
 */

/**
 * Loading size variants
 */
enum class LoadingSize {
    SMALL,      // 24dp - for inline loading
    MEDIUM,     // 40dp - default size
    LARGE,      // 56dp - for prominent loading
    XLARGE      // 72dp - for full-screen loading
}

/**
 * Circular Loading Spinner
 *
 * An elegant circular spinner with smooth rotation animation.
 * Perfect for indicating loading states.
 *
 * @param modifier Modifier for the loading indicator
 * @param size Size variant
 * @param color Color of the spinner (defaults to brand color)
 * @param strokeWidth Width of the spinner stroke
 */
@Composable
fun VetraLoadingSpinner(
    modifier: Modifier = Modifier,
    size: LoadingSize = LoadingSize.MEDIUM,
    color: Color = VetraTheme.colors.brand,
    strokeWidth: Dp? = null
) {
    val indicatorSize = when (size) {
        LoadingSize.SMALL -> 24.dp
        LoadingSize.MEDIUM -> 40.dp
        LoadingSize.LARGE -> 56.dp
        LoadingSize.XLARGE -> 72.dp
    }

    val defaultStrokeWidth = when (size) {
        LoadingSize.SMALL -> 2.dp
        LoadingSize.MEDIUM -> 3.dp
        LoadingSize.LARGE -> 4.dp
        LoadingSize.XLARGE -> 5.dp
    }

    val finalStrokeWidth = strokeWidth ?: defaultStrokeWidth

    // Rotation animation
    val infiniteTransition = rememberInfiniteTransition(label = "spinner")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    // Arc sweep animation
    val sweep by infiniteTransition.animateFloat(
        initialValue = 30f,
        targetValue = 300f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "sweep"
    )

    Canvas(
        modifier = modifier.size(indicatorSize)
    ) {
        val sweepAngle = sweep
        val startAngle = rotation

        drawArc(
            color = color,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = Stroke(
                width = finalStrokeWidth.toPx(),
                cap = StrokeCap.Round
            )
            // size parameter removed - uses canvas size by default
        )
    }
}

/**
 * Dots Loading Indicator
 *
 * Three animated dots that bounce in sequence.
 * Modern and playful loading indicator.
 *
 * @param modifier Modifier for the loading indicator
 * @param color Color of the dots
 * @param dotSize Size of each dot
 * @param spacing Spacing between dots
 */
@Composable
fun VetraLoadingDots(
    modifier: Modifier = Modifier,
    color: Color = VetraTheme.colors.brand,
    dotSize: Dp = 8.dp,
    spacing: Dp = 8.dp
) {
    val infiniteTransition = rememberInfiniteTransition(label = "dots")

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(3) { index ->
            val scale by infiniteTransition.animateFloat(
                initialValue = 0.8f,
                targetValue = 1.2f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 600,
                        delayMillis = index * 150,
                        easing = FastOutSlowInEasing
                    ),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "dot_scale_$index"
            )

            Box(
                modifier = Modifier
                    .size(dotSize * 1.2f) // Fixed size for layout
                    .graphicsLayer {
                        scaleX = scale / 1.2f
                        scaleY = scale / 1.2f
                    }
                    .background(color, CircleShape)
            )
        }
    }
}

/**
 * Pulse Loading Indicator
 *
 * Concentric circles that pulse outward.
 * Elegant and subtle loading indicator.
 *
 * @param modifier Modifier for the loading indicator
 * @param size Size of the pulse
 * @param color Color of the pulse
 */
@Composable
fun VetraLoadingPulse(
    modifier: Modifier = Modifier,
    size: LoadingSize = LoadingSize.MEDIUM,
    color: Color = VetraTheme.colors.brand
) {
    val pulseSize = when (size) {
        LoadingSize.SMALL -> 24.dp
        LoadingSize.MEDIUM -> 40.dp
        LoadingSize.LARGE -> 56.dp
        LoadingSize.XLARGE -> 72.dp
    }

    val infiniteTransition = rememberInfiniteTransition(label = "pulse")

    Box(
        modifier = modifier.size(pulseSize),
        contentAlignment = Alignment.Center
    ) {
        repeat(2) { index ->
            val scale by infiniteTransition.animateFloat(
                initialValue = 0.3f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 1500,
                        delayMillis = index * 750,
                        easing = FastOutSlowInEasing
                    ),
                    repeatMode = RepeatMode.Restart
                ),
                label = "pulse_scale_$index"
            )

            val alpha by infiniteTransition.animateFloat(
                initialValue = 0.8f,
                targetValue = 0f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 1500,
                        delayMillis = index * 750,
                        easing = FastOutSlowInEasing
                    ),
                    repeatMode = RepeatMode.Restart
                ),
                label = "pulse_alpha_$index"
            )

            Box(
                modifier = Modifier
                    .size(pulseSize) // Fixed size for layout
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        this.alpha = alpha
                    }
                    .background(color, CircleShape)
            )
        }

        // Center dot
        Box(
            modifier = Modifier
                .size(pulseSize * 0.3f)
                .background(color, CircleShape)
        )
    }
}

/**
 * Linear Progress Bar
 *
 * A sleek progress bar for determinate or indeterminate loading.
 *
 * @param modifier Modifier for the progress bar
 * @param progress Progress value (0f to 1f), null for indeterminate
 * @param color Color of the progress
 * @param trackColor Color of the track
 * @param height Height of the progress bar
 */
@Composable
fun VetraLinearProgress(
    modifier: Modifier = Modifier,
    progress: Float? = null,
    color: Color = VetraTheme.colors.brand,
    trackColor: Color = VetraTheme.colors.borderSubtle,
    height: Dp = 4.dp
) {
    val shapes = VetraTheme.shapes

    Box(
        modifier = modifier
            .height(height)
            .clip(shapes.full)
            .background(trackColor)
    ) {
        if (progress != null) {
            // Determinate progress
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(progress.coerceIn(0f, 1f))
                    .background(color)
            )
        } else {
            // Indeterminate progress
            val infiniteTransition = rememberInfiniteTransition(label = "linear_progress")

            val offset by infiniteTransition.animateFloat(
                initialValue = -0.3f,
                targetValue = 1.3f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1500, easing = FastOutSlowInEasing),
                    repeatMode = RepeatMode.Restart
                ),
                label = "progress_offset"
            )

            Canvas(modifier = Modifier.fillMaxSize()) {
                val barWidth = size.width * 0.3f
                val startX = (size.width * offset).coerceIn(-barWidth, size.width)

                drawRect(
                    brush = Brush.linearGradient(
                        0f to color.copy(alpha = 0f),
                        0.5f to color,
                        1f to color.copy(alpha = 0f),
                        start = Offset(startX, 0f),
                        end = Offset(startX + barWidth, 0f)
                    ),
                    size = Size(size.width, size.height)
                )
            }
        }
    }
}

/**
 * Full Screen Loading
 *
 * A centered loading indicator with optional message.
 * Perfect for full-screen loading states.
 *
 * @param modifier Modifier for the container
 * @param message Optional loading message
 * @param loadingType Type of loading indicator to show
 */
@Composable
fun VetraFullScreenLoading(
    modifier: Modifier = Modifier,
    message: String? = null,
    loadingType: LoadingType = LoadingType.SPINNER
) {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colors.canvas),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when (loadingType) {
                LoadingType.SPINNER -> VetraLoadingSpinner(size = LoadingSize.LARGE)
                LoadingType.DOTS -> VetraLoadingDots()
                LoadingType.PULSE -> VetraLoadingPulse(size = LoadingSize.LARGE)
            }

            if (message != null) {
                Text(
                    text = message,
                    style = typography.bodyMd.copy(color = colors.textSecondary)
                )
            }
        }
    }
}

enum class LoadingType {
    SPINNER,
    DOTS,
    PULSE
}

// ============================================================================
// Previews
// ============================================================================

@Preview
@Composable
private fun VetraLoadingSpinnerPreview() {
    VetraTheme {
        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Loading Spinners",
                style = VetraTheme.typography.headingMd.copy(color = VetraTheme.colors.textPrimary)
            )

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                VetraLoadingSpinner(size = LoadingSize.SMALL)
                VetraLoadingSpinner(size = LoadingSize.MEDIUM)
                VetraLoadingSpinner(size = LoadingSize.LARGE)
                VetraLoadingSpinner(size = LoadingSize.XLARGE)
            }

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                VetraLoadingSpinner(color = VetraTheme.colors.accent)
                VetraLoadingSpinner(color = VetraTheme.colors.success)
                VetraLoadingSpinner(color = VetraTheme.colors.warning)
            }
        }
    }
}

@Preview
@Composable
private fun VetraLoadingDotsPreview() {
    VetraTheme {
        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Loading Dots",
                style = VetraTheme.typography.headingMd.copy(color = VetraTheme.colors.textPrimary)
            )

            VetraLoadingDots()
            VetraLoadingDots(color = VetraTheme.colors.accent)
            VetraLoadingDots(dotSize = 12.dp, spacing = 12.dp)
        }
    }
}

@Preview
@Composable
private fun VetraLoadingPulsePreview() {
    VetraTheme {
        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Loading Pulse",
                style = VetraTheme.typography.headingMd.copy(color = VetraTheme.colors.textPrimary)
            )

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                VetraLoadingPulse(size = LoadingSize.SMALL)
                VetraLoadingPulse(size = LoadingSize.MEDIUM)
                VetraLoadingPulse(size = LoadingSize.LARGE)
            }
        }
    }
}

@Preview
@Composable
private fun VetraLinearProgressPreview() {
    VetraTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                "Linear Progress",
                style = VetraTheme.typography.headingMd.copy(color = VetraTheme.colors.textPrimary)
            )

            Text(
                "Indeterminate",
                style = VetraTheme.typography.bodyMd.copy(color = VetraTheme.colors.textSecondary)
            )
            VetraLinearProgress(modifier = Modifier.fillMaxWidth())

            Text(
                "Determinate",
                style = VetraTheme.typography.bodyMd.copy(color = VetraTheme.colors.textSecondary)
            )
            VetraLinearProgress(
                modifier = Modifier.fillMaxWidth(),
                progress = 0.3f
            )
            VetraLinearProgress(
                modifier = Modifier.fillMaxWidth(),
                progress = 0.7f
            )

            Text(
                "Custom Colors",
                style = VetraTheme.typography.bodyMd.copy(color = VetraTheme.colors.textSecondary)
            )
            VetraLinearProgress(
                modifier = Modifier.fillMaxWidth(),
                color = VetraTheme.colors.success,
                progress = 0.5f
            )
        }
    }
}

