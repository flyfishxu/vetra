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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.outlined.Accessibility
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Brightness4
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Devices
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.TouchApp
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
import com.flyfishxu.vetraui.core.components.VetraBrandCard
import com.flyfishxu.vetraui.core.components.VetraCard
import com.flyfishxu.vetraui.core.components.VetraIconButton
import com.flyfishxu.vetraui.core.components.VetraNavigationBar
import com.flyfishxu.vetraui.core.components.VetraNavigationBarItem
import com.flyfishxu.vetraui.core.components.VetraOutlinedCard
import com.flyfishxu.vetraui.core.components.VetraTopAppBar
import com.flyfishxu.vetraui.core.theme.VetraTheme
import com.flyfishxu.vetraui.screens.ButtonsScreen
import com.flyfishxu.vetraui.screens.CardsScreen
import com.flyfishxu.vetraui.screens.InputsScreen
import com.flyfishxu.vetraui.screens.LoadingScreen
import com.flyfishxu.vetraui.screens.MenuScreen
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
    BUTTONS,
    CARDS,
    INPUTS,
    MENU,
    LOADING,
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
                            AppSection.BUTTONS -> "Buttons"
                            AppSection.CARDS -> "Cards"
                            AppSection.INPUTS -> "Inputs"
                            AppSection.MENU -> "Menus"
                            AppSection.LOADING -> "Loading"
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
                        onNavigate = { selectedSection = it }
                    )

                    AppSection.BUTTONS -> ButtonsScreen()
                    AppSection.CARDS -> CardsScreen()
                    AppSection.INPUTS -> InputsScreen()
                    AppSection.MENU -> MenuScreen()
                    AppSection.LOADING -> LoadingScreen()
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
                    selected = selectedSection == AppSection.BUTTONS,
                    onClick = { selectedSection = AppSection.BUTTONS },
                    icon = Icons.Outlined.TouchApp,
                    selectedIcon = Icons.Filled.TouchApp,
                    label = "Buttons"
                )
                VetraNavigationBarItem(
                    selected = selectedSection == AppSection.CARDS,
                    onClick = { selectedSection = AppSection.CARDS },
                    icon = Icons.Outlined.GridView,
                    selectedIcon = Icons.Filled.GridView,
                    label = "Cards"
                )
                VetraNavigationBarItem(
                    selected = selectedSection == AppSection.INPUTS,
                    onClick = { selectedSection = AppSection.INPUTS },
                    icon = Icons.Outlined.Edit,
                    selectedIcon = Icons.Filled.Edit,
                    label = "Inputs"
                )
                VetraNavigationBarItem(
                    selected = selectedSection == AppSection.MENU,
                    onClick = { selectedSection = AppSection.MENU },
                    icon = Icons.Outlined.Menu,
                    selectedIcon = Icons.Filled.Menu,
                    label = "Menus"
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
fun HomeScreen(onNavigate: (AppSection) -> Unit) {
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
                onClick = { onNavigate(AppSection.BUTTONS) }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "Button Components",
                            style = typography.headingMd.copy(color = colors.onBrandSubtle)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            "5 variants: Primary, Secondary, Outlined, Ghost, and Danger",
                            style = typography.bodyMd.copy(color = colors.onBrandSubtle)
                        )
                    }
                    Icon(
                        imageVector = Icons.Filled.TouchApp,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = colors.brand
                    )
                }
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                VetraCard(
                    modifier = Modifier.weight(1f),
                    onClick = { onNavigate(AppSection.CARDS) }
                ) {
                    Icon(
                        imageVector = Icons.Filled.GridView,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = colors.brand
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        "Cards",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "5 card styles",
                        style = typography.bodySm.copy(color = colors.textSecondary)
                    )
                }

                VetraCard(
                    modifier = Modifier.weight(1f),
                    onClick = { onNavigate(AppSection.INPUTS) }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = colors.accent
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        "Inputs",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Forms",
                        style = typography.bodySm.copy(color = colors.textSecondary)
                    )
                }
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                VetraCard(
                    modifier = Modifier.weight(1f),
                    onClick = { onNavigate(AppSection.MENU) }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = colors.success
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        "Menus",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Dropdowns",
                        style = typography.bodySm.copy(color = colors.textSecondary)
                    )
                }

                VetraCard(
                    modifier = Modifier.weight(1f),
                    onClick = { onNavigate(AppSection.LOADING) }
                ) {
                    Icon(
                        imageVector = Icons.Filled.HourglassBottom,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = colors.brand
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        "Loading",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Indicators",
                        style = typography.bodySm.copy(color = colors.textSecondary)
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
