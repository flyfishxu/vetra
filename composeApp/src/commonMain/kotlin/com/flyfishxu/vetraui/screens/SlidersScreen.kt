package com.flyfishxu.vetraui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.DividerOrientation
import com.flyfishxu.vetraui.core.VetraBrandDivider
import com.flyfishxu.vetraui.core.VetraCard
import com.flyfishxu.vetraui.core.VetraDivider
import com.flyfishxu.vetraui.core.VetraDividerWithText
import com.flyfishxu.vetraui.core.VetraInsetDivider
import com.flyfishxu.vetraui.core.VetraRangeSlider
import com.flyfishxu.vetraui.core.VetraSliderWithLabel
import com.flyfishxu.vetraui.core.VetraStrongDivider
import com.flyfishxu.vetraui.core.VetraSubtleDivider
import com.flyfishxu.vetraui.core.theme.VetraTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Sliders & Dividers Screen
 *
 * Demonstrates all slider and divider variants in Vetra UI
 */
@Preview
@Composable
fun SlidersScreen() {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography

    var sliderValue1 by remember { mutableStateOf(0.5f) }
    var sliderValue2 by remember { mutableStateOf(50f) }
    var sliderValue3 by remember { mutableStateOf(75f) }
    var sliderValue4 by remember { mutableStateOf(3f) }
    var rangeSliderValues by remember { mutableStateOf(0.3f..0.7f) }
    var priceRange by remember { mutableStateOf(20f..80f) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.canvas),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        // Section: Sliders
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    "Sliders",
                    style = typography.displaySm.copy(color = colors.textPrimary)
                )
                Text(
                    "Select values from continuous or discrete ranges",
                    style = typography.bodyMd.copy(color = colors.textSecondary)
                )
            }
        }

        // Continuous Slider
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        "Continuous Slider",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Smooth value selection with no discrete steps",
                        style = typography.bodySm.copy(color = colors.textSecondary)
                    )

                    VetraDivider()

                    VetraSliderWithLabel(
                        value = sliderValue1,
                        onValueChange = { sliderValue1 = it },
                        label = "Progress",
                        valueFormatter = { "${(it * 100).toInt()}%" }
                    )
                }
            }
        }

        // Slider with Custom Range
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        "Custom Range Slider",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Define your own value range (0-100)",
                        style = typography.bodySm.copy(color = colors.textSecondary)
                    )

                    VetraSubtleDivider()

                    VetraSliderWithLabel(
                        value = sliderValue2,
                        onValueChange = { sliderValue2 = it },
                        label = "Volume",
                        valueRange = 0f..100f,
                        valueFormatter = { "${it.toInt()}" }
                    )
                }
            }
        }

        // Discrete Slider
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        "Discrete Slider",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Snap to specific step values (5 steps)",
                        style = typography.bodySm.copy(color = colors.textSecondary)
                    )

                    VetraBrandDivider()

                    VetraSliderWithLabel(
                        value = sliderValue4,
                        onValueChange = { sliderValue4 = it },
                        label = "Rating",
                        valueRange = 1f..5f,
                        steps = 3,
                        valueFormatter = { "⭐".repeat(it.toInt()) }
                    )
                }
            }
        }

        // Range Slider
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        "Range Slider",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Select a range with start and end values",
                        style = typography.bodySm.copy(color = colors.textSecondary)
                    )

                    VetraStrongDivider()

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "Time Range",
                                style = typography.labelLg.copy(color = colors.textPrimary)
                            )
                            Text(
                                "${(rangeSliderValues.start * 100).toInt()}% - ${(rangeSliderValues.endInclusive * 100).toInt()}%",
                                style = typography.labelLg.copy(color = colors.brand)
                            )
                        }

                        VetraRangeSlider(
                            values = rangeSliderValues,
                            onValuesChange = { rangeSliderValues = it }
                        )
                    }
                }
            }
        }

        // Price Range Example
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        "Price Filter",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Common use case: filtering by price range",
                        style = typography.bodySm.copy(color = colors.textSecondary)
                    )

                    VetraInsetDivider(startPadding = 0.dp)

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "Price Range",
                                style = typography.labelLg.copy(color = colors.textPrimary)
                            )
                            Text(
                                "$${priceRange.start.toInt()} - $${priceRange.endInclusive.toInt()}",
                                style = typography.labelLg.copy(color = colors.accent)
                            )
                        }

                        VetraRangeSlider(
                            values = priceRange,
                            onValuesChange = { priceRange = it },
                            valueRange = 0f..200f,
                            steps = 18
                        )
                    }
                }
            }
        }

        // Disabled State
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        "Disabled State",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Shows read-only values",
                        style = typography.bodySm.copy(color = colors.textSecondary)
                    )

                    VetraDivider()

                    VetraSliderWithLabel(
                        value = sliderValue3,
                        onValueChange = { },
                        label = "Battery Level",
                        enabled = false,
                        valueRange = 0f..100f,
                        valueFormatter = { "${it.toInt()}%" }
                    )
                }
            }
        }

        // Section: Dividers
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    "Dividers",
                    style = typography.displaySm.copy(color = colors.textPrimary)
                )
                Text(
                    "Visual separators for content organization",
                    style = typography.bodyMd.copy(color = colors.textSecondary)
                )
            }
        }

        // Divider Variants
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                    Text(
                        "Divider Variants",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            "Standard Divider",
                            style = typography.labelLg.copy(color = colors.textSecondary)
                        )
                        VetraDivider()
                    }

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            "Subtle Divider",
                            style = typography.labelLg.copy(color = colors.textSecondary)
                        )
                        VetraSubtleDivider()
                    }

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            "Strong Divider",
                            style = typography.labelLg.copy(color = colors.textSecondary)
                        )
                        VetraStrongDivider()
                    }

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            "Brand Divider",
                            style = typography.labelLg.copy(color = colors.textSecondary)
                        )
                        VetraBrandDivider()
                    }

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            "Inset Divider",
                            style = typography.labelLg.copy(color = colors.textSecondary)
                        )
                        VetraInsetDivider(startPadding = 48.dp)
                    }
                }
            }
        }

        // Divider with Text
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        "Divider with Text",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Useful for section headers and category separators",
                        style = typography.bodySm.copy(color = colors.textSecondary)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    VetraDividerWithText(text = "OR")

                    Spacer(modifier = Modifier.height(8.dp))

                    VetraDividerWithText(text = "SECTION 1")
                }
            }
        }

        // Vertical Dividers
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        "Vertical Dividers",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Separate content horizontally",
                        style = typography.bodySm.copy(color = colors.textSecondary)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                "34",
                                style = typography.displaySm.copy(color = colors.brand)
                            )
                            Text(
                                "Projects",
                                style = typography.bodySm.copy(color = colors.textSecondary)
                            )
                        }

                        VetraDivider(
                            orientation = DividerOrientation.Vertical,
                            modifier = Modifier.padding(vertical = 12.dp)
                        )

                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                "128",
                                style = typography.displaySm.copy(color = colors.accent)
                            )
                            Text(
                                "Tasks",
                                style = typography.bodySm.copy(color = colors.textSecondary)
                            )
                        }

                        VetraDivider(
                            orientation = DividerOrientation.Vertical,
                            modifier = Modifier.padding(vertical = 12.dp)
                        )

                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                "98%",
                                style = typography.displaySm.copy(color = colors.success)
                            )
                            Text(
                                "Complete",
                                style = typography.bodySm.copy(color = colors.textSecondary)
                            )
                        }
                    }
                }
            }
        }

        // List Example with Dividers
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        "List with Inset Dividers",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Common pattern for list items",
                        style = typography.bodySm.copy(color = colors.textSecondary)
                    )

                    VetraSubtleDivider()

                    Column {
                        repeat(3) { index ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 12.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .background(
                                            when (index) {
                                                0 -> colors.brandSubtle
                                                1 -> colors.accentSubtle
                                                else -> colors.canvasSubtle
                                            },
                                            VetraTheme.shapes.sm
                                        )
                                )

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        "List Item ${index + 1}",
                                        style = typography.bodyMd.copy(color = colors.textPrimary)
                                    )
                                    Text(
                                        "Description for item ${index + 1}",
                                        style = typography.bodySm.copy(color = colors.textSecondary)
                                    )
                                }

                                Text(
                                    "${(index + 1) * 25}%",
                                    style = typography.labelMd.copy(color = colors.brand)
                                )
                            }

                            if (index < 2) {
                                VetraInsetDivider(startPadding = 52.dp)
                            }
                        }
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

