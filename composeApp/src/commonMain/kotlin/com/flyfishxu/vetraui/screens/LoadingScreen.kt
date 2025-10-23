package com.flyfishxu.vetraui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.LoadingSize
import com.flyfishxu.vetraui.core.VetraCard
import com.flyfishxu.vetraui.core.VetraLinearProgress
import com.flyfishxu.vetraui.core.VetraLoadingDots
import com.flyfishxu.vetraui.core.VetraLoadingPulse
import com.flyfishxu.vetraui.core.VetraLoadingSpinner
import com.flyfishxu.vetraui.core.VetraSkeletonButton
import com.flyfishxu.vetraui.core.VetraSkeletonCard
import com.flyfishxu.vetraui.core.VetraSkeletonListItem
import com.flyfishxu.vetraui.core.VetraSkeletonText
import com.flyfishxu.vetraui.core.theme.VetraTheme

@Composable
fun LoadingScreen() {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    "Loading Components",
                    style = typography.displaySm.copy(color = colors.textPrimary)
                )
                Text(
                    "Elegant loading indicators and skeleton loaders",
                    style = typography.bodyLg.copy(color = colors.textSecondary)
                )
            }
        }

        // Spinner
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        "Loading Spinner",
                        style = typography.headingLg.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Circular spinner with smooth rotation animation",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            VetraLoadingSpinner(size = LoadingSize.SMALL)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                "Small",
                                style = typography.bodySm.copy(color = colors.textSecondary)
                            )
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            VetraLoadingSpinner(size = LoadingSize.MEDIUM)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                "Medium",
                                style = typography.bodySm.copy(color = colors.textSecondary)
                            )
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            VetraLoadingSpinner(size = LoadingSize.LARGE)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                "Large",
                                style = typography.bodySm.copy(color = colors.textSecondary)
                            )
                        }
                    }

                    // Colors
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        VetraLoadingSpinner(color = colors.brand)
                        VetraLoadingSpinner(color = colors.accent)
                        VetraLoadingSpinner(color = colors.success)
                        VetraLoadingSpinner(color = colors.warning)
                    }
                }
            }
        }

        // Dots
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        "Loading Dots",
                        style = typography.headingLg.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Bouncing dots with sequential animation",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        VetraLoadingDots()
                        VetraLoadingDots(color = colors.accent)
                        VetraLoadingDots(dotSize = 12.dp, spacing = 12.dp)
                    }
                }
            }
        }

        // Pulse
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        "Loading Pulse",
                        style = typography.headingLg.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Concentric circles with pulse animation",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        VetraLoadingPulse(size = LoadingSize.SMALL)
                        VetraLoadingPulse(size = LoadingSize.MEDIUM)
                        VetraLoadingPulse(size = LoadingSize.LARGE)
                    }
                }
            }
        }

        // Linear Progress
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        "Linear Progress",
                        style = typography.headingLg.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Progress bars for determinate and indeterminate loading",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text(
                            "Indeterminate",
                            style = typography.bodyMd.copy(color = colors.textSecondary)
                        )
                        VetraLinearProgress(modifier = Modifier.fillMaxWidth())

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            "Determinate",
                            style = typography.bodyMd.copy(color = colors.textSecondary)
                        )
                        VetraLinearProgress(
                            modifier = Modifier.fillMaxWidth(),
                            progress = 0.3f
                        )
                        VetraLinearProgress(
                            modifier = Modifier.fillMaxWidth(),
                            progress = 0.7f
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            "Custom Colors",
                            style = typography.bodyMd.copy(color = colors.textSecondary)
                        )
                        VetraLinearProgress(
                            modifier = Modifier.fillMaxWidth(),
                            color = colors.success,
                            progress = 0.5f
                        )
                        VetraLinearProgress(
                            modifier = Modifier.fillMaxWidth(),
                            color = colors.warning,
                            progress = 0.8f
                        )
                    }
                }
            }
        }

        // Skeleton Loaders
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Skeleton Loaders",
                    style = typography.headingLg.copy(color = colors.textPrimary)
                )
                Text(
                    "Shimmer effects for content placeholders",
                    style = typography.bodyMd.copy(color = colors.textSecondary)
                )

                VetraCard(modifier = Modifier.fillMaxWidth()) {
                    VetraSkeletonListItem(
                        modifier = Modifier.fillMaxWidth(),
                        showAvatar = true,
                        linesCount = 2
                    )
                }

                VetraCard(modifier = Modifier.fillMaxWidth()) {
                    VetraSkeletonCard(
                        modifier = Modifier.fillMaxWidth(),
                        showImage = true,
                        imageHeight = 150.dp,
                        linesCount = 3
                    )
                }

                VetraCard(modifier = Modifier.fillMaxWidth()) {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        VetraSkeletonText()
                        VetraSkeletonText(width = 200.dp)
                        VetraSkeletonText(width = 150.dp)

                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            VetraSkeletonButton()
                            VetraSkeletonButton(width = 80.dp)
                        }
                    }
                }
            }
        }
    }
}

