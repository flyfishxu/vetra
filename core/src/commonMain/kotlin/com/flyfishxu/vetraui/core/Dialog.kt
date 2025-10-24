package com.flyfishxu.vetraui.core

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.flyfishxu.vetraui.core.theme.VetraTheme
import com.flyfishxu.vetraui.core.theme.vetraShadow
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Vetra Dialog
 *
 * A modern, elegant dialog component with smooth animations and flexible layout.
 * Designed to present focused content and actions in an overlay.
 *
 * Dialog Variants:
 * - Standard (VetraDialog): Full dialog with title, content, and actions
 * - Alert (VetraAlertDialog): Quick alert with message and confirm/dismiss actions
 * - Custom (VetraCustomDialog): Fully customizable content
 *
 * Design Features:
 * - Elegant scale + fade animations
 * - Adaptive shadows for depth
 * - Responsive sizing
 * - Accessibility-focused
 * - Seamless light/dark mode support
 */

private val DialogMinWidth = 280.dp
private val DialogMaxWidth = 560.dp
private val DialogPadding = 24.dp
private val DialogTitleBottomPadding = 16.dp
private val DialogContentBottomPadding = 24.dp
private val DialogButtonSpacing = 12.dp
private val DialogAnimationDuration = 300

/**
 * Standard Dialog - Full dialog with title, content, and actions
 *
 * A complete dialog component with all standard elements. Use for complex
 * interactions that require user confirmation or input.
 *
 * @param visible Whether the dialog is visible
 * @param onDismissRequest Called when the user attempts to dismiss the dialog
 * @param title Dialog title (optional)
 * @param content Dialog content area
 * @param confirmButton Confirm action button
 * @param dismissButton Dismiss action button (optional)
 * @param modifier Modifier for the dialog container
 * @param properties Dialog properties for customization
 */
@Composable
fun VetraDialog(
    visible: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    title: (@Composable () -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
    confirmButton: @Composable RowScope.() -> Unit,
    dismissButton: (@Composable RowScope.() -> Unit)? = null,
    properties: DialogProperties = DialogProperties()
) {
    if (visible) {
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = properties
        ) {
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(
                    animationSpec = tween(durationMillis = DialogAnimationDuration)
                ) + scaleIn(
                    initialScale = 0.8f,
                    transformOrigin = TransformOrigin.Center,
                    animationSpec = tween(durationMillis = DialogAnimationDuration)
                ),
                exit = fadeOut(
                    animationSpec = tween(durationMillis = DialogAnimationDuration)
                ) + scaleOut(
                    targetScale = 0.8f,
                    transformOrigin = TransformOrigin.Center,
                    animationSpec = tween(durationMillis = DialogAnimationDuration)
                )
            ) {
                DialogContent(
                    modifier = modifier,
                    title = title,
                    content = content,
                    buttons = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            dismissButton?.invoke(this)
                            if (dismissButton != null) {
                                Spacer(modifier = Modifier.width(DialogButtonSpacing))
                            }
                            confirmButton()
                        }
                    }
                )
            }
        }
    }
}

/**
 * Alert Dialog - Quick alert with message and confirm/dismiss actions
 *
 * A simplified dialog for showing important messages with quick actions.
 * Best for confirmations, warnings, or simple user choices.
 *
 * @param visible Whether the dialog is visible
 * @param onDismissRequest Called when the user attempts to dismiss the dialog
 * @param title Alert title
 * @param message Alert message text
 * @param confirmText Text for confirm button (default: "Confirm")
 * @param dismissText Text for dismiss button (default: "Cancel")
 * @param onConfirm Called when confirm button is clicked
 * @param modifier Modifier for the dialog container
 * @param isDanger Whether this is a dangerous/destructive action
 * @param properties Dialog properties for customization
 */
@Composable
fun VetraAlertDialog(
    visible: Boolean,
    onDismissRequest: () -> Unit,
    title: String,
    message: String,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
    confirmText: String = "Confirm",
    dismissText: String = "Cancel",
    isDanger: Boolean = false,
    properties: DialogProperties = DialogProperties()
) {
    VetraDialog(
        visible = visible,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        title = {
            Text(
                text = title,
                style = VetraTheme.typography.headingLg.copy(
                    color = VetraTheme.colors.textPrimary
                )
            )
        },
        content = {
            Text(
                text = message,
                style = VetraTheme.typography.bodyMd.copy(
                    color = VetraTheme.colors.textSecondary
                )
            )
        },
        confirmButton = {
            if (isDanger) {
                VetraDangerButton(onClick = onConfirm) {
                    Text(confirmText)
                }
            } else {
                VetraButton(onClick = onConfirm) {
                    Text(confirmText)
                }
            }
        },
        dismissButton = {
            VetraGhostButton(onClick = onDismissRequest) {
                Text(dismissText)
            }
        },
        properties = properties
    )
}

/**
 * Custom Dialog - Fully customizable dialog content
 *
 * A minimal dialog wrapper for completely custom content.
 * Use when you need full control over the dialog appearance.
 *
 * @param visible Whether the dialog is visible
 * @param onDismissRequest Called when the user attempts to dismiss the dialog
 * @param modifier Modifier for the dialog container
 * @param properties Dialog properties for customization
 * @param content Fully custom dialog content
 */
