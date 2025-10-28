package com.flyfishxu.vetraui.core

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.theme.VetraTheme
import com.flyfishxu.vetraui.core.theme.vetraShadow
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Vetra Checkbox
 *
 * A modern checkbox component with smooth animations and elegant design.
 * Perfect for multi-choice selections and toggleable options.
 *
 * Design Features:
 * - Smooth checkmark animation with scale effect
 * - Color transitions for checked/unchecked states
 * - Indeterminate state support
 * - Clear visual feedback
 * - Accessible by default
 */

private val CheckboxSize = 20.dp
private val CheckboxBorderWidth = 2.dp
private val CheckboxTouchTarget = 44.dp
private val CheckboxAnimationDuration = 250
private val CheckboxCornerRadius = 4.dp

/**
 * Standard Checkbox - Multi-choice selector
 *
 * @param checked Whether the checkbox is checked
 * @param onCheckedChange Called when the user toggles the checkbox
 * @param modifier Modifier for the checkbox
 * @param enabled Whether the checkbox is enabled
 * @param indeterminate Whether the checkbox is in an indeterminate state
 */
@Composable
fun VetraCheckbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    indeterminate: Boolean = false
) {
    val colors = VetraTheme.colors
    val shadows = VetraTheme.shadows
    val interactionSource = remember { MutableInteractionSource() }

    val backgroundColor by animateColorAsState(
        targetValue = when {
            (checked || indeterminate) && enabled -> colors.brand
            (checked || indeterminate) && !enabled -> colors.canvasSubtle
            else -> colors.canvas
        },
        animationSpec = tween(durationMillis = CheckboxAnimationDuration),
        label = "checkboxBackgroundColor"
    )

    val borderColor by animateColorAsState(
        targetValue = when {
            (checked || indeterminate) && enabled -> colors.brand
            !checked && enabled -> colors.border
            (checked || indeterminate) && !enabled -> colors.borderSubtle
            else -> colors.borderSubtle
        },
        animationSpec = tween(durationMillis = CheckboxAnimationDuration),
        label = "checkboxBorderColor"
    )

    val checkmarkColor by animateColorAsState(
        targetValue = when {
            (checked || indeterminate) && enabled -> colors.onBrand
            (checked || indeterminate) && !enabled -> colors.textDisabled
            else -> Color.Transparent
        },
        animationSpec = tween(durationMillis = CheckboxAnimationDuration),
        label = "checkboxCheckmarkColor"
    )

    val checkmarkProgress by animateFloatAsState(
        targetValue = if (checked || indeterminate) 1f else 0f,
        animationSpec = tween(durationMillis = CheckboxAnimationDuration),
        label = "checkboxCheckmarkProgress"
    )

    val checkboxShape = VetraTheme.shapes.xs

    Box(
        modifier = modifier
            .size(CheckboxTouchTarget)
            .clip(checkboxShape)
            .clickable(
                onClick = { onCheckedChange?.invoke(!checked) },
                enabled = enabled && onCheckedChange != null,
                role = Role.Checkbox,
                interactionSource = interactionSource,
                indication = null
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(CheckboxSize)
                .vetraShadow(
                    elevation = if ((checked || indeterminate) && enabled) shadows.xs else shadows.none,
                    shape = checkboxShape
                )
                .border(
                    width = if (checked || indeterminate) 0.dp else CheckboxBorderWidth,
                    color = borderColor,
                    shape = checkboxShape
                )
                .background(
                    color = backgroundColor,
                    shape = checkboxShape
                )
                .drawBehind {
                    if (checkmarkProgress > 0f) {
                        if (indeterminate) {
                            drawIndeterminateLine(
                                color = checkmarkColor,
                                progress = checkmarkProgress
                            )
                        } else {
                            drawCheckmark(
                                color = checkmarkColor,
                                progress = checkmarkProgress
                            )
                        }
                    }
                }
        )
    }
}

/**
 * Checkbox with Label - Multi-choice selector with associated text
 *
 * @param checked Whether the checkbox is checked
 * @param onCheckedChange Called when the user toggles the checkbox
 * @param label Label text
 * @param modifier Modifier for the checkbox
 * @param enabled Whether the checkbox is enabled
 * @param indeterminate Whether the checkbox is in an indeterminate state
 */
@Composable
fun VetraCheckboxWithLabel(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    indeterminate: Boolean = false
) {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier
            .clip(VetraTheme.shapes.sm)
            .clickable(
                onClick = { onCheckedChange?.invoke(!checked) },
                enabled = enabled && onCheckedChange != null,
                role = Role.Checkbox,
                interactionSource = interactionSource,
                indication = null
            )
            .padding(vertical = 8.dp, horizontal = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        VetraCheckbox(
            checked = checked,
            onCheckedChange = null,  // Handled by Row's clickable
            enabled = enabled,
            indeterminate = indeterminate
        )

        Text(
            text = label,
            style = typography.bodyMd.copy(
                color = if (enabled) colors.textPrimary else colors.textDisabled
            )
        )
    }
}

// ============================================================================
// Drawing Functions
// ============================================================================

/**
 * Draws a checkmark icon with animation
 */
