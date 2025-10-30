package com.flyfishxu.vetraui.core

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.theme.VetraTheme
import com.flyfishxu.vetraui.core.theme.vetraShadow
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

/**
 * Vetra Pull to Refresh
 *
 * A modern, elegant pull-to-refresh component with smooth animations and gesture handling.
 * Built from scratch without Material components, following Vetra design principles.
 *
 * Features:
 * - Smooth gesture recognition and animations
 * - Elegant loading indicator with rotation
 * - Customizable trigger threshold
 * - Support for custom indicators
 * - State-based architecture for predictable behavior
 * - Cross-platform compatibility
 *
 * @param refreshing Whether refresh is currently in progress
 * @param onRefresh Callback when user triggers refresh
 * @param modifier Modifier for the container
 * @param enabled Whether pull-to-refresh is enabled
 * @param indicatorColor Color of the refresh indicator
 * @param indicator Custom refresh indicator composable
 * @param content The scrollable content
 */
@OptIn(ExperimentalVetraApi::class)
@Composable
fun VetraPullToRefresh(
    refreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    indicatorColor: Color? = null,
    indicator: @Composable (VetraPullToRefreshState) -> Unit = { state ->
        VetraDefaultRefreshIndicator(state = state, color = indicatorColor)
    },
    content: @Composable () -> Unit
) {
    val state = rememberVetraPullToRefreshState(
        refreshing = refreshing,
        onRefresh = onRefresh,
        enabled = enabled
    )

    Box(
        modifier = modifier
            .nestedScroll(state.nestedScrollConnection)
    ) {
        // Content
        Box(modifier = Modifier.fillMaxSize()) {
            content()
        }

        // Refresh indicator
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .offset { IntOffset(0, state.indicatorOffsetY.roundToInt()) }
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 16.dp)
            ) {
                indicator(state)
            }
        }
    }
}

/**
 * State holder for Vetra Pull to Refresh component
 *
 * Manages the pull gesture, animations, and refresh state using MVI-like architecture.
 *
 * @param refreshing Whether refresh is currently in progress
 * @param onRefresh Callback when user triggers refresh
 * @param enabled Whether pull-to-refresh is enabled
 * @param threshold Distance to pull before triggering refresh (in pixels)
 */
@ExperimentalVetraApi
@Stable
class VetraPullToRefreshState(
    refreshing: Boolean,
    private val onRefresh: () -> Unit,
    enabled: Boolean,
    private val threshold: Float = 120f
) {
    // Current pull distance
    internal var pullOffset by mutableFloatStateOf(0f)
        private set

    // Whether refresh is in progress
    var isRefreshing by mutableStateOf(refreshing)
        internal set

    // Whether pull-to-refresh is enabled
    var isEnabled by mutableStateOf(enabled)

    // Progress of the pull (0f to 1f, can exceed 1f)
    val pullProgress: Float by derivedStateOf {
        (pullOffset / threshold).coerceIn(0f, 1.5f)
    }

    // Whether the pull has exceeded the threshold
    val isOverThreshold: Boolean by derivedStateOf {
        pullOffset >= threshold
    }

    // Indicator offset for positioning
    val indicatorOffsetY: Float by derivedStateOf {
        if (isRefreshing) {
            threshold * 0.5f
        } else {
            (pullOffset * 0.8f).coerceAtMost(threshold * 0.5f)
        }
    }

    // Scale for the indicator based on pull progress
    val indicatorScale: Float by derivedStateOf {
        pullProgress.coerceIn(0f, 1f)
    }

    // Rotation for the indicator based on pull progress
    val indicatorRotation: Float by derivedStateOf {
        if (isRefreshing) 0f else pullProgress * 180f
    }

    internal val nestedScrollConnection = object : NestedScrollConnection {
        override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
            if (!isEnabled || isRefreshing) return Offset.Zero

            // Only consume scroll when we're already pulling (pullOffset > 0)
            // and user is scrolling up (available.y < 0)
            return if (available.y < 0 && pullOffset > 0) {
                val consumed = if (pullOffset + available.y >= 0) {
                    available.y
                } else {
                    -pullOffset
                }
                pullOffset += consumed
                Offset(0f, consumed)
            } else {
                Offset.Zero
            }
        }

        override fun onPostScroll(
            consumed: Offset,
            available: Offset,
            source: NestedScrollSource
        ): Offset {
            if (!isEnabled || isRefreshing) return Offset.Zero

            // Only handle pull down when:
            // 1. User is dragging (not flinging)
            // 2. There's available scroll downward (available.y > 0)
            // 3. Content can't scroll up anymore (at the top)
            if (source == NestedScrollSource.UserInput && available.y > 0) {
                val dragMultiplier = when {
                    pullOffset < threshold -> 0.5f
                    else -> 0.2f // Resistance after threshold
                }
                val newOffset = available.y * dragMultiplier
                pullOffset += newOffset
                return Offset(0f, available.y)
            }
            
            return Offset.Zero
        }

        override suspend fun onPreFling(available: Velocity): Velocity {
            return if (pullOffset > 0) {
                // Trigger refresh if threshold exceeded
                if (pullOffset >= threshold && !isRefreshing) {
                    isRefreshing = true
                    onRefresh()
                }

                // Reset pull offset with animation
                animatePullOffsetTo(0f)

                // Consume the velocity to prevent fling
                available
            } else {
                Velocity.Zero
            }
        }

        override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
            // Reset pull offset if there's any remaining
            if (pullOffset > 0 && !isRefreshing) {
                animatePullOffsetTo(0f)
            }
            return Velocity.Zero
        }
    }

    private suspend fun animatePullOffsetTo(target: Float) {
        // Simple linear animation for pull offset
        val start = pullOffset
        val duration = 200
        val startTime = System.currentTimeMillis()

        while (pullOffset != target) {
            val elapsed = System.currentTimeMillis() - startTime
            val progress = (elapsed.toFloat() / duration).coerceIn(0f, 1f)

            pullOffset = start + (target - start) * progress

            if (progress >= 1f) {
                pullOffset = target
                break
            }

            kotlinx.coroutines.delay(16)
        }
    }

    /**
     * Call this when refresh is complete to reset the state
     */
    fun onRefreshComplete() {
        isRefreshing = false
    }
}

