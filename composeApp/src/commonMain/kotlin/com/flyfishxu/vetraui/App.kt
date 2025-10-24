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
import com.flyfishxu.vetraui.navigation.*
import com.flyfishxu.vetraui.screens.*
import com.flyfishxu.vetraui.theme.ThemeMode
import com.flyfishxu.vetraui.theme.isSystemInDarkTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Vetra UI Showcase App
 *
 * A comprehensive demonstration of all Vetra UI components and features.
 * Shows the elegant, modern design system in action.
 */

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

    // Navigation state
    val navigationState = rememberNavigationState()
    
    // Handle back press
    BackHandler(enabled = navigationState.canGoBack) {
        navigationState.navigateBack()
    }

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
                        when (navigationState.currentDestination) {
                            is Destination.Home -> "Vetra UI"
                            is Destination.Components -> "Components"
                            is Destination.Settings -> "Settings"
                            is Destination.ButtonsDetail -> "Buttons"
                            is Destination.CardsDetail -> "Cards"
                            is Destination.InputsDetail -> "Inputs"
                            is Destination.SlidersDetail -> "Sliders"
                            is Destination.MenuDetail -> "Menus"
                            is Destination.LoadingDetail -> "Loading"
                            is Destination.BadgesAndChipsDetail -> "Badges & Chips"
                        }
                    )
                },
                navigationIcon = if (navigationState.canGoBack) {
                    {
                        VetraIconButton(
                            onClick = { navigationState.navigateBack() },
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
                when (navigationState.currentDestination) {
                    is Destination.Home -> HomeScreen(
                        onNavigateToComponents = { navigationState.navigateTo(Destination.Components) }
                    )

                    is Destination.Components -> ComponentsGalleryScreen(
                        onNavigateToDetail = { destination -> navigationState.navigateTo(destination) }
                    )
                    
                    is Destination.Settings -> SettingsScreen(
                        themeMode = themeMode,
                        systemInDarkMode = systemInDarkMode,
                        actualDarkMode = isDarkMode,
                        onThemeModeChange = { themeMode = it }
                    )
                    
                    // Component detail screens
                    is Destination.ButtonsDetail -> ButtonsScreen()
                    is Destination.CardsDetail -> CardsScreen()
                    is Destination.InputsDetail -> InputsScreen()
                    is Destination.SlidersDetail -> SlidersScreen()
                    is Destination.MenuDetail -> MenuScreen()
                    is Destination.LoadingDetail -> LoadingScreen()
                    is Destination.BadgesAndChipsDetail -> BadgesAndChipsScreen()
                }
            }

            // Bottom Navigation
            VetraNavigationBar {
                VetraNavigationBarItem(
                    selected = navigationState.currentDestination.getMainTab() is Destination.Home,
                    onClick = { 
                        if (navigationState.currentDestination !is Destination.Home) {
                            navigationState.navigateAndClearStack(Destination.Home)
                        }
                    },
                    icon = Icons.Outlined.Home,
                    selectedIcon = Icons.Filled.Home,
                    label = "Home"
                )
                VetraNavigationBarItem(
                    selected = navigationState.currentDestination.getMainTab() is Destination.Components,
                    onClick = { 
                        val mainTab = navigationState.currentDestination.getMainTab()
                        if (mainTab !is Destination.Components) {
                            navigationState.navigateAndClearStack(Destination.Components)
                        } else if (navigationState.currentDestination !is Destination.Components) {
                            // If we're in a detail screen, go back to components gallery
                            navigationState.navigateBackTo(Destination.Components)
                        }
                    },
                    icon = Icons.Outlined.ViewModule,
                    selectedIcon = Icons.Filled.ViewModule,
                    label = "Components"
                )
                VetraNavigationBarItem(
                    selected = navigationState.currentDestination.getMainTab() is Destination.Settings,
                    onClick = { 
                        if (navigationState.currentDestination !is Destination.Settings) {
                            navigationState.navigateAndClearStack(Destination.Settings)
                        }
                    },
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
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
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
                Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
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
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
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
