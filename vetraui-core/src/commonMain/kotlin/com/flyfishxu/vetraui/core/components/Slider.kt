package com.flyfishxu.vetraui.core.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.theme.VetraTheme
import com.flyfishxu.vetraui.core.theme.vetraShadow
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.abs
import kotlin.math.round

/**
 * Vetra Slider
 *
 * A modern slider component with smooth animations and elegant design.
 * Allows users to select a value from a continuous or discrete range.
 *
 * Design Features:
 * - Smooth dragging interaction
 * - Elegant thumb with soft shadow
 * - Clear track visualization
 * - Support for continuous and discrete values
 * - Accessible by default
 */

private val SliderHeight = 40.dp
private val TrackHeight = 4.dp
private val ThumbSize = 20.dp
private val ThumbHoverSize = 24.dp
private val ThumbAnimationDuration = 150

/**
 * Standard Slider - Select a value from a range
 *
 * @param value Current value of the slider
 * @param onValueChange Called when the value changes during drag
 * @param modifier Modifier for the slider
 * @param enabled Whether the slider is enabled
 * @param valueRange Range of values the slider can take
 * @param steps Number of discrete steps (0 for continuous)
 * @param onValueChangeFinished Called when the user stops dragging
 */
@Composable
fun VetraSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val shadows = VetraTheme.shadows
    val density = LocalDensity.current

    var isDragging by remember { mutableStateOf(false) }
    var sliderSize by remember { mutableStateOf(IntSize.Zero) }

    val thumbSize by animateDpAsState(
        targetValue = if (isDragging && enabled) ThumbHoverSize else ThumbSize,
        animationSpec = tween(durationMillis = ThumbAnimationDuration),
        label = "thumbSize"
    )

    val normalizedValue = ((value - valueRange.start) / (valueRange.endInclusive - valueRange.start))
        .coerceIn(0f, 1f)

    val trackColor = if (enabled) colors.borderSubtle else colors.canvasSubtle
    val activeTrackColor = if (enabled) colors.brand else colors.brandDisabled
    val thumbColor = if (enabled) colors.canvasElevated else colors.canvasSubtle
    val thumbShadow = if (enabled) shadows.md else shadows.none

    Box(
        modifier = modifier
            .height(SliderHeight)
            .semantics {
                contentDescription = "Slider value: $value"
            }
            .onGloballyPositioned { coordinates ->
                sliderSize = coordinates.size
            }
            .pointerInput(enabled, valueRange, steps) {
                if (!enabled) return@pointerInput

                detectDragGestures(
                    onDragStart = {
                        isDragging = true
                    },
                    onDragEnd = {
                        isDragging = false
                        onValueChangeFinished?.invoke()
                    },
                    onDragCancel = {
                        isDragging = false
                        onValueChangeFinished?.invoke()
                    },
                    onDrag = { change, _ ->
                        change.consume()
                        val sliderWidth = sliderSize.width
                        if (sliderWidth > 0) {
                            val positionX = change.position.x.coerceIn(0f, sliderWidth.toFloat())
                            val normalizedPos = positionX / sliderWidth

                            val newValue = if (steps > 0) {
                                val stepSize = 1f / (steps + 1)
                                val nearestStep = round(normalizedPos / stepSize).toInt()
                                (nearestStep * stepSize).coerceIn(0f, 1f)
                            } else {
                                normalizedPos
                            }

                            val actualValue = valueRange.start + newValue * (valueRange.endInclusive - valueRange.start)
                            onValueChange(actualValue)
                        }
                    }
                )
            },
        contentAlignment = Alignment.CenterStart
    ) {
        // Background Track
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(TrackHeight)
                .clip(shapes.full)
                .background(trackColor)
        )

        // Active Track
        Box(
            modifier = Modifier
                .fillMaxWidth(normalizedValue)
                .height(TrackHeight)
                .clip(shapes.full)
                .background(activeTrackColor)
        )

        // Step Indicators (if steps > 0)
        if (steps > 0 && enabled) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(steps + 2) { index ->
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .background(
                                color = if (index.toFloat() / (steps + 1) <= normalizedValue) {
                                    activeTrackColor
                                } else {
                                    trackColor
                                },
                                shape = CircleShape
                            )
                    )
                }
            }
        }

        // Thumb
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = thumbSize / 2),
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .absoluteOffset(
                        x = with(density) {
                            (sliderSize.width.toDp() - thumbSize) * normalizedValue
                        }
                    )
                    .size(thumbSize)
                    .vetraShadow(
                        elevation = thumbShadow,
                        shape = CircleShape
                    )
                    .background(
                        color = thumbColor,
                        shape = CircleShape
                    )
            )
        }
    }
}

