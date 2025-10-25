package com.flyfishxu.vetraui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.*
import com.flyfishxu.vetraui.core.theme.VetraTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Badges and Chips Screen
 *
 * Demonstrates all badge and chip variants with interactive examples
 */
@Preview
@Composable
fun BadgesAndChipsScreen() {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.canvas),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    "Badges & Chips",
                    style = typography.displaySm.copy(color = colors.textPrimary)
                )
                Text(
                    "Compact labels and interactive tags for status, filters, and selections",
                    style = typography.bodyLg.copy(color = colors.textSecondary)
                )
            }
        }

        // ========================================
        // BADGES SECTION
        // ========================================

        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Badges",
                    style = typography.headingLg.copy(color = colors.textPrimary)
                )
                Text(
                    "Small labels for status indicators, counts, and categories",
                    style = typography.bodyMd.copy(color = colors.textSecondary)
                )
            }
        }

        // Standard Badges
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        "Solid Badges",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Vibrant colors for clear status indication",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        VetraBadge("New")
                        VetraBadgeSecondary("Featured")
                        VetraBadgeSuccess("Active")
                        VetraBadgeWarning("Pending")
                        VetraBadgeDanger("Error")
                        VetraBadgeInfo("Info")
                    }
                }
            }
        }

        // Count Badges
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        "Count Badges",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Perfect for displaying numbers and quantities",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        VetraBadge("1")
                        VetraBadge("12")
                        VetraBadge("99+")
                        VetraBadgeSuccess("5")
                        VetraBadgeDanger("3")
                    }
                }
            }
        }

        // Outlined Badges
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        "Outlined Badges",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Subtle variant for less emphasis",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        VetraBadgeOutlined("Beta")
                        VetraBadgeOutlined("v2.0")
                        VetraBadgeOutlined("Pro")
                        VetraBadgeOutlined("Premium")
                    }
                }
            }
        }

        // Dot Badges
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        "Dot Badges",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Notification indicators and status dots",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.padding(top = 8.dp)) {
                        // Empty dots
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            VetraBadgeDot()
                            VetraBadgeDot(color = colors.success)
                            VetraBadgeDot(color = colors.warning)
                            VetraBadgeDot(color = colors.info)
                        }

                        // Dots with counts
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            VetraBadgeDot(count = 1)
                            VetraBadgeDot(count = 5)
                            VetraBadgeDot(count = 12)
                            VetraBadgeDot(count = 99)
                            VetraBadgeDot(count = 150)
                        }
                    }
                }
            }
        }

        // Badge Usage Examples
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        "Usage Examples",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Common use cases for badges",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.padding(top = 8.dp)) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Notifications",
                                style = typography.bodyMd.copy(color = colors.textPrimary),
                                modifier = Modifier.weight(1f)
                            )
                            VetraBadgeDot(count = 3)
                        }

                        VetraSubtleDivider()

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Server Status",
                                style = typography.bodyMd.copy(color = colors.textPrimary),
                                modifier = Modifier.weight(1f)
                            )
                            VetraBadgeSuccess("Online")
                        }

                        VetraSubtleDivider()

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "API Version",
                                style = typography.bodyMd.copy(color = colors.textPrimary),
                                modifier = Modifier.weight(1f)
                            )
                            VetraBadgeOutlined("v2.1.0")
                        }

                        VetraSubtleDivider()

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Account Type",
                                style = typography.bodyMd.copy(color = colors.textPrimary),
                                modifier = Modifier.weight(1f)
                            )
                            VetraBadgeSecondary("Premium")
                        }
                    }
                }
            }
        }

        // ========================================
        // CHIPS SECTION
        // ========================================

        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Chips",
                    style = typography.headingLg.copy(color = colors.textPrimary)
                )
                Text(
                    "Interactive tags for filters, selections, and actions",
                    style = typography.bodyMd.copy(color = colors.textSecondary)
                )
            }
        }

        // Standard Chips
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        "Standard Chips",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Basic chips with optional icons and actions",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(top = 8.dp)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            VetraChip(label = "Simple", onClick = {})
                            VetraChip(label = "Clickable", onClick = {})
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            VetraChip(label = "With Icon", leadingIcon = Icons.Default.Star, onClick = {})
                            VetraChip(
                                label = "Removable",
                                trailingIcon = Icons.Default.Close,
                                onTrailingIconClick = {},
                                onClick = {}
                            )
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            VetraChip(label = "Disabled", enabled = false)
                        }
                    }
                }
            }
        }

        // Outlined Chips
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        "Outlined Chips",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Subtle variant with border only",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        VetraChipOutlined(label = "Kotlin", onClick = {})
                        VetraChipOutlined(label = "Compose", leadingIcon = Icons.Default.Star, onClick = {})
                        VetraChipOutlined(
                            label = "Android",
                            trailingIcon = Icons.Default.Close,
                            onTrailingIconClick = {},
                            onClick = {}
                        )
                    }
                }
            }
        }

        // Elevated Chips
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        "Elevated Chips",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Prominent chips with elevation",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        VetraChipElevated(label = "Elevated", onClick = {})
                        VetraChipElevated(label = "Featured", leadingIcon = Icons.Default.Star, onClick = {})
                        VetraChipElevated(
                            label = "Premium",
                            trailingIcon = Icons.Default.Close,
                            onTrailingIconClick = {},
                            onClick = {}
                        )
                    }
                }
            }
        }

        // Assist Chips
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        "Assist Chips",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Action suggestions and quick shortcuts",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        VetraChipAssist(label = "Add to cart", onClick = {}, leadingIcon = Icons.Default.Add)
                        VetraChipAssist(label = "Share", onClick = {})
                        VetraChipAssist(label = "Bookmark", onClick = {}, leadingIcon = Icons.Default.Star)
                    }
                }
            }
        }

        // Filter Chips
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        "Filter Chips",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Toggleable chips for filtering and selection",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )

                    var selectedFilters by remember { mutableStateOf(setOf("All")) }

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(top = 8.dp)) {
                        Text(
                            "Status Filter:",
                            style = typography.bodySm.copy(color = colors.textSecondary)
                        )

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            VetraChipFilter(
                                label = "All",
                                selected = "All" in selectedFilters,
                                onClick = {
                                    selectedFilters = if ("All" in selectedFilters) {
                                        emptySet()
                                    } else {
                                        setOf("All")
                                    }
                                }
                            )
                            VetraChipFilter(
                                label = "Active",
                                selected = "Active" in selectedFilters,
                                onClick = {
                                    selectedFilters = if ("Active" in selectedFilters) {
                                        selectedFilters - "Active"
                                    } else {
                                        selectedFilters + "Active"
                                    }
                                },
                                leadingIcon = Icons.Default.Star
                            )
                            VetraChipFilter(
                                label = "Completed",
                                selected = "Completed" in selectedFilters,
                                onClick = {
                                    selectedFilters = if ("Completed" in selectedFilters) {
                                        selectedFilters - "Completed"
                                    } else {
                                        selectedFilters + "Completed"
                                    }
                                }
                            )
                            VetraChipFilter(
                                label = "Archived",
                                selected = "Archived" in selectedFilters,
                                onClick = {
                                    selectedFilters = if ("Archived" in selectedFilters) {
                                        selectedFilters - "Archived"
                                    } else {
                                        selectedFilters + "Archived"
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }

        // Interactive Example: Tag Selection
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        "Interactive Example",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Select and remove tags dynamically",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )

                    var selectedTags by remember {
                        mutableStateOf(listOf("Design", "Development", "UI/UX"))
                    }

                    Column(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.padding(top = 8.dp)) {
                        Text(
                            "Selected Tags (${selectedTags.size}):",
                            style = typography.bodySm.copy(color = colors.textSecondary)
                        )

                        if (selectedTags.isNotEmpty()) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState())
                            ) {
                                selectedTags.forEach { tag ->
                                    VetraChip(
                                        label = tag,
                                        trailingIcon = Icons.Default.Close,
                                        onTrailingIconClick = {
                                            selectedTags = selectedTags - tag
                                        }
                                    )
                                }
                            }
                        } else {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(colors.canvasSubtle, VetraTheme.shapes.xs)
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    "No tags selected",
                                    style = typography.bodyMd.copy(color = colors.textTertiary)
                                )
                            }
                        }

                        VetraSubtleDivider()

                        Text(
                            "Add Tags:",
                            style = typography.bodySm.copy(color = colors.textSecondary)
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState())
                        ) {
                            val availableTags = listOf(
                                "Design", "Development", "UI/UX", "Mobile",
                                "Web", "Backend", "Frontend", "Testing"
                            )
                            availableTags.filter { it !in selectedTags }.forEach { tag ->
                                VetraChipAssist(
                                    label = tag,
                                    onClick = {
                                        selectedTags = selectedTags + tag
                                    },
                                    leadingIcon = Icons.Default.Add
                                )
                            }
                        }
                    }
                }
            }
        }

        // Comparison
        item {
            VetraOutlinedCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        "When to Use",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )

                    VetraSubtleDivider()

                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        // Badges
                        Row(verticalAlignment = Alignment.Top) {
                            VetraBadge("Badges")
                            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                            Text(
                                "For status indicators, counts, labels, and notifications. Non-interactive.",
                                style = typography.bodyMd.copy(color = colors.textSecondary),
                                modifier = Modifier.weight(1f)
                            )
                        }

                        VetraSubtleDivider()

                        // Chips
                        Row(verticalAlignment = Alignment.Top) {
                            VetraChip(label = "Chips")
                            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                            Text(
                                "For interactive tags, filters, selections, and removable items. User actions.",
                                style = typography.bodyMd.copy(color = colors.textSecondary),
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }
    }
}

