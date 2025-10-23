package com.flyfishxu.vetraui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TouchApp
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
import com.flyfishxu.vetraui.core.components.VetraBrandCard
import com.flyfishxu.vetraui.core.components.VetraCard
import com.flyfishxu.vetraui.core.components.VetraElevatedCard
import com.flyfishxu.vetraui.core.components.VetraFlatCard
import com.flyfishxu.vetraui.core.components.VetraOutlinedCard
import com.flyfishxu.vetraui.core.theme.VetraTheme

@Composable
fun CardsScreen() {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography
    var clickCount by remember { mutableStateOf(0) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    "Card Components",
                    style = typography.displaySm.copy(color = colors.textPrimary)
                )
                Text(
                    "Five card variants for different use cases",
                    style = typography.bodyLg.copy(color = colors.textSecondary)
                )
            }
        }

        // Standard Card
        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    "Standard Card",
                    style = typography.headingMd.copy(color = colors.textPrimary)
                )

                VetraCard(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = colors.brand
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        "Default Elevation",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "The standard card with soft shadow elevation. Perfect for most use cases.",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )
                }
            }
        }

        // Flat Card
        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    "Flat Card",
                    style = typography.headingMd.copy(color = colors.textPrimary)
                )

                VetraFlatCard(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "No Elevation",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Subtle background color without elevation. Good for nested cards or dense layouts.",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )
                }
            }
        }

        // Elevated Card
        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    "Elevated Card",
                    style = typography.headingMd.copy(color = colors.textPrimary)
                )

                VetraElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "Higher Elevation",
                                style = typography.headingMd.copy(color = colors.textPrimary)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                "More prominent shadow for emphasis.",
                                style = typography.bodyMd.copy(color = colors.textSecondary)
                            )
                        }
                        Icon(
                            imageVector = Icons.Filled.Layers,
                            contentDescription = null,
                            modifier = Modifier.size(32.dp),
                            tint = colors.accent
                        )
                    }
                }
            }
        }

        // Outlined Card
        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    "Outlined Card",
                    style = typography.headingMd.copy(color = colors.textPrimary)
                )

                VetraOutlinedCard(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "Border Style",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Clear boundaries with a border, no shadow. Perfect for minimalist designs.",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )
                }
            }
        }

        // Brand Card
        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    "Brand Card",
                    style = typography.headingMd.copy(color = colors.textPrimary)
                )

                VetraBrandCard(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        imageVector = Icons.Filled.Celebration,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = colors.brand
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        "Brand Accent",
                        style = typography.headingMd.copy(color = colors.onBrandSubtle)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Subtle brand color background for highlighting special content.",
                        style = typography.bodyMd.copy(color = colors.onBrandSubtle)
                    )
                }
            }
        }

        // Interactive Card
        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    "Interactive Card",
                    style = typography.headingMd.copy(color = colors.textPrimary)
                )

                VetraCard(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { clickCount++ }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "Clickable",
                                style = typography.headingMd.copy(color = colors.textPrimary)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                "Cards can be interactive. Clicked $clickCount times.",
                                style = typography.bodyMd.copy(color = colors.textSecondary)
                            )
                        }
                        Icon(
                            imageVector = Icons.Filled.TouchApp,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = colors.brand
                        )
                    }
                }
            }
        }
    }
}

