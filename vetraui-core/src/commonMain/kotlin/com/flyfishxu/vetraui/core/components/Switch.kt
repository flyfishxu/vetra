package com.flyfishxu.vetraui.core.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.theme.VetraTheme
import com.flyfishxu.vetraui.core.theme.vetraShadow
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Vetra Switch
 *
 * A modern toggle switch component with smooth animations and elegant design.
 *
 * Design Features:
 * - Smooth thumb transition
 * - Color transitions for states
 * - Subtle shadow on active state
 * - Clear visual feedback
 */

private val SwitchWidth = 48.dp
private val SwitchHeight = 28.dp
private val SwitchPadding = 3.dp
private val SwitchThumbSize = 22.dp
private val SwitchAnimationDuration = 250

/**
 * Standard Switch - Toggle between on/off states
 *
 * @param checked Whether the switch is checked
 * @param onCheckedChange Called when the user toggles the switch
 * @param modifier Modifier for the switch
 * @param enabled Whether the switch is enabled
 */
@Composable
fun VetraSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val colors = VetraTheme.colors
    val shadows = VetraTheme.shadows
    val interactionSource = remember { MutableInteractionSource() }

    val backgroundColor by animateColorAsState(
        targetValue = when {
            checked && enabled -> colors.brand
            checked && !enabled -> colors.canvasSubtle
            !checked && enabled -> colors.border
            else -> colors.borderSubtle
        },
        animationSpec = tween(durationMillis = SwitchAnimationDuration),
        label = "switchBackgroundColor"
    )

    val thumbOffset by animateDpAsState(
        targetValue = if (checked) {
            SwitchWidth - SwitchThumbSize - SwitchPadding
        } else {
            SwitchPadding
        },
        animationSpec = tween(durationMillis = SwitchAnimationDuration),
        label = "switchThumbOffset"
    )

    val thumbColor by animateColorAsState(
        targetValue = when {
            checked && enabled -> colors.onBrand
            !checked && enabled -> colors.canvasElevated
            else -> colors.textDisabled
        },
        animationSpec = tween(durationMillis = SwitchAnimationDuration),
        label = "switchThumbColor"
    )

    val switchShape = RoundedCornerShape(percent = 50)

    Box(
        modifier = modifier
            .size(width = SwitchWidth, height = SwitchHeight)
            .vetraShadow(
                elevation = if (checked && enabled) shadows.xs else shadows.none,
                shape = switchShape
            )
            .clip(switchShape)
            .background(backgroundColor)
            .clickable(
                onClick = { onCheckedChange(!checked) },
                enabled = enabled,
                role = Role.Switch,
                interactionSource = interactionSource,
                indication = null
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .padding(start = thumbOffset)
                .size(SwitchThumbSize)
                .vetraShadow(
                    elevation = if (enabled) shadows.xs else shadows.none,
                    shape = CircleShape
                )
                .background(
                    color = thumbColor,
                    shape = CircleShape
                )
        )
    }
}

/**
 * Switch with Label - Toggle with associated text
 *
 * @param checked Whether the switch is checked
 * @param onCheckedChange Called when the user toggles the switch
 * @param label Label text
 * @param modifier Modifier for the switch
 * @param enabled Whether the switch is enabled
 */
@Composable
fun VetraSwitchWithLabel(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier
            .clickable(
                onClick = { onCheckedChange(!checked) },
                enabled = enabled,
                role = Role.Switch,
                interactionSource = interactionSource,
                indication = null
            ),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        VetraSwitch(
            checked = checked,
            onCheckedChange = {},  // Handled by Row's clickable
            enabled = enabled
        )

        androidx.compose.material3.Text(
            text = label,
            style = typography.bodyMd.copy(
                color = if (enabled) colors.textPrimary else colors.textDisabled
            )
        )
    }
}

// ============================================================================
// Previews
// ============================================================================

@Preview
@Composable
private fun VetraSwitchPreview() {
    VetraTheme {
        var checked by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            androidx.compose.material3.Text(
                "Switch States",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                VetraSwitch(
                    checked = false,
                    onCheckedChange = {}
                )
                VetraSwitch(
                    checked = true,
                    onCheckedChange = {}
                )
            }

            androidx.compose.material3.Text(
                "Disabled States",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                VetraSwitch(
                    checked = false,
                    onCheckedChange = {},
                    enabled = false
                )
                VetraSwitch(
                    checked = true,
                    onCheckedChange = {},
                    enabled = false
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            androidx.compose.material3.Text(
                "With Labels",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                VetraSwitchWithLabel(
                    checked = checked,
                    onCheckedChange = { checked = it },
                    label = "Enable Notifications"
                )

                VetraSwitchWithLabel(
                    checked = !checked,
                    onCheckedChange = { checked = !it },
                    label = "Dark Mode"
                )

                VetraSwitchWithLabel(
                    checked = true,
                    onCheckedChange = {},
                    label = "Read-only Setting",
                    enabled = false
                )
            }
        }
    }
}
