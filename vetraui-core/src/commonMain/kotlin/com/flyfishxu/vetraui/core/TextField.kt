package com.flyfishxu.vetraui.core

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.theme.VetraTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Vetra TextField
 *
 * A modern, elegant text field with sophisticated animations and clean design.
 *
 * Design Features:
 * - Animated underline that expands from center on focus
 * - Floating label with smooth scale and position transitions
 * - Animated placeholder with fade and slide effects
 * - Clear visual states (unfocused, focused, error, disabled)
 * - Customizable leading and trailing icons
 * - Support for single and multiline input
 *
 * This component combines the best of modern UI design with accessibility
 * and usability in mind.
 */

private val TextFieldHeight = 56.dp
private val TextFieldLineWidth = 2.dp
private val TextFieldPadding = 16.dp
private val AnimationDurationMs = 300

/**
 * Standard TextField - Clean input with floating label
 *
 * @param value The current text value
 * @param onValueChange Callback when text changes
 * @param modifier Modifier for the text field
 * @param enabled Whether the text field is enabled
 * @param readOnly Whether the text field is read-only
 * @param label Optional label text that floats above input
 * @param placeholder Optional placeholder text
 * @param leadingIcon Optional composable for leading icon
 * @param trailingIcon Optional composable for trailing icon
 * @param isError Whether the field is in error state
 * @param visualTransformation Visual transformation (e.g., password masking)
 * @param keyboardOptions Keyboard options
 * @param keyboardActions Keyboard actions
 * @param singleLine Whether the field should be single line
 * @param maxLines Maximum number of lines
 */