/**
 * Slider with Label - Slider with value display
 *
 * @param value Current value of the slider
 * @param onValueChange Called when the value changes during drag
 * @param label Label text
 * @param modifier Modifier for the slider
 * @param enabled Whether the slider is enabled
 * @param valueRange Range of values the slider can take
 * @param steps Number of discrete steps (0 for continuous)
 * @param showValue Whether to show the current value
 * @param valueFormatter Custom formatter for the value display
 * @param onValueChangeFinished Called when the user stops dragging
 */
@Composable
fun VetraSliderWithLabel(
    value: Float,
    onValueChange: (Float) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    showValue: Boolean = true,
    valueFormatter: (Float) -> String = { "${(it * 10).toInt() / 10.0}" },
    onValueChangeFinished: (() -> Unit)? = null
) {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = typography.labelLg.copy(
                    color = if (enabled) colors.textPrimary else colors.textDisabled
                )
            )

            if (showValue) {
                Text(
                    text = valueFormatter(value),
                    style = typography.labelLg.copy(
                        color = if (enabled) colors.brand else colors.brandDisabled
                    )
                )
            }
        }

        VetraSlider(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            valueRange = valueRange,
            steps = steps,
            onValueChangeFinished = onValueChangeFinished
        )
    }
}

/**
 * Range Slider - Select a range with start and end values
 *
 * @param values Current start and end values
 * @param onValuesChange Called when the values change during drag
 * @param modifier Modifier for the slider
 * @param enabled Whether the slider is enabled
 * @param valueRange Range of values the slider can take
 * @param steps Number of discrete steps (0 for continuous)
 * @param onValuesChangeFinished Called when the user stops dragging
 */
@Composable
fun VetraRangeSlider(
    values: ClosedFloatingPointRange<Float>,
    onValuesChange: (ClosedFloatingPointRange<Float>) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    onValuesChangeFinished: (() -> Unit)? = null
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val shadows = VetraTheme.shadows
    val density = LocalDensity.current

    var isDraggingStart by remember { mutableStateOf(false) }
    var isDraggingEnd by remember { mutableStateOf(false) }
    var sliderSize by remember { mutableStateOf(IntSize.Zero) }

    val startThumbSize by animateDpAsState(
        targetValue = if (isDraggingStart && enabled) ThumbHoverSize else ThumbSize,
        animationSpec = tween(durationMillis = ThumbAnimationDuration),
        label = "startThumbSize"
    )

    val endThumbSize by animateDpAsState(
        targetValue = if (isDraggingEnd && enabled) ThumbHoverSize else ThumbSize,
        animationSpec = tween(durationMillis = ThumbAnimationDuration),
        label = "endThumbSize"
    )

    val normalizedStart = ((values.start - valueRange.start) / (valueRange.endInclusive - valueRange.start))
        .coerceIn(0f, 1f)
    val normalizedEnd = ((values.endInclusive - valueRange.start) / (valueRange.endInclusive - valueRange.start))
        .coerceIn(0f, 1f)

    val trackColor = if (enabled) colors.borderSubtle else colors.canvasSubtle
    val activeTrackColor = if (enabled) colors.brand else colors.brandDisabled
    val thumbColor = if (enabled) colors.canvasElevated else colors.canvasSubtle
    val thumbShadow = if (enabled) shadows.md else shadows.none

    fun handleDrag(positionX: Float, isStartThumb: Boolean) {
        val sliderWidth = sliderSize.width
        if (sliderWidth > 0) {
            val normalizedPos = (positionX / sliderWidth).coerceIn(0f, 1f)

            val newValue = if (steps > 0) {
                val stepSize = 1f / (steps + 1)
                val nearestStep = round(normalizedPos / stepSize).toInt()
                (nearestStep * stepSize).coerceIn(0f, 1f)
            } else {
                normalizedPos
            }

            val actualValue = valueRange.start + newValue * (valueRange.endInclusive - valueRange.start)

            val newRange = if (isStartThumb) {
                actualValue.coerceAtMost(values.endInclusive)..values.endInclusive
            } else {
                values.start..actualValue.coerceAtLeast(values.start)
            }

            onValuesChange(newRange)
        }
    }

    Box(
        modifier = modifier
            .height(SliderHeight)
            .semantics {
                contentDescription = "Range slider: ${values.start} to ${values.endInclusive}"
            }
            .onGloballyPositioned { coordinates ->
                sliderSize = coordinates.size
            },
        contentAlignment = Alignment.CenterStart
    ) {
        // Background Track
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(TrackHeight)
                .clip(shapes.full)
                .background(trackColor)
        )

        // Active Track
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = with(density) { (sliderSize.width.toDp()) * normalizedStart },
                    end = with(density) { (sliderSize.width.toDp()) * (1f - normalizedEnd) }
                )
                .height(TrackHeight)
                .clip(shapes.full)
                .background(activeTrackColor)
        )

        // Start Thumb
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = startThumbSize / 2),
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .absoluteOffset(
                        x = with(density) {
                            (sliderSize.width.toDp() - startThumbSize) * normalizedStart
                        }
                    )
                    .size(startThumbSize)
                    .vetraShadow(
                        elevation = thumbShadow,
                        shape = CircleShape
                    )
                    .background(
                        color = thumbColor,
                        shape = CircleShape
                    )
                    .pointerInput(enabled, valueRange, steps) {
                        if (!enabled) return@pointerInput

                        detectDragGestures(
                            onDragStart = {
                                isDraggingStart = true
                            },
                            onDragEnd = {
                                isDraggingStart = false
                                onValuesChangeFinished?.invoke()
                            },
                            onDragCancel = {
                                isDraggingStart = false
                                onValuesChangeFinished?.invoke()
                            },
                            onDrag = { change, _ ->
                                change.consume()
                                handleDrag(change.position.x + (sliderSize.width * normalizedStart), true)
                            }
                        )
                    }
            )
        }

        // End Thumb
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = endThumbSize / 2),
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .absoluteOffset(
                        x = with(density) {
                            (sliderSize.width.toDp() - endThumbSize) * normalizedEnd
                        }
                    )
                    .size(endThumbSize)
                    .vetraShadow(
                        elevation = thumbShadow,
                        shape = CircleShape
                    )
                    .background(
                        color = thumbColor,
                        shape = CircleShape
                    )
                    .pointerInput(enabled, valueRange, steps) {
                        if (!enabled) return@pointerInput

                        detectDragGestures(
                            onDragStart = {
                                isDraggingEnd = true
                            },
                            onDragEnd = {
                                isDraggingEnd = false
                                onValuesChangeFinished?.invoke()
                            },
                            onDragCancel = {
                                isDraggingEnd = false
                                onValuesChangeFinished?.invoke()
                            },
                            onDrag = { change, _ ->
                                change.consume()
                                handleDrag(change.position.x + (sliderSize.width * normalizedEnd), false)
                            }
                        )
                    }
            )
        }
    }
}