private fun DrawScope.drawCheckmark(
    color: Color,
    progress: Float
) {
    val width = size.width
    val height = size.height

    // Checkmark path coordinates (relative to size)
    val checkPath = Path().apply {
        moveTo(width * 0.25f, height * 0.5f)
        lineTo(width * 0.4f, height * 0.7f)
        lineTo(width * 0.75f, height * 0.3f)
    }

    drawPath(
        path = checkPath,
        color = color.copy(alpha = color.alpha * progress),
        style = Stroke(
            width = 2.dp.toPx(),
            cap = StrokeCap.Round,
            join = StrokeJoin.Round
        )
    )
}

/**
 * Draws an indeterminate line with animation
 */
private fun DrawScope.drawIndeterminateLine(
    color: Color,
    progress: Float
) {
    val width = size.width
    val height = size.height
    val lineWidth = width * 0.5f

    drawLine(
        color = color.copy(alpha = color.alpha * progress),
        start = Offset(
            x = (width - lineWidth) / 2f,
            y = height / 2f
        ),
        end = Offset(
            x = (width + lineWidth) / 2f,
            y = height / 2f
        ),
        strokeWidth = 2.dp.toPx(),
        cap = StrokeCap.Round
    )
}

// ============================================================================
// Previews
// ============================================================================

@Preview
@Composable
private fun VetraCheckboxPreview() {
    VetraTheme {
        var checked1 by remember { mutableStateOf(false) }
        var checked2 by remember { mutableStateOf(true) }
        var checked3 by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Checkbox States",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                VetraCheckbox(
                    checked = false,
                    onCheckedChange = {}
                )
                VetraCheckbox(
                    checked = true,
                    onCheckedChange = {}
                )
                VetraCheckbox(
                    checked = false,
                    onCheckedChange = {},
                    indeterminate = true
                )
            }

            Text(
                "Disabled States",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                VetraCheckbox(
                    checked = false,
                    onCheckedChange = {},
                    enabled = false
                )
                VetraCheckbox(
                    checked = true,
                    onCheckedChange = {},
                    enabled = false
                )
                VetraCheckbox(
                    checked = false,
                    onCheckedChange = {},
                    enabled = false,
                    indeterminate = true
                )
            }

            Text(
                "With Labels",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                VetraCheckboxWithLabel(
                    checked = checked1,
                    onCheckedChange = { checked1 = it },
                    label = "Accept terms and conditions"
                )
                VetraCheckboxWithLabel(
                    checked = checked2,
                    onCheckedChange = { checked2 = it },
                    label = "Subscribe to newsletter"
                )
                VetraCheckboxWithLabel(
                    checked = checked3,
                    onCheckedChange = { checked3 = it },
                    label = "Enable notifications"
                )
            }

            Text(
                "Indeterminate Example",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            var option1 by remember { mutableStateOf(true) }
            var option2 by remember { mutableStateOf(false) }
            var option3 by remember { mutableStateOf(false) }

            val allChecked = option1 && option2 && option3
            val noneChecked = !option1 && !option2 && !option3
            val someChecked = !allChecked && !noneChecked

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                VetraCheckboxWithLabel(
                    checked = allChecked,
                    onCheckedChange = {
                        if (allChecked || someChecked) {
                            option1 = false
                            option2 = false
                            option3 = false
                        } else {
                            option1 = true
                            option2 = true
                            option3 = true
                        }
                    },
                    label = "Select All",
                    indeterminate = someChecked
                )

                Column(
                    modifier = Modifier.padding(start = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    VetraCheckboxWithLabel(
                        checked = option1,
                        onCheckedChange = { option1 = it },
                        label = "Option 1"
                    )
                    VetraCheckboxWithLabel(
                        checked = option2,
                        onCheckedChange = { option2 = it },
                        label = "Option 2"
                    )
                    VetraCheckboxWithLabel(
                        checked = option3,
                        onCheckedChange = { option3 = it },
                        label = "Option 3"
                    )
                }
            }

            Text(
                "Disabled Examples",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                VetraCheckboxWithLabel(
                    checked = false,
                    onCheckedChange = {},
                    label = "Disabled unchecked",
                    enabled = false
                )
                VetraCheckboxWithLabel(
                    checked = true,
                    onCheckedChange = {},
                    label = "Disabled checked",
                    enabled = false
                )
            }
        }
    }
}

@Preview
@Composable
private fun VetraCheckboxDarkPreview() {
    VetraTheme(darkMode = true) {
        var checked1 by remember { mutableStateOf(false) }
        var checked2 by remember { mutableStateOf(true) }
        var checked3 by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Dark Mode Checkboxes",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                VetraCheckboxWithLabel(
                    checked = checked1,
                    onCheckedChange = { checked1 = it },
                    label = "First Option"
                )
                VetraCheckboxWithLabel(
                    checked = checked2,
                    onCheckedChange = { checked2 = it },
                    label = "Second Option"
                )
                VetraCheckboxWithLabel(
                    checked = checked3,
                    onCheckedChange = { checked3 = it },
                    label = "Third Option"
                )
            }
        }
    }
}

