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
import com.flyfishxu.vetraui.core.VetraBrandCard
import com.flyfishxu.vetraui.core.VetraCard
import com.flyfishxu.vetraui.core.VetraElevatedCard
import com.flyfishxu.vetraui.core.VetraFlatCard
import com.flyfishxu.vetraui.core.VetraOutlinedCard
import com.flyfishxu.vetraui.core.theme.VetraTheme

@Composable
fun CardsScreen() {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography
    var clickCount by remember { mutableStateOf(0) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        // Standard Card
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Standard Card",
                    style = typography.headingLg.copy(color = colors.textPrimary)
                )
                Text(
                    "Soft shadow with subtle border. For most use cases.",
                    style = typography.bodyMd.copy(color = colors.textSecondary)
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
                        "Card Title",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "The default card with soft shadow elevation.",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )
                }
            }
        }

        // Elevated Card
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Elevated Card",
                    style = typography.headingLg.copy(color = colors.textPrimary)
                )
                Text(
                    "Strong shadow, no border. For emphasis.",
                    style = typography.bodyMd.copy(color = colors.textSecondary)
                )

                VetraElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "Featured Content",
                                style = typography.headingMd.copy(color = colors.textPrimary)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                "Higher elevation for important content.",
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
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Outlined Card",
                    style = typography.headingLg.copy(color = colors.textPrimary)
                )
                Text(
                    "Border only, no shadow. Minimalist style.",
                    style = typography.bodyMd.copy(color = colors.textSecondary)
                )

                VetraOutlinedCard(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "Bordered Card",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Clear boundaries with visible border.",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )
                }
            }
        }

        // Flat Card
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Flat Card",
                    style = typography.headingLg.copy(color = colors.textPrimary)
                )
                Text(
                    "No shadow or border. Most subtle variant.",
                    style = typography.bodyMd.copy(color = colors.textSecondary)
                )

                VetraFlatCard(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "Flat Design",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Simple background color only.",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )
                }
            }
        }

        // Brand Card
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Brand Card",
                    style = typography.headingLg.copy(color = colors.textPrimary)
                )
                Text(
                    "Brand color background for special content.",
                    style = typography.bodyMd.copy(color = colors.textSecondary)
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
                        "Special Highlight",
                        style = typography.headingMd.copy(color = colors.onBrandSubtle)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Draws attention with brand color.",
                        style = typography.bodyMd.copy(color = colors.onBrandSubtle)
                    )
                }
            }
        }

        // Interactive Card
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Interactive Cards",
                    style = typography.headingLg.copy(color = colors.textPrimary)
                )
                Text(
                    "All cards can be clickable.",
                    style = typography.bodyMd.copy(color = colors.textSecondary)
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
                                "Clicked $clickCount times",
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