@Composable
fun VetraCustomDialog(
    visible: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    properties: DialogProperties = DialogProperties(),
    content: @Composable () -> Unit
) {
    if (visible) {
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = properties
        ) {
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(
                    animationSpec = tween(durationMillis = DialogAnimationDuration)
                ) + scaleIn(
                    initialScale = 0.8f,
                    transformOrigin = TransformOrigin.Center,
                    animationSpec = tween(durationMillis = DialogAnimationDuration)
                ),
                exit = fadeOut(
                    animationSpec = tween(durationMillis = DialogAnimationDuration)
                ) + scaleOut(
                    targetScale = 0.8f,
                    transformOrigin = TransformOrigin.Center,
                    animationSpec = tween(durationMillis = DialogAnimationDuration)
                )
            ) {
                Box(
                    modifier = modifier
                        .sizeIn(minWidth = DialogMinWidth, maxWidth = DialogMaxWidth)
                        .vetraShadow(
                            elevation = VetraTheme.shadows.xl,
                            shape = VetraTheme.shapes.xl
                        )
                        .clip(VetraTheme.shapes.xl)
                        .background(VetraTheme.colors.canvasElevated)
                ) {
                    content()
                }
            }
        }
    }
}

/**
 * Internal dialog content layout
 */
@Composable
private fun DialogContent(
    modifier: Modifier = Modifier,
    title: (@Composable () -> Unit)?,
    content: @Composable ColumnScope.() -> Unit,
    buttons: @Composable () -> Unit
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val shadows = VetraTheme.shadows

    Column(
        modifier = modifier
            .sizeIn(minWidth = DialogMinWidth, maxWidth = DialogMaxWidth)
            .vetraShadow(elevation = shadows.xl, shape = shapes.xl)
            .clip(shapes.xl)
            .background(colors.canvasElevated)
            .padding(DialogPadding)
    ) {
        // Title
        if (title != null) {
            title()
            Spacer(modifier = Modifier.height(DialogTitleBottomPadding))
        }

        // Content
        Column(content = content)
        Spacer(modifier = Modifier.height(DialogContentBottomPadding))

        // Buttons
        buttons()
    }
}

// ============================================================================
// Previews
// ============================================================================

@Preview
@Composable
private fun VetraDialogPreview() {
    VetraTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(VetraTheme.colors.canvas),
            contentAlignment = Alignment.Center
        ) {
            VetraDialog(
                visible = true,
                onDismissRequest = {},
                title = {
                    Text(
                        "Dialog Title",
                        style = VetraTheme.typography.headingLg.copy(
                            color = VetraTheme.colors.textPrimary
                        )
                    )
                },
                content = {
                    Text(
                        "This is a standard Vetra dialog with a title, content area, and action buttons. " +
                                "It features elegant animations and adapts to both light and dark modes.",
                        style = VetraTheme.typography.bodyMd.copy(
                            color = VetraTheme.colors.textSecondary
                        )
                    )
                },
                confirmButton = {
                    VetraButton(onClick = {}) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    VetraGhostButton(onClick = {}) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun VetraAlertDialogPreview() {
    VetraTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(VetraTheme.colors.canvas),
            contentAlignment = Alignment.Center
        ) {
            VetraAlertDialog(
                visible = true,
                onDismissRequest = {},
                title = "Delete Item",
                message = "Are you sure you want to delete this item? This action cannot be undone.",
                onConfirm = {},
                confirmText = "Delete",
                isDanger = true
            )
        }
    }
}

@Preview
@Composable
private fun VetraCustomDialogPreview() {
    VetraTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(VetraTheme.colors.canvas),
            contentAlignment = Alignment.Center
        ) {
            VetraCustomDialog(
                visible = true,
                onDismissRequest = {}
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Custom Dialog",
                        style = VetraTheme.typography.headingXl.copy(
                            color = VetraTheme.colors.textPrimary
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "This is a fully customizable dialog. You have complete control over the content and layout.",
                        style = VetraTheme.typography.bodyMd.copy(
                            color = VetraTheme.colors.textSecondary
                        )
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    VetraButton(onClick = {}) {
                        Text("Got it")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun VetraDialogDarkPreview() {
    VetraTheme(darkMode = true) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(VetraTheme.colors.canvas),
            contentAlignment = Alignment.Center
        ) {
            VetraDialog(
                visible = true,
                onDismissRequest = {},
                title = {
                    Text(
                        "Dark Mode Dialog",
                        style = VetraTheme.typography.headingLg.copy(
                            color = VetraTheme.colors.textPrimary
                        )
                    )
                },
                content = {
                    Text(
                        "The dialog automatically adapts to dark mode with enhanced shadows and appropriate colors.",
                        style = VetraTheme.typography.bodyMd.copy(
                            color = VetraTheme.colors.textSecondary
                        )
                    )
                },
                confirmButton = {
                    VetraButton(onClick = {}) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    VetraGhostButton(onClick = {}) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun VetraDialogWithoutTitlePreview() {
    VetraTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(VetraTheme.colors.canvas),
            contentAlignment = Alignment.Center
        ) {
            VetraDialog(
                visible = true,
                onDismissRequest = {},
                content = {
                    Text(
                        "This dialog has no title, only content and actions. " +
                                "Perfect for simple confirmations.",
                        style = VetraTheme.typography.bodyMd.copy(
                            color = VetraTheme.colors.textPrimary
                        )
                    )
                },
                confirmButton = {
                    VetraButton(onClick = {}) {
                        Text("OK")
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun VetraAlertDialogInfoPreview() {
    VetraTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(VetraTheme.colors.canvas),
            contentAlignment = Alignment.Center
        ) {
            VetraAlertDialog(
                visible = true,
                onDismissRequest = {},
                title = "Important Update",
                message = "A new version of the app is available. Update now to get the latest features and improvements.",
                onConfirm = {},
                confirmText = "Update",
                dismissText = "Later",
                isDanger = false
            )
        }
    }
}

