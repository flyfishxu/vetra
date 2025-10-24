package com.flyfishxu.vetraui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.DeleteForever
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.VetraBrandCard
import com.flyfishxu.vetraui.core.VetraButton
import com.flyfishxu.vetraui.core.VetraCard
import com.flyfishxu.vetraui.core.VetraDangerButton
import com.flyfishxu.vetraui.core.VetraOutlinedButton
import com.flyfishxu.vetraui.core.VetraSwitchWithLabel
import com.flyfishxu.vetraui.core.theme.VetraTheme
import com.flyfishxu.vetraui.theme.ThemeMode

@Composable
fun SettingsScreen(
    themeMode: ThemeMode,
    systemInDarkMode: Boolean,
    actualDarkMode: Boolean,
    onThemeModeChange: (ThemeMode) -> Unit
) {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography
    val uriHandler = LocalUriHandler.current

    var notificationsEnabled by remember { mutableStateOf(true) }
    var soundEnabled by remember { mutableStateOf(true) }
    var analyticsEnabled by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    "Settings",
                    style = typography.displaySm.copy(color = colors.textPrimary)
                )
                Text(
                    "Customize your experience",
                    style = typography.bodyLg.copy(color = colors.textSecondary)
                )
            }
        }

        // Appearance
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Palette,
                            contentDescription = null,
                            tint = colors.brand
                        )
                        Text(
                            "Appearance",
                            style = typography.headingMd.copy(color = colors.textPrimary)
                        )
                    }

                    // Theme mode options
                    ThemeModeOption(
                        selected = themeMode == ThemeMode.SYSTEM,
                        onClick = { onThemeModeChange(ThemeMode.SYSTEM) },
                        icon = Icons.Outlined.AutoAwesome,
                        title = "System Default",
                        description = "Follow system theme (currently ${if (systemInDarkMode) "dark" else "light"})"
                    )

                    ThemeModeOption(
                        selected = themeMode == ThemeMode.LIGHT,
                        onClick = { onThemeModeChange(ThemeMode.LIGHT) },
                        icon = Icons.Outlined.LightMode,
                        title = "Light Mode",
                        description = "Always use light theme"
                    )

                    ThemeModeOption(
                        selected = themeMode == ThemeMode.DARK,
                        onClick = { onThemeModeChange(ThemeMode.DARK) },
                        icon = Icons.Outlined.DarkMode,
                        title = "Dark Mode",
                        description = "Always use dark theme"
                    )

                    // Info banner
                    if (themeMode == ThemeMode.SYSTEM) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(VetraTheme.shapes.sm)
                                .background(colors.brandSubtle)
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Info,
                                contentDescription = null,
                                tint = colors.brand,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                "Theme automatically changes with your system settings",
                                style = typography.bodySm.copy(color = colors.onBrandSubtle)
                            )
                        }
                    }
                }
            }
        }

        // Notifications
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = null,
                            tint = colors.accent
                        )
                        Text(
                            "Notifications",
                            style = typography.headingMd.copy(color = colors.textPrimary)
                        )
                    }

                    VetraSwitchWithLabel(
                        checked = notificationsEnabled,
                        onCheckedChange = { notificationsEnabled = it },
                        label = "Enable Notifications"
                    )

                    VetraSwitchWithLabel(
                        checked = soundEnabled,
                        onCheckedChange = { soundEnabled = it },
                        label = "Sound Effects"
                    )
                }
            }
        }

        // Privacy
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Security,
                            contentDescription = null,
                            tint = colors.brand
                        )
                        Text(
                            "Privacy & Security",
                            style = typography.headingMd.copy(color = colors.textPrimary)
                        )
                    }

                    VetraSwitchWithLabel(
                        checked = analyticsEnabled,
                        onCheckedChange = { analyticsEnabled = it },
                        label = "Share Analytics"
                    )

                    Text(
                        "Help us improve by sharing anonymous usage data",
                        style = typography.bodySm.copy(color = colors.textSecondary)
                    )
                }
            }
        }

        // About
        item {
            VetraBrandCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = null,
                            tint = colors.brand
                        )
                        Text(
                            "About Vetra UI",
                            style = typography.headingMd.copy(color = colors.onBrandSubtle)
                        )
                    }

                    Text(
                        "Version 1.0.0",
                        style = typography.bodyMd.copy(color = colors.onBrandSubtle)
                    )

                    Text(
                        "A modern, elegant design system for Compose Multiplatform. Built with accessibility, performance, and developer experience in mind.",
                        style = typography.bodySm.copy(color = colors.onBrandSubtle)
                    )

                    Text(
                        "Currently using ${if (actualDarkMode) "dark" else "light"} theme",
                        style = typography.bodySm.copy(color = colors.onBrandSubtle)
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        VetraOutlinedButton(onClick = {}) {
                            Text("Documentation")
                        }
                        VetraButton(onClick = {
                            uriHandler.openUri("https://github.com/flyfishxu/vetra")
                        }) {
                            Text("GitHub")
                        }
                    }
                }
            }
        }

        // Actions
        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                VetraButton(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Refresh,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Reset to Defaults")
                }

                VetraDangerButton(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Outlined.DeleteForever,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Clear All Data")
                }
            }
        }
    }
}

@Composable
fun ThemeModeOption(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    title: String,
    description: String
) {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography
    val shapes = VetraTheme.shapes

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shapes.sm)
            .clickable(onClick = onClick)
            .background(
                if (selected) colors.brandSubtle else colors.canvasSubtle
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (selected) colors.brand else colors.textSecondary,
            modifier = Modifier.size(24.dp)
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                title,
                style = typography.bodyLg.copy(
                    color = if (selected) colors.onBrandSubtle else colors.textPrimary
                )
            )
            Text(
                description,
                style = typography.bodySm.copy(
                    color = if (selected) colors.onBrandSubtle else colors.textSecondary
                )
            )
        }

        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = colors.brand,
                unselectedColor = colors.border
            )
        )
    }
}
