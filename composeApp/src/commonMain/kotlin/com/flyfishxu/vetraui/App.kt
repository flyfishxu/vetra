package com.flyfishxu.vetraui

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ViewModule
import androidx.compose.material.icons.outlined.Accessibility
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Brightness4
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Devices
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ViewModule
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.VetraBrandCard
import com.flyfishxu.vetraui.core.VetraIconButton
import com.flyfishxu.vetraui.core.VetraNavigationBar
import com.flyfishxu.vetraui.core.VetraNavigationBarItem
import com.flyfishxu.vetraui.core.VetraOutlinedCard
import com.flyfishxu.vetraui.core.VetraTopAppBar
import com.flyfishxu.vetraui.core.theme.VetraTheme
import com.flyfishxu.vetraui.screens.ComponentsGalleryScreen
import com.flyfishxu.vetraui.screens.SettingsScreen
import com.flyfishxu.vetraui.theme.ThemeMode
import com.flyfishxu.vetraui.theme.isSystemInDarkTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Vetra UI Showcase App
 *
 * A comprehensive demonstration of all Vetra UI components and features.
 * Shows the elegant, modern design system in action.
 */

enum class AppSection {
    HOME,
    COMPONENTS,
    SETTINGS
}

@Composable
@Preview
fun App(
    onThemeChanged: ((Boolean) -> Unit)? = null
) {
    // Theme state management
    var themeMode by remember { mutableStateOf(ThemeMode.SYSTEM) }
    val systemInDarkMode = isSystemInDarkTheme()

    // Calculate actual dark mode based on preference and system state
    val isDarkMode = when (themeMode) {
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
        ThemeMode.SYSTEM -> systemInDarkMode
    }

    // Notify platform of theme changes (for system bars, etc.)
    LaunchedEffect(isDarkMode) {
        onThemeChanged?.invoke(isDarkMode)
    }

    var selectedSection by remember { mutableStateOf(AppSection.HOME) }

    VetraTheme(darkMode = isDarkMode) {
        val colors = VetraTheme.colors

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.canvas)
        ) {
            // Top App Bar
            VetraTopAppBar(
                title = {
                    Text(
                        when (selectedSection) {
                            AppSection.HOME -> "Vetra UI"
                            AppSection.COMPONENTS -> "Components"
                            AppSection.SETTINGS -> "Settings"
                        }
                    )
                },
                navigationIcon = if (selectedSection != AppSection.HOME) {
                    {
                        VetraIconButton(
                            onClick = { selectedSection = AppSection.HOME },
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                } else null,
                actions = {
                    VetraIconButton(
                        onClick = {
                            themeMode = when (themeMode) {
                                ThemeMode.LIGHT -> ThemeMode.DARK
                                ThemeMode.DARK -> ThemeMode.SYSTEM
                                ThemeMode.SYSTEM -> ThemeMode.LIGHT
                            }
                        },
                        imageVector = when (themeMode) {
                            ThemeMode.LIGHT -> Icons.Outlined.LightMode
                            ThemeMode.DARK -> Icons.Outlined.DarkMode
                            ThemeMode.SYSTEM -> Icons.Outlined.Brightness4
                        },
                        contentDescription = "Toggle theme"
                    )
                }
            )

            // Content
            Box(modifier = Modifier.weight(1f)) {
                when (selectedSection) {
                    AppSection.HOME -> HomeScreen(
                        onNavigateToComponents = { selectedSection = AppSection.COMPONENTS }
                    )

                    AppSection.COMPONENTS -> ComponentsGalleryScreen()
                    
                    AppSection.SETTINGS -> SettingsScreen(
                        themeMode = themeMode,
                        systemInDarkMode = systemInDarkMode,
                        actualDarkMode = isDarkMode,
                        onThemeModeChange = { themeMode = it }
                    )
                }
            }

            // Bottom Navigation
            VetraNavigationBar {
                VetraNavigationBarItem(
                    selected = selectedSection == AppSection.HOME,
                    onClick = { selectedSection = AppSection.HOME },
                    icon = Icons.Outlined.Home,
                    selectedIcon = Icons.Filled.Home,
                    label = "Home"
                )
                VetraNavigationBarItem(
                    selected = selectedSection == AppSection.COMPONENTS,
                    onClick = { selectedSection = AppSection.COMPONENTS },
                    icon = Icons.Outlined.ViewModule,
                    selectedIcon = Icons.Filled.ViewModule,
                    label = "Components"
                )
                VetraNavigationBarItem(
                    selected = selectedSection == AppSection.SETTINGS,
                    onClick = { selectedSection = AppSection.SETTINGS },
                    icon = Icons.Outlined.Settings,
                    selectedIcon = Icons.Filled.Settings,
                    label = "Settings"
                )
            }
        }
    }
}

@Composable
fun HomeScreen(onNavigateToComponents: () -> Unit) {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    "Welcome to Vetra UI",
                    style = typography.displayMd.copy(color = colors.textPrimary)
                )
                Text(
                    "A modern, elegant design system for Compose Multiplatform",
                    style = typography.bodyLg.copy(color = colors.textSecondary)
                )
            }
        }

        item {
            VetraBrandCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = onNavigateToComponents
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "Explore Components",
                            style = typography.headingLg.copy(color = colors.onBrandSubtle)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Browse all UI components: Buttons, Cards, Inputs, Sliders, Menus, and more",
                            style = typography.bodyMd.copy(color = colors.onBrandSubtle)
                        )
                    }
                    Icon(
                        imageVector = Icons.Filled.ViewModule,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = colors.brand
                    )
                }
            }
        }

        item {
            VetraOutlinedCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        "Design Principles",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )

                    FeatureItem(
                        icon = Icons.Outlined.Palette,
                        title = "Semantic Colors",
                        description = "Intuitive naming like 'brand', 'textSecondary', 'canvasElevated'"
                    )

                    FeatureItem(
                        icon = Icons.Outlined.AutoAwesome,
                        title = "Elegant Motion",
                        description = "Smooth animations with spring physics and refined timing"
                    )

                    FeatureItem(
                        icon = Icons.Outlined.Accessibility,
                        title = "Accessible",
                        description = "Built with accessibility in mind from the ground up"
                    )

                    FeatureItem(
                        icon = Icons.Outlined.Devices,
                        title = "Cross-Platform",
                        description = "Works seamlessly on Android, iOS, and Desktop"
                    )
                }
            }
        }
    }
}

@Composable
fun FeatureItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String
) {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = colors.brand
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                title,
                style = typography.bodyLg.copy(color = colors.textPrimary)
            )
            Text(
                description,
                style = typography.bodySm.copy(color = colors.textSecondary)
            )
        }
    }
}
