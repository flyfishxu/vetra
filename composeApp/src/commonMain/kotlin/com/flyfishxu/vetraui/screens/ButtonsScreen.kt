package com.flyfishxu.vetraui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.components.VetraButton
import com.flyfishxu.vetraui.core.components.VetraDangerButton
import com.flyfishxu.vetraui.core.components.VetraFilledIconButton
import com.flyfishxu.vetraui.core.components.VetraGhostButton
import com.flyfishxu.vetraui.core.components.VetraIconButton
import com.flyfishxu.vetraui.core.components.VetraOutlinedButton
import com.flyfishxu.vetraui.core.components.VetraOutlinedIconButton
import com.flyfishxu.vetraui.core.components.VetraSecondaryButton
import com.flyfishxu.vetraui.core.theme.VetraTheme

@Composable
fun ButtonsScreen() {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        // Primary Buttons
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Primary Buttons",
                    style = typography.headingLg.copy(color = colors.textPrimary)
                )
                Text(
                    "For main actions. Use sparingly - one per section.",
                    style = typography.bodyMd.copy(color = colors.textSecondary)
                )

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    VetraButton(onClick = {}) {
                        Text("Primary")
                    }
                    VetraButton(onClick = {}, enabled = false) {
                        Text("Disabled")
                    }
                }

                VetraButton(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Filled.Download,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Download")
                }
            }
        }

        // Secondary Buttons
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Secondary Buttons",
                    style = typography.headingLg.copy(color = colors.textPrimary)
                )
                Text(
                    "For important but not primary actions.",
                    style = typography.bodyMd.copy(color = colors.textSecondary)
                )

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    VetraSecondaryButton(onClick = {}) {
                        Text("Secondary")
                    }
                    VetraSecondaryButton(onClick = {}, enabled = false) {
                        Text("Disabled")
                    }
                }

                VetraSecondaryButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Like")
                }
            }
        }

        // Outlined Buttons
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Outlined Buttons",
                    style = typography.headingLg.copy(color = colors.textPrimary)
                )
                Text(
                    "For tertiary actions or when you need many buttons.",
                    style = typography.bodyMd.copy(color = colors.textSecondary)
                )

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    VetraOutlinedButton(onClick = {}) {
                        Text("Outlined")
                    }
                    VetraOutlinedButton(onClick = {}, enabled = false) {
                        Text("Disabled")
                    }
                }
            }
        }

        // Ghost Buttons
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Ghost Buttons",
                    style = typography.headingLg.copy(color = colors.textPrimary)
                )
                Text(
                    "Minimal emphasis, perfect for dense UIs.",
                    style = typography.bodyMd.copy(color = colors.textSecondary)
                )

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    VetraGhostButton(onClick = {}) {
                        Text("Ghost")
                    }
                    VetraGhostButton(onClick = {}, enabled = false) {
                        Text("Disabled")
                    }
                }
            }
        }

        // Danger Buttons
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Danger Buttons",
                    style = typography.headingLg.copy(color = colors.textPrimary)
                )
                Text(
                    "For destructive actions that need caution.",
                    style = typography.bodyMd.copy(color = colors.textSecondary)
                )

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    VetraDangerButton(onClick = {}) {
                        Text("Delete")
                    }
                    VetraDangerButton(onClick = {}, enabled = false) {
                        Text("Disabled")
                    }
                }

                VetraDangerButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Delete Account")
                }
            }
        }

        // Icon Buttons
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Icon Buttons",
                    style = typography.headingLg.copy(color = colors.textPrimary)
                )
                Text(
                    "Three variants for different emphasis levels.",
                    style = typography.bodyMd.copy(color = colors.textSecondary)
                )

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    VetraIconButton(
                        onClick = {},
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Standard"
                    )
                    VetraFilledIconButton(
                        onClick = {},
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Filled"
                    )
                    VetraOutlinedIconButton(
                        onClick = {},
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Outlined"
                    )
                }
            }
        }
    }
}