@Composable
fun VetraTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val typography = VetraTheme.typography

    val interactionSource = remember { MutableInteractionSource() }
    var isFocused by remember { mutableStateOf(false) }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is FocusInteraction.Focus -> isFocused = true
                is FocusInteraction.Unfocus -> isFocused = false
            }
        }
    }

    val shouldFloatLabel = isFocused || value.isNotEmpty()

    // Colors
    val accentColor = when {
        isError -> colors.danger
        isFocused -> colors.brand
        else -> colors.border
    }

    val textColor = if (enabled) colors.textPrimary else colors.textDisabled
    val labelColor = if (isError) colors.danger else colors.textSecondary
    val placeholderColor = colors.textTertiary
    val backgroundColor = if (enabled) colors.canvasElevated else colors.canvasSubtle

    // Animations
    val accentLineColor by animateColorAsState(
        targetValue = accentColor,
        animationSpec = tween(durationMillis = AnimationDurationMs, easing = EaseInOutCubic),
        label = "accentLineColor"
    )

    val lineExpansionProgress by animateFloatAsState(
        targetValue = if (isFocused) 1f else 0f,
        animationSpec = tween(durationMillis = AnimationDurationMs, easing = EaseInOutCubic),
        label = "lineExpansion"
    )

    val labelScale by animateFloatAsState(
        targetValue = if (shouldFloatLabel) 0.85f else 1f,
        animationSpec = tween(durationMillis = AnimationDurationMs, easing = EaseOut),
        label = "labelScale"
    )

    val labelAlpha by animateFloatAsState(
        targetValue = if (shouldFloatLabel) 1f else 0.6f,
        animationSpec = tween(durationMillis = AnimationDurationMs),
        label = "labelAlpha"
    )

    val placeholderAlpha by animateFloatAsState(
        targetValue = if (shouldFloatLabel && value.isEmpty()) 1f else 0f,
        animationSpec = tween(durationMillis = 250, easing = EaseOut),
        label = "placeholderAlpha"
    )

    Column(modifier = modifier) {
        // Floating label (above the field when active)
        if (label != null && shouldFloatLabel) {
            Box(
                modifier = Modifier
                    .padding(start = TextFieldPadding, bottom = 6.dp)
                    .scale(labelScale)
                    .alpha(labelAlpha)
            ) {
                Text(
                    text = label,
                    style = typography.labelMd.copy(color = labelColor)
                )
            }
        }

        // Main text field container
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = TextFieldHeight)
                .clip(shapes.sm)
                .background(backgroundColor)
                .padding(horizontal = TextFieldPadding, vertical = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            // Animated underline
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(TextFieldLineWidth)
                    .padding(top = 4.dp)
                    .align(Alignment.BottomCenter)
            ) {
                val canvasWidth = size.width
                val lineHeightPx = TextFieldLineWidth.toPx()

                // Base line (subtle)
                drawLine(
                    color = colors.borderSubtle,
                    start = Offset(0f, lineHeightPx / 2),
                    end = Offset(canvasWidth, lineHeightPx / 2),
                    strokeWidth = lineHeightPx
                )

                // Accent line (expands from center)
                if (lineExpansionProgress > 0f) {
                    val expandedWidth = canvasWidth * lineExpansionProgress
                    val startX = (canvasWidth - expandedWidth) / 2
                    drawLine(
                        color = accentLineColor,
                        start = Offset(startX, lineHeightPx / 2),
                        end = Offset(startX + expandedWidth, lineHeightPx / 2),
                        strokeWidth = lineHeightPx
                    )
                }
            }

            // Label inside field (when not floating)
            if (label != null && !shouldFloatLabel) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(
                            start = if (leadingIcon != null) 36.dp else 0.dp,
                            end = if (trailingIcon != null) 36.dp else 0.dp
                        )
                        .alpha(labelAlpha)
                ) {
                    Text(
                        text = label,
                        style = typography.bodyLg.copy(color = labelColor)
                    )
                }
            }

            // Content row with icons and text
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Leading icon
                if (leadingIcon != null) {
                    Box(modifier = Modifier.padding(end = 12.dp)) {
                        CompositionLocalProvider(
                            LocalContentColor provides colors.textSecondary
                        ) {
                            leadingIcon()
                        }
                    }
                }

                // Text field and placeholder
                Box(modifier = Modifier.weight(1f)) {
                    // Placeholder
                    if (placeholder != null && shouldFloatLabel && value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = typography.bodyLg.copy(color = placeholderColor),
                            modifier = Modifier.alpha(placeholderAlpha)
                        )
                    }

                    // Main text field
                    BasicTextField(
                        value = value,
                        onValueChange = onValueChange,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = enabled,
                        readOnly = readOnly,
                        singleLine = singleLine,
                        maxLines = maxLines,
                        textStyle = typography.bodyLg.copy(color = textColor),
                        cursorBrush = SolidColor(colors.brand),
                        visualTransformation = visualTransformation,
                        keyboardOptions = keyboardOptions,
                        keyboardActions = keyboardActions,
                        interactionSource = interactionSource
                    )
                }

                // Trailing icon
                if (trailingIcon != null) {
                    Box(modifier = Modifier.padding(start = 12.dp)) {
                        CompositionLocalProvider(
                            LocalContentColor provides colors.textSecondary
                        ) {
                            trailingIcon()
                        }
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
private fun VetraTextFieldPreview() {
    VetraTheme {
        var text by remember { mutableStateOf("") }
        var passwordText by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                "Text Fields",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            // Basic
            VetraTextField(
                value = text,
                onValueChange = { text = it },
                label = "Username",
                placeholder = "Enter your username"
            )

            // With leading icon
            VetraTextField(
                value = text,
                onValueChange = { text = it },
                label = "Email",
                placeholder = "your@email.com",
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Mail, contentDescription = "Mail")
                }
            )

            // With trailing icon
            VetraTextField(
                value = text,
                onValueChange = { text = it },
                label = "Search",
                placeholder = "Search something...",
                trailingIcon = {
                    Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search")
                }
            )

            // Error state
            VetraTextField(
                value = "invalid@",
                onValueChange = {},
                label = "Error Field",
                isError = true
            )

            // Disabled
            VetraTextField(
                value = "Disabled text",
                onValueChange = {},
                enabled = false,
                label = "Disabled"
            )

            // Password
            VetraTextField(
                value = passwordText,
                onValueChange = { passwordText = it },
                label = "Password",
                placeholder = "Enter password",
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        }
    }
}
