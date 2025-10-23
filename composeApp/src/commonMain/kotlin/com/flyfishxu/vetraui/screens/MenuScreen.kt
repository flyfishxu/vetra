package com.flyfishxu.vetraui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Archive
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material.icons.outlined.Block
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material.icons.outlined.ContentCut
import androidx.compose.material.icons.outlined.ContentPaste
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.FolderOpen
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Print
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Upload
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.components.VetraButton
import com.flyfishxu.vetraui.core.components.VetraCard
import com.flyfishxu.vetraui.core.components.VetraContextMenu
import com.flyfishxu.vetraui.core.components.VetraDropdownMenu
import com.flyfishxu.vetraui.core.components.VetraIconMenuButton
import com.flyfishxu.vetraui.core.components.VetraMenuButton
import com.flyfishxu.vetraui.core.components.VetraMenuDivider
import com.flyfishxu.vetraui.core.components.VetraMenuItem
import com.flyfishxu.vetraui.core.components.VetraMenuLabel
import com.flyfishxu.vetraui.core.theme.VetraTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MenuScreen() {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography

    var selectedAction by remember { mutableStateOf("No action selected") }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    "Menu Components",
                    style = typography.displaySm.copy(color = colors.textPrimary)
                )
                Text(
                    "Elegant dropdown menus and context menus with smooth animations",
                    style = typography.bodyLg.copy(color = colors.textSecondary)
                )
            }
        }

        // Action feedback card
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        "Last Action",
                        style = typography.labelLg.copy(color = colors.textSecondary)
                    )
                    Text(
                        selectedAction,
                        style = typography.bodyMd.copy(color = colors.textPrimary)
                    )
                }
            }
        }

        // Menu Button
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        "Menu Button",
                        style = typography.headingLg.copy(color = colors.textPrimary)
                    )
                    Text(
                        "A button that opens a dropdown menu when clicked",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        VetraMenuButton(
                            text = "File",
                            leadingIcon = Icons.Outlined.Description
                        ) {
                            VetraMenuItem(
                                text = "New File",
                                onClick = { selectedAction = "New File" },
                                leadingIcon = Icons.Outlined.Add
                            )
                            VetraMenuItem(
                                text = "Open",
                                onClick = { selectedAction = "Open" },
                                leadingIcon = Icons.Outlined.FolderOpen
                            )
                            VetraMenuItem(
                                text = "Save",
                                onClick = { selectedAction = "Save" },
                                leadingIcon = Icons.Outlined.Save
                            )
                            VetraMenuDivider()
                            VetraMenuItem(
                                text = "Export",
                                onClick = { selectedAction = "Export" },
                                leadingIcon = Icons.Outlined.Upload
                            )
                        }

                        VetraMenuButton(text = "Edit") {
                            VetraMenuItem(
                                text = "Cut",
                                onClick = { selectedAction = "Cut" },
                                leadingIcon = Icons.Outlined.ContentCut
                            )
                            VetraMenuItem(
                                text = "Copy",
                                onClick = { selectedAction = "Copy" },
                                leadingIcon = Icons.Outlined.ContentCopy
                            )
                            VetraMenuItem(
                                text = "Paste",
                                onClick = { selectedAction = "Paste" },
                                leadingIcon = Icons.Outlined.ContentPaste
                            )
                        }
                    }
                }
            }
        }

        // Icon Menu Button
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        "Icon Menu Button",
                        style = typography.headingLg.copy(color = colors.textPrimary)
                    )
                    Text(
                        "An icon button that opens a dropdown menu",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        VetraIconMenuButton(
                            icon = Icons.Filled.MoreVert,
                            contentDescription = "More options"
                        ) {
                            VetraMenuItem(
                                text = "Settings",
                                onClick = { selectedAction = "Settings" },
                                leadingIcon = Icons.Outlined.Settings
                            )
                            VetraMenuItem(
                                text = "Profile",
                                onClick = { selectedAction = "Profile" },
                                leadingIcon = Icons.Outlined.Person
                            )
                            VetraMenuDivider()
                            VetraMenuItem(
                                text = "Logout",
                                onClick = { selectedAction = "Logout" },
                                leadingIcon = Icons.AutoMirrored.Outlined.Logout,
                                contentColor = colors.danger
                            )
                        }

                        VetraIconMenuButton(
                            icon = Icons.Filled.Share,
                            contentDescription = "Share"
                        ) {
                            VetraMenuLabel("Share to")
                            VetraMenuItem(
                                text = "Email",
                                onClick = { selectedAction = "Share via Email" },
                                leadingIcon = Icons.Outlined.Email
                            )
                            VetraMenuItem(
                                text = "Link",
                                onClick = { selectedAction = "Copy Link" },
                                leadingIcon = Icons.Outlined.Link
                            )
                            VetraMenuItem(
                                text = "Social Media",
                                onClick = { selectedAction = "Share to Social" },
                                leadingIcon = Icons.Outlined.Share
                            )
                        }

                        VetraIconMenuButton(
                            icon = Icons.Filled.FilterList,
                            contentDescription = "Filter"
                        ) {
                            VetraMenuLabel("Sort by")
                            VetraMenuItem(
                                text = "Name",
                                onClick = { selectedAction = "Sort by Name" },
                                trailingIcon = Icons.Outlined.ArrowUpward
                            )
                            VetraMenuItem(
                                text = "Date",
                                onClick = { selectedAction = "Sort by Date" },
                                trailingIcon = Icons.Outlined.ArrowUpward
                            )
                            VetraMenuItem(
                                text = "Size",
                                onClick = { selectedAction = "Sort by Size" },
                                trailingIcon = Icons.Outlined.ArrowUpward
                            )
                            VetraMenuDivider()
                            VetraMenuLabel("View")
                            VetraMenuItem(
                                text = "Grid",
                                onClick = { selectedAction = "Grid View" },
                                leadingIcon = Icons.Outlined.GridView
                            )
                            VetraMenuItem(
                                text = "List",
                                onClick = { selectedAction = "List View" },
                                leadingIcon = Icons.AutoMirrored.Outlined.List
                            )
                        }
                    }
                }
            }
        }

        // Manual Dropdown Menu
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        "Custom Dropdown Menu",
                        style = typography.headingLg.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Manually controlled dropdown menu with custom trigger",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )

                    var expanded by remember { mutableStateOf(false) }

                    Box {
                        VetraButton(
                            onClick = { expanded = !expanded }
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Choose Action")
                                Icon(
                                    imageVector = if (expanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }

                        VetraDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            VetraMenuItem(
                                text = "Download",
                                onClick = {
                                    selectedAction = "Download"
                                    expanded = false
                                },
                                leadingIcon = Icons.Outlined.Download
                            )
                            VetraMenuItem(
                                text = "Print",
                                onClick = {
                                    selectedAction = "Print"
                                    expanded = false
                                },
                                leadingIcon = Icons.Outlined.Print
                            )
                            VetraMenuItem(
                                text = "Archive",
                                onClick = {
                                    selectedAction = "Archive"
                                    expanded = false
                                },
                                leadingIcon = Icons.Outlined.Archive
                            )
                            VetraMenuDivider()
                            VetraMenuItem(
                                text = "Delete",
                                onClick = {
                                    selectedAction = "Delete"
                                    expanded = false
                                },
                                leadingIcon = Icons.Outlined.Delete,
                                contentColor = colors.danger
                            )
                        }
                    }
                }
            }
        }

        // Context Menu Demo
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        "Context Menu",
                        style = typography.headingLg.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Right-click or long-press to open context menu",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )

                    var contextMenuExpanded by remember { mutableStateOf(false) }

                    Box {
                        VetraCard(
                            onClick = { contextMenuExpanded = true },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Image,
                                    contentDescription = null,
                                    modifier = Modifier.size(48.dp),
                                    tint = colors.brand
                                )
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        "Sample Image.jpg",
                                        style = typography.bodyLg.copy(color = colors.textPrimary)
                                    )
                                    Text(
                                        "Click to open context menu",
                                        style = typography.bodySm.copy(color = colors.textSecondary)
                                    )
                                }
                            }
                        }

                        VetraContextMenu(
                            expanded = contextMenuExpanded,
                            onDismissRequest = { contextMenuExpanded = false }
                        ) {
                            VetraMenuItem(
                                text = "Open",
                                onClick = {
                                    selectedAction = "Open Image"
                                    contextMenuExpanded = false
                                },
                                leadingIcon = Icons.AutoMirrored.Outlined.OpenInNew
                            )
                            VetraMenuItem(
                                text = "Rename",
                                onClick = {
                                    selectedAction = "Rename Image"
                                    contextMenuExpanded = false
                                },
                                leadingIcon = Icons.Outlined.Edit
                            )
                            VetraMenuItem(
                                text = "Copy",
                                onClick = {
                                    selectedAction = "Copy Image"
                                    contextMenuExpanded = false
                                },
                                leadingIcon = Icons.Outlined.ContentCopy
                            )
                            VetraMenuDivider()
                            VetraMenuItem(
                                text = "Move to Trash",
                                onClick = {
                                    selectedAction = "Delete Image"
                                    contextMenuExpanded = false
                                },
                                leadingIcon = Icons.Outlined.Delete,
                                contentColor = colors.danger
                            )
                        }
                    }
                }
            }
        }

        // Disabled Items
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        "Menu with Disabled Items",
                        style = typography.headingLg.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Some menu items can be disabled",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )

                    VetraMenuButton(text = "Options") {
                        VetraMenuItem(
                            text = "Enabled Item",
                            onClick = { selectedAction = "Enabled Item" },
                            leadingIcon = Icons.Outlined.CheckCircle
                        )
                        VetraMenuItem(
                            text = "Disabled Item",
                            onClick = { selectedAction = "This should not trigger" },
                            leadingIcon = Icons.Outlined.Block,
                            enabled = false
                        )
                        VetraMenuDivider()
                        VetraMenuItem(
                            text = "Another Enabled",
                            onClick = { selectedAction = "Another Enabled" },
                            leadingIcon = Icons.Outlined.CheckCircle
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MenuScreenPreview() {
    VetraTheme {
        MenuScreen()
    }
}

@Preview
@Composable
fun MenuScreenDarkPreview() {
    VetraTheme(darkMode = true) {
        MenuScreen()
    }
}