/**
 * Remember and create a [VetraPullToRefreshState]
 */
@ExperimentalVetraApi
@Composable
fun rememberVetraPullToRefreshState(
    refreshing: Boolean,
    onRefresh: () -> Unit,
    enabled: Boolean = true,
    threshold: Float = 120f
): VetraPullToRefreshState {
    val state = remember {
        VetraPullToRefreshState(
            refreshing = refreshing,
            onRefresh = onRefresh,
            enabled = enabled,
            threshold = threshold
        )
    }

    // Update state when props change
    state.isRefreshing = refreshing
    state.isEnabled = enabled

    return state
}

/**
 * Default refresh indicator for Vetra Pull to Refresh
 *
 * An elegant circular indicator with a rotating arc that grows as the user pulls.
 *
 * @param state The pull-to-refresh state
 * @param color Custom color for the indicator
 */
@ExperimentalVetraApi
@Composable
fun VetraDefaultRefreshIndicator(
    state: VetraPullToRefreshState,
    color: Color? = null,
    modifier: Modifier = Modifier
) {
    val colors = VetraTheme.colors
    val shadows = VetraTheme.shadows
    val indicatorColor = color ?: colors.brand

    // Animate scale when refreshing
    val scale by animateFloatAsState(
        targetValue = if (state.isRefreshing || state.indicatorScale > 0) 1f else 0f,
        animationSpec = tween(200, easing = LinearEasing),
        label = "indicator_scale"
    )

    Box(
        modifier = modifier
            .size(48.dp)
            .scale(scale * state.indicatorScale)
            .vetraShadow(elevation = shadows.md, shape = CircleShape)
            .clip(CircleShape)
            .background(colors.canvasElevated),
        contentAlignment = Alignment.Center
    ) {
        if (state.isRefreshing) {
            // Show spinning loading indicator when refreshing
            VetraLoadingSpinner(
                size = LoadingSize.MEDIUM,
                color = indicatorColor
            )
        } else {
            // Show rotating arc based on pull progress
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .rotate(state.indicatorRotation)
            ) {
                VetraLoadingSpinner(
                    size = LoadingSize.MEDIUM,
                    color = indicatorColor.copy(alpha = state.pullProgress.coerceIn(0f, 1f))
                )
            }
        }
    }
}

/**
 * Compact refresh indicator that appears inline
 *
 * A more subtle indicator that can be used in tight spaces.
 *
 * @param state The pull-to-refresh state
 * @param color Custom color for the indicator
 */
@ExperimentalVetraApi
@Composable
fun VetraCompactRefreshIndicator(
    state: VetraPullToRefreshState,
    color: Color? = null,
    modifier: Modifier = Modifier
) {
    val colors = VetraTheme.colors
    val indicatorColor = color ?: colors.brand

    val scale by animateFloatAsState(
        targetValue = if (state.isRefreshing || state.indicatorScale > 0) 1f else 0f,
        animationSpec = tween(200, easing = LinearEasing),
        label = "compact_indicator_scale"
    )

    Box(
        modifier = modifier
            .size(32.dp)
            .scale(scale * state.indicatorScale),
        contentAlignment = Alignment.Center
    ) {
        if (state.isRefreshing) {
            VetraLoadingSpinner(
                size = LoadingSize.SMALL,
                color = indicatorColor
            )
        } else {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .rotate(state.indicatorRotation)
            ) {
                VetraLoadingSpinner(
                    size = LoadingSize.SMALL,
                    color = indicatorColor.copy(alpha = state.pullProgress.coerceIn(0f, 1f))
                )
            }
        }
    }
}
