package com.flyfishxu.vetraui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

/**
 * Navigation destinations in the app
 */
sealed class Destination {
    data object Home : Destination()
    data object Components : Destination()
    data object Settings : Destination()
    
    // Component detail screens
    data object ButtonsDetail : Destination()
    data object CardsDetail : Destination()
    data object InputsDetail : Destination()
    data object SlidersDetail : Destination()
    data object MenuDetail : Destination()
    data object LoadingDetail : Destination()
    data object BadgesAndChipsDetail : Destination()
    data object DialogsDetail : Destination()
}

/**
 * Navigation state that manages the navigation stack
 */
class NavigationState(
    initialDestination: Destination = Destination.Home
) {
    private val _stack = mutableStateOf(listOf(initialDestination))
    val stack: List<Destination> get() = _stack.value
    
    val currentDestination: Destination
        get() = _stack.value.lastOrNull() ?: Destination.Home
    
    val canGoBack: Boolean
        get() = _stack.value.size > 1
    
    /**
     * Navigate to a new destination
     */
    fun navigateTo(destination: Destination) {
        // Don't add duplicate if it's already the current screen
        if (currentDestination == destination) return
        
        _stack.value = _stack.value + destination
    }
    
    /**
     * Navigate back to the previous screen
     * @return true if navigation happened, false if already at root
     */
    fun navigateBack(): Boolean {
        if (!canGoBack) return false
        
        _stack.value = _stack.value.dropLast(1)
        return true
    }
    
    /**
     * Navigate back to a specific destination, removing all screens above it
     */
    fun navigateBackTo(destination: Destination) {
        val index = _stack.value.lastIndexOf(destination)
        if (index != -1) {
            _stack.value = _stack.value.take(index + 1)
        }
    }
    
    /**
     * Navigate to a destination and clear the stack
     */
    fun navigateAndClearStack(destination: Destination) {
        _stack.value = listOf(destination)
    }
    
    companion object {
        val Saver: Saver<NavigationState, *> = listSaver(
            save = { state ->
                state.stack.map { destination ->
                    when (destination) {
                        is Destination.Home -> "Home"
                        is Destination.Components -> "Components"
                        is Destination.Settings -> "Settings"
                        is Destination.ButtonsDetail -> "ButtonsDetail"
                        is Destination.CardsDetail -> "CardsDetail"
                        is Destination.InputsDetail -> "InputsDetail"
                        is Destination.SlidersDetail -> "SlidersDetail"
                        is Destination.MenuDetail -> "MenuDetail"
                        is Destination.LoadingDetail -> "LoadingDetail"
                        is Destination.BadgesAndChipsDetail -> "BadgesAndChipsDetail"
                        is Destination.DialogsDetail -> "DialogsDetail"
                    }
                }
            },
            restore = { list ->
                val destinations = list.mapNotNull { item ->
                    when (item) {
                        "Home" -> Destination.Home
                        "Components" -> Destination.Components
                        "Settings" -> Destination.Settings
                        "ButtonsDetail" -> Destination.ButtonsDetail
                        "CardsDetail" -> Destination.CardsDetail
                        "InputsDetail" -> Destination.InputsDetail
                        "SlidersDetail" -> Destination.SlidersDetail
                        "MenuDetail" -> Destination.MenuDetail
                        "LoadingDetail" -> Destination.LoadingDetail
                        "BadgesAndChipsDetail" -> Destination.BadgesAndChipsDetail
                        "DialogsDetail" -> Destination.DialogsDetail
                        else -> null
                    }
                }
                NavigationState().apply {
                    if (destinations.isNotEmpty()) {
                        _stack.value = destinations
                    }
                }
            }
        )
    }
}

/**
 * Remember navigation state across configuration changes
 */
@Composable
fun rememberNavigationState(
    initialDestination: Destination = Destination.Home
): NavigationState {
    return rememberSaveable(saver = NavigationState.Saver) {
        NavigationState(initialDestination)
    }
}

/**
 * Get the main tab for a destination
 */
fun Destination.getMainTab(): Destination {
    return when (this) {
        is Destination.Home -> Destination.Home
        is Destination.Components,
        is Destination.ButtonsDetail,
        is Destination.CardsDetail,
        is Destination.InputsDetail,
        is Destination.SlidersDetail,
        is Destination.MenuDetail,
        is Destination.LoadingDetail,
        is Destination.BadgesAndChipsDetail,
        is Destination.DialogsDetail -> Destination.Components
        is Destination.Settings -> Destination.Settings
    }
}

/**
 * Check if destination is a main tab
 */
fun Destination.isMainTab(): Boolean {
    return this is Destination.Home ||
           this is Destination.Components ||
           this is Destination.Settings
}