// ============================================================================
// Previews
// ============================================================================

@Preview
@Composable
private fun VetraSliderPreview() {
    VetraTheme {
        var value1 by remember { mutableStateOf(0.5f) }
        var value2 by remember { mutableStateOf(25f) }
        var value3 by remember { mutableStateOf(3f) }
        var rangeValues by remember { mutableStateOf(0.2f..0.7f) }

        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                "Slider Variants",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            // Standard Slider
            Text(
                "Continuous Slider",
                style = VetraTheme.typography.labelLg.copy(color = VetraTheme.colors.textSecondary)
            )
            VetraSlider(
                value = value1,
                onValueChange = { value1 = it },
                modifier = Modifier.fillMaxWidth()
            )

            // Slider with Label
            VetraSliderWithLabel(
                value = value2,
                onValueChange = { value2 = it },
                label = "Volume",
                valueRange = 0f..100f,
                valueFormatter = { "${it.toInt()}%" }
            )

            // Discrete Slider
            Text(
                "Discrete Slider (5 steps)",
                style = VetraTheme.typography.labelLg.copy(color = VetraTheme.colors.textSecondary)
            )
            VetraSlider(
                value = value3,
                onValueChange = { value3 = it },
                valueRange = 0f..5f,
                steps = 3,
                modifier = Modifier.fillMaxWidth()
            )

            // Disabled Slider
            Text(
                "Disabled State",
                style = VetraTheme.typography.labelLg.copy(color = VetraTheme.colors.textSecondary)
            )
            VetraSlider(
                value = 0.7f,
                onValueChange = {},
                enabled = false,
                modifier = Modifier.fillMaxWidth()
            )

            // Range Slider
            Text(
                "Range Slider",
                style = VetraTheme.typography.labelLg.copy(color = VetraTheme.colors.textSecondary)
            )
            VetraRangeSlider(
                values = rangeValues,
                onValuesChange = { rangeValues = it },
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                "Range: ${((rangeValues.start * 100).toInt() / 100.0)} - ${((rangeValues.endInclusive * 100).toInt() / 100.0)}",
                style = VetraTheme.typography.bodySm.copy(color = VetraTheme.colors.textSecondary)
            )
        }
    }
}

@Preview
@Composable
private fun VetraSliderDarkPreview() {
    VetraTheme(darkMode = true) {
        var value by remember { mutableStateOf(0.6f) }

        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            VetraSliderWithLabel(
                value = value,
                onValueChange = { value = it },
                label = "Brightness",
                valueFormatter = { "${(it * 100).toInt()}%" }
            )

            VetraSlider(
                value = value,
                onValueChange = { value = it },
                steps = 4,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

