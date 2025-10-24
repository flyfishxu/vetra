package com.flyfishxu.vetraui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Label
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.*
import com.flyfishxu.vetraui.core.theme.VetraTheme
import com.flyfishxu.vetraui.navigation.Destination

/**
 * Components Gallery Screen
 *
 * A centralized screen showcasing all UI components in Vetra UI
 */
@Composable
fun ComponentsGalleryScreen(
    onNavigateToDetail: (Destination) -> Unit
) {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography

    // Main gallery view
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
                    "Component Library",
                    style = typography.displayMd.copy(color = colors.textPrimary)
                )
                Text(
                    "Explore all available UI components",
                    style = typography.bodyLg.copy(color = colors.textSecondary)
                )
            }
        }

        // Buttons
        item {
            ComponentCategoryCard(
                title = "Buttons",
                description = "5 variants: Primary, Secondary, Outlined, Ghost, and Danger",
                icon = Icons.Filled.TouchApp,
                iconColor = colors.brand,
                onClick = { onNavigateToDetail(Destination.ButtonsDetail) }
            )
        }

        // Cards
        item {
            ComponentCategoryCard(
                title = "Cards",
                description = "5 card styles: Standard, Flat, Elevated, Outlined, and Brand",
                icon = Icons.Filled.GridView,
                iconColor = colors.accent,
                onClick = { onNavigateToDetail(Destination.CardsDetail) }
            )
        }

        // Inputs
        item {
            ComponentCategoryCard(
                title = "Input Components",
                description = "Text fields, switches, checkboxes, and radio buttons",
                icon = Icons.Filled.Edit,
                iconColor = colors.info,
                onClick = { onNavigateToDetail(Destination.InputsDetail) }
            )
        }

        // Badges & Chips
        item {
            ComponentCategoryCard(
                title = "Badges & Chips",
                description = "Status badges, notification dots, and interactive tags",
                icon = Icons.AutoMirrored.Filled.Label,
                iconColor = colors.warning,
                onClick = { onNavigateToDetail(Destination.BadgesAndChipsDetail) }
            )
        }

        // Sliders & Dividers
        item {
            ComponentCategoryCard(
                title = "Sliders & Dividers",
                description = "Value selection sliders and visual content separators",
                icon = Icons.Filled.Tune,
                iconColor = colors.success,
                onClick = { onNavigateToDetail(Destination.SlidersDetail) }
            )
        }

        // Menus
        item {
            ComponentCategoryCard(
                title = "Menus",
                description = "Dropdown menus and contextual options",
                icon = Icons.Filled.Menu,
                iconColor = colors.brand,
                onClick = { onNavigateToDetail(Destination.MenuDetail) }
            )
        }

        // Loading
        item {
            ComponentCategoryCard(
                title = "Loading Indicators",
                description = "Spinners, progress bars, and skeleton screens",
                icon = Icons.Filled.HourglassBottom,
                iconColor = colors.accent,
                onClick = { onNavigateToDetail(Destination.LoadingDetail) }
            )
        }

        // Dialogs
        item {
            ComponentCategoryCard(
                title = "Dialogs",
                description = "Modal overlays with elegant animations for important interactions",
                icon = Icons.Filled.QuestionAnswer,
                iconColor = colors.info,
                onClick = { onNavigateToDetail(Destination.DialogsDetail) }
            )
        }

        // Quick Stats
        item {
            VetraOutlinedCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        "Statistics",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )

                    VetraSubtleDivider()

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        StatItem(
                            value = "40+",
                            label = "Components",
                            color = colors.brand
                        )

                        VetraDivider(
                            orientation = DividerOrientation.Vertical,
                            modifier = Modifier
                                .height(60.dp)
                                .padding(vertical = 8.dp)
                        )

                        StatItem(
                            value = "8",
                            label = "Categories",
                            color = colors.accent
                        )

                        VetraDivider(
                            orientation = DividerOrientation.Vertical,
                            modifier = Modifier
                                .height(60.dp)
                                .padding(vertical = 8.dp)
                        )

                        StatItem(
                            value = "100%",
                            label = "Customizable",
                            color = colors.success
                        )
                    }
                }
            }
        }

        // Bottom spacing
        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

/**
 * Component Category Card
 */
@Composable
private fun ComponentCategoryCard(
    title: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconColor: androidx.compose.ui.graphics.Color,
    onClick: () -> Unit
) {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography

    VetraCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(
                        iconColor.copy(alpha = 0.1f),
                        VetraTheme.shapes.md
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    tint = iconColor
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    style = typography.headingMd.copy(color = colors.textPrimary)
                )
                Text(
                    text = description,
                    style = typography.bodySm.copy(color = colors.textSecondary)
                )
            }

            Icon(
                imageVector = Icons.Filled.ChevronRight,
                contentDescription = null,
                tint = colors.textTertiary
            )
        }
    }
}

/**
 * Stat Item
 */
@Composable
private fun StatItem(
    value: String,
    label: String,
    color: androidx.compose.ui.graphics.Color
) {
    val typography = VetraTheme.typography
    val colors = VetraTheme.colors

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = value,
            style = typography.displaySm.copy(color = color)
        )
        Text(
            text = label,
            style = typography.bodySm.copy(color = colors.textSecondary)
        )
    }
}

