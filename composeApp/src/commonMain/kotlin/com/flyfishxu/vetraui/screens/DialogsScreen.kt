package com.flyfishxu.vetraui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.*
import com.flyfishxu.vetraui.core.theme.VetraTheme

/**
 * Dialogs Screen
 *
 * Demonstrates all dialog variants and use cases
 */
@Composable
fun DialogsScreen() {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography

    // Dialog states
    var showStandardDialog by remember { mutableStateOf(false) }
    var showConfirmDialog by remember { mutableStateOf(false) }
    var showDangerDialog by remember { mutableStateOf(false) }
    var showInfoDialog by remember { mutableStateOf(false) }
    var showSuccessDialog by remember { mutableStateOf(false) }
    var showCustomDialog by remember { mutableStateOf(false) }
    var showWithoutTitleDialog by remember { mutableStateOf(false) }
    var showComplexDialog by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.canvas),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    "Dialogs",
                    style = typography.displayMd.copy(color = colors.textPrimary)
                )
                Text(
                    "Modal overlays for important interactions and messages",
                    style = typography.bodyLg.copy(color = colors.textSecondary)
                )
            }
        }

        // Standard Dialog
        item {
            DemoSection(title = "Standard Dialog") {
                VetraButton(onClick = { showStandardDialog = true }) {
                    Text("Show Standard Dialog")
                }
            }
        }

        // Alert Dialog - Info
        item {
            DemoSection(title = "Alert Dialog - Info") {
                VetraButton(onClick = { showInfoDialog = true }) {
                    Text("Show Info Alert")
                }
            }
        }

        // Alert Dialog - Confirmation
        item {
            DemoSection(title = "Alert Dialog - Confirmation") {
                VetraSecondaryButton(onClick = { showConfirmDialog = true }) {
                    Text("Show Confirmation")
                }
            }
        }

        // Alert Dialog - Danger
        item {
            DemoSection(title = "Alert Dialog - Danger") {
                VetraDangerButton(onClick = { showDangerDialog = true }) {
                    Text("Delete Item")
                }
            }
        }

        // Success Dialog
        item {
            DemoSection(title = "Success Dialog") {
                VetraButton(onClick = { showSuccessDialog = true }) {
                    Text("Show Success")
                }
            }
        }

        // Dialog without Title
        item {
            DemoSection(title = "Dialog without Title") {
                VetraOutlinedButton(onClick = { showWithoutTitleDialog = true }) {
                    Text("Simple Message")
                }
            }
        }

        // Complex Dialog
        item {
            DemoSection(title = "Complex Dialog") {
                VetraSecondaryButton(onClick = { showComplexDialog = true }) {
                    Text("Show Complex Dialog")
                }
            }
        }

        // Custom Dialog
        item {
            DemoSection(title = "Custom Dialog") {
                VetraOutlinedButton(onClick = { showCustomDialog = true }) {
                    Text("Show Custom Dialog")
                }
            }
        }

        // Design Notes
        item {
            VetraOutlinedCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        "Design Notes",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    VetraSubtleDivider()
                    Text(
                        "• Standard Dialog: For complex interactions with title, content, and actions\n" +
                                "• Alert Dialog: Quick confirmations with simplified API\n" +
                                "• Custom Dialog: Full control over content and layout\n" +
                                "• Animations: Smooth scale + fade for elegant transitions\n" +
                                "• Elevation: XL shadow (16dp) for proper hierarchy\n" +
                                "• Shape: XL corners (24dp) for modern aesthetics",
                        style = typography.bodySm.copy(color = colors.textSecondary)
                    )
                }
            }
        }

        // Bottom spacing
        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    // Dialog instances
    VetraDialog(
        visible = showStandardDialog,
        onDismissRequest = { showStandardDialog = false },
        title = {
            Text(
                "Standard Dialog",
                style = typography.headingLg.copy(color = colors.textPrimary)
            )
        },
        content = {
            Text(
                "This is a standard Vetra dialog with a title, content area, and action buttons. " +
                        "It features elegant scale and fade animations, and automatically adapts to light and dark modes.",
                style = typography.bodyMd.copy(color = colors.textSecondary)
            )
        },
        confirmButton = {
            VetraButton(onClick = { showStandardDialog = false }) {
                Text("Confirm")
            }
        },
        dismissButton = {
            VetraGhostButton(onClick = { showStandardDialog = false }) {
                Text("Cancel")
            }
        }
    )

    VetraAlertDialog(
        visible = showInfoDialog,
        onDismissRequest = { showInfoDialog = false },
        title = "Important Update",
        message = "A new version of the app is available. Update now to get the latest features and improvements.",
        onConfirm = { showInfoDialog = false },
        confirmText = "Update",
        dismissText = "Later",
        isDanger = false
    )

    VetraAlertDialog(
        visible = showConfirmDialog,
        onDismissRequest = { showConfirmDialog = false },
        title = "Confirm Action",
        message = "Are you sure you want to proceed with this action? This will make changes to your settings.",
        onConfirm = { showConfirmDialog = false },
        confirmText = "Proceed",
        dismissText = "Cancel",
        isDanger = false
    )

    VetraAlertDialog(
        visible = showDangerDialog,
        onDismissRequest = { showDangerDialog = false },
        title = "Delete Item",
        message = "Are you sure you want to delete this item? This action cannot be undone and all associated data will be permanently removed.",
        onConfirm = { showDangerDialog = false },
        confirmText = "Delete",
        dismissText = "Cancel",
        isDanger = true
    )

    VetraDialog(
        visible = showSuccessDialog,
        onDismissRequest = { showSuccessDialog = false },
        title = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = null,
                    tint = colors.success,
                    modifier = Modifier.size(28.dp)
                )
                Text(
                    "Success!",
                    style = typography.headingLg.copy(color = colors.success)
                )
            }
        },
        content = {
            Text(
                "Your operation completed successfully. All changes have been saved and synced.",
                style = typography.bodyMd.copy(color = colors.textSecondary)
            )
        },
        confirmButton = {
            VetraButton(onClick = { showSuccessDialog = false }) {
                Text("Great!")
            }
        }
    )

    VetraDialog(
        visible = showWithoutTitleDialog,
        onDismissRequest = { showWithoutTitleDialog = false },
        content = {
            Text(
                "This is a simple dialog without a title. Perfect for quick confirmations or short messages that don't need extra hierarchy.",
                style = typography.bodyMd.copy(color = colors.textPrimary)
            )
        },
        confirmButton = {
            VetraButton(onClick = { showWithoutTitleDialog = false }) {
                Text("OK")
            }
        }
    )

    VetraDialog(
        visible = showComplexDialog,
        onDismissRequest = { showComplexDialog = false },
        title = {
            Text(
                "Choose Your Preference",
                style = typography.headingLg.copy(color = colors.textPrimary)
            )
        },
        content = {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Select how you'd like to receive notifications:",
                    style = typography.bodyMd.copy(color = colors.textSecondary)
                )

                var selectedOption by remember { mutableStateOf(0) }

                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    RadioOption(
                        selected = selectedOption == 0,
                        onClick = { selectedOption = 0 },
                        text = "Email notifications"
                    )
                    RadioOption(
                        selected = selectedOption == 1,
                        onClick = { selectedOption = 1 },
                        text = "Push notifications"
                    )
                    RadioOption(
                        selected = selectedOption == 2,
                        onClick = { selectedOption = 2 },
                        text = "Both"
                    )
                    RadioOption(
                        selected = selectedOption == 3,
                        onClick = { selectedOption = 3 },
                        text = "None"
                    )
                }
            }
        },
        confirmButton = {
            VetraButton(onClick = { showComplexDialog = false }) {
                Text("Save")
            }
        },
        dismissButton = {
            VetraGhostButton(onClick = { showComplexDialog = false }) {
                Text("Cancel")
            }
        }
    )

    VetraCustomDialog(
        visible = showCustomDialog,
        onDismissRequest = { showCustomDialog = false }
    ) {
        Column(
            modifier = Modifier.padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                tint = colors.brand
            )

            Text(
                "Custom Layout",
                style = typography.displaySm.copy(color = colors.textPrimary)
            )

            Text(
                "This dialog uses VetraCustomDialog for complete control over the content and layout. You can build any custom UI you need.",
                style = typography.bodyMd.copy(color = colors.textSecondary)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                VetraOutlinedButton(
                    onClick = { showCustomDialog = false },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cancel")
                }
                VetraButton(
                    onClick = { showCustomDialog = false },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Confirm")
                }
            }
        }
    }
}

@Composable
private fun DemoSection(
    title: String,
    content: @Composable () -> Unit
) {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography

    VetraCard(modifier = Modifier.fillMaxWidth()) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(
                text = title,
                style = typography.headingMd.copy(color = colors.textPrimary)
            )
            content()
        }
    }
}

@Composable
private fun RadioOption(
    selected: Boolean,
    onClick: () -> Unit,
    text: String
) {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        VetraRadioButton(
            selected = selected,
            onClick = onClick
        )
        Text(
            text = text,
            style = typography.bodyMd.copy(color = colors.textPrimary)
        )
    }
}

