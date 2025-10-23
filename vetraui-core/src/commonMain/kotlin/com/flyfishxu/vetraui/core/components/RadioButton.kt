package com.flyfishxu.vetraui.core.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.theme.VetraTheme
import com.flyfishxu.vetraui.core.theme.vetraShadow
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Vetra Radio Button
 *
 * A modern radio button component with smooth animations and elegant design.
 * Perfect for single-choice selections within a group.
 *
 * Design Features:
 * - Smooth scale transition for the inner indicator
 * - Color transitions for selected/unselected states
 * - Clear visual feedback
 * - Accessible by default
 *
 * Usage:
 * ```
 * var selected by remember { mutableStateOf(false) }
 * VetraRadioButton(
 *     selected = selected,
 *     onClick = { selected = !selected }
 * )
 * ```
 */

private val RadioButtonSize = 20.dp
private val RadioButtonBorderWidth = 2.dp
private val RadioButtonInnerCircleSize = 10.dp
private val RadioButtonTouchTarget = 44.dp
private val RadioButtonAnimationDuration = 250

/**
 * Standard Radio Button - Single choice selector
 *
 * @param selected Whether the radio button is selected
 * @param onClick Called when the user clicks the radio button
 * @param modifier Modifier for the radio button
 * @param enabled Whether the radio button is enabled
 */
@Composable
fun VetraRadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val colors = VetraTheme.colors
    val shadows = VetraTheme.shadows
    val interactionSource = remember { MutableInteractionSource() }

    val borderColor by animateColorAsState(
        targetValue = when {
            selected && enabled -> colors.brand
            !selected && enabled -> colors.border
            selected && !enabled -> colors.textDisabled
            else -> colors.borderSubtle
        },
        animationSpec = tween(durationMillis = RadioButtonAnimationDuration),
        label = "radioButtonBorderColor"
    )

    val innerCircleSize by animateDpAsState(
        targetValue = if (selected) RadioButtonInnerCircleSize else 0.dp,
        animationSpec = tween(durationMillis = RadioButtonAnimationDuration),
        label = "radioButtonInnerCircleSize"
    )

    val innerCircleColor by animateColorAsState(
        targetValue = when {
            selected && enabled -> colors.brand
            selected && !enabled -> colors.textDisabled
            else -> Color.Transparent
        },
        animationSpec = tween(durationMillis = RadioButtonAnimationDuration),
        label = "radioButtonInnerCircleColor"
    )

    Box(
        modifier = modifier
            .size(RadioButtonTouchTarget)
            .clip(CircleShape)
            .clickable(
                onClick = onClick ?: {},
                enabled = enabled && onClick != null,
                role = Role.RadioButton,
                interactionSource = interactionSource,
                indication = null
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(RadioButtonSize)
                .vetraShadow(
                    elevation = if (selected && enabled) shadows.xs else shadows.none,
                    shape = CircleShape
                )
                .border(
                    width = RadioButtonBorderWidth,
                    color = borderColor,
                    shape = CircleShape
                )
                .background(
                    color = colors.canvas,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(innerCircleSize)
                    .background(
                        color = innerCircleColor,
                        shape = CircleShape
                    )
            )
        }
    }
}

/**
 * Radio Button with Label - Single choice selector with associated text
 *
 * @param selected Whether the radio button is selected
 * @param onClick Called when the user clicks the radio button
 * @param label Label text
 * @param modifier Modifier for the radio button
 * @param enabled Whether the radio button is enabled
 */
@Composable
fun VetraRadioButtonWithLabel(
    selected: Boolean,
    onClick: (() -> Unit)?,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier
            .clip(VetraTheme.shapes.sm)
            .clickable(
                onClick = onClick ?: {},
                enabled = enabled && onClick != null,
                role = Role.RadioButton,
                interactionSource = interactionSource,
                indication = null
            )
            .padding(vertical = 8.dp, horizontal = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        VetraRadioButton(
            selected = selected,
            onClick = null,  // Handled by Row's clickable
            enabled = enabled
        )

        Text(
            text = label,
            style = typography.bodyMd.copy(
                color = if (enabled) colors.textPrimary else colors.textDisabled
            )
        )
    }
}

/**
 * Radio Button Group - Convenience composable for managing a group of radio buttons
 *
 * @param options List of options to display
 * @param selectedOption Currently selected option
 * @param onOptionSelected Called when an option is selected
 * @param modifier Modifier for the group
 * @param enabled Whether the radio buttons are enabled
 * @param optionLabel Lambda to get the label for each option
 */
@Composable
fun <T> VetraRadioGroup(
    options: List<T>,
    selectedOption: T,
    onOptionSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    optionLabel: (T) -> String = { it.toString() }
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        options.forEach { option ->
            VetraRadioButtonWithLabel(
                selected = option == selectedOption,
                onClick = { onOptionSelected(option) },
                label = optionLabel(option),
                enabled = enabled
            )
        }
    }
}

// ============================================================================
// Previews
// ============================================================================

@Preview
@Composable
private fun VetraRadioButtonPreview() {
    VetraTheme {
        var selectedIndex by remember { mutableStateOf(0) }

        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Radio Button States",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                VetraRadioButton(
                    selected = false,
                    onClick = {}
                )
                VetraRadioButton(
                    selected = true,
                    onClick = {}
                )
            }

            Text(
                "Disabled States",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                VetraRadioButton(
                    selected = false,
                    onClick = {},
                    enabled = false
                )
                VetraRadioButton(
                    selected = true,
                    onClick = {},
                    enabled = false
                )
            }

            Text(
                "With Labels",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                VetraRadioButtonWithLabel(
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0 },
                    label = "Option 1"
                )
                VetraRadioButtonWithLabel(
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1 },
                    label = "Option 2"
                )
                VetraRadioButtonWithLabel(
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2 },
                    label = "Option 3"
                )
            }

            Text(
                "Radio Group",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            var selectedSize by remember { mutableStateOf("Medium") }
            VetraRadioGroup(
                options = listOf("Small", "Medium", "Large"),
                selectedOption = selectedSize,
                onOptionSelected = { selectedSize = it }
            )

            Text(
                "Disabled Group",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            VetraRadioGroup(
                options = listOf("Option A", "Option B", "Option C"),
                selectedOption = "Option B",
                onOptionSelected = {},
                enabled = false
            )
        }
    }
}

@Preview
@Composable
private fun VetraRadioButtonDarkPreview() {
    VetraTheme(darkMode = true) {
        var selectedIndex by remember { mutableStateOf(1) }

        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Dark Mode Radio Buttons",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                VetraRadioButtonWithLabel(
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0 },
                    label = "First Option"
                )
                VetraRadioButtonWithLabel(
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1 },
                    label = "Second Option"
                )
                VetraRadioButtonWithLabel(
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2 },
                    label = "Third Option"
                )
            }
        }
    }
}

