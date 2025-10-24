# Vetra UI Elevation System

## Overview

Vetra UI features a refined adaptive shadow system designed for clear visual hierarchy, natural appearance, and cross-platform consistency:

- **Adaptive Configuration**: Automatic adjustment between light and dark modes
- **Clear Visibility**: Properly tuned alpha values (12-18% light, 24-32% dark) for clear depth perception
- **Natural Hierarchy**: Visible shadows that create clear separation and establish visual order
- **Cross-Platform Optimized**: Consistent rendering across Android, iOS, and Desktop platforms

## Elevation Levels

Seven standard elevation levels:

```kotlin
VetraShadows.none = 0.dp   // No elevation - flat elements
VetraShadows.xs = 1.dp     // Barely perceptible - hovering elements
VetraShadows.sm = 2.dp     // Light - cards, contained buttons
VetraShadows.md = 4.dp     // Moderate - raised buttons, slider thumbs
VetraShadows.lg = 8.dp     // Strong - dropdowns, tooltips
VetraShadows.xl = 16.dp    // Maximum - dialogs, navigation drawers
VetraShadows.xxl = 24.dp   // Dramatic - modals, critical alerts
```

## Usage

### Basic Usage

Apply adaptive shadows with the `vetraShadow` modifier. Shadows automatically adapt to light/dark mode:

```kotlin
Box(
    modifier = Modifier
        .vetraShadow(
            elevation = VetraTheme.shadows.md,
            shape = RoundedCornerShape(16.dp)
        )
) {
    // Your content
}
```

The shadow will automatically use:
- **Light mode**: 15% ambient, 25% spot shadows (1.15× spread)
- **Dark mode**: 25% ambient, 35% spot shadows (1.2× spread)

### Advanced Configuration

Customize shadow appearance if needed:

```kotlin
Modifier.vetraShadow(
    elevation = VetraTheme.shadows.lg,
    shape = VetraTheme.shapes.md,
    clip = false
)
```

**Parameters:**
- `elevation`: Shadow height (Dp value)
- `shape`: Shadow shape (should match element shape)
- `clip`: Clip content to shape (default: false)

### Custom Shadow Config

For special cases, you can provide custom shadow configuration:

```kotlin
VetraTheme(
    darkMode = false,
    shadowConfig = ShadowConfig(
        ambientAlpha = 0.15f,
        spotAlpha = 0.20f,
        elevationMultiplier = 1.2f
    )
) {
    // Your app content
}
```

## Design Principles

1. **Consistency**: Predefined levels ensure uniform visual hierarchy
2. **Progressive Scale**: Gradual elevation growth from xs to xxl (1dp to 24dp)
3. **Clear Visibility**: Properly tuned alpha values ensure shadows are clearly visible
4. **Adaptive Design**: Different configurations for light and dark modes
5. **Natural Feel**: Mimics real-world lighting with heavier shadows below
6. **Balance**: Clear depth perception without overwhelming the interface
7. **Cross-Platform**: Consistent appearance across all supported platforms

## Technical Implementation

The adaptive shadow system is implemented through:

1. **Mode-Aware Configuration**:
   - **Light Mode**: Ambient 15%, Spot 25%, Multiplier 1.15×
   - **Dark Mode**: Ambient 25%, Spot 35%, Multiplier 1.2×
   - Automatically selected based on theme mode
2. **Diffusion Coefficient**: Actual spread = elevation × multiplier
   - Light mode: 1.15× for natural, controlled spread
   - Dark mode: 1.2× for enhanced visibility on dark backgrounds
3. **Dual Shadow System**:
   - Ambient shadow: Provides soft base shadow for overall depth
   - Spot shadow: Creates directional shadow for clear separation
   - Both work together to create realistic depth perception
4. **Natural Lighting Model**:
   - Mimics light source from above
   - Heavier shadows at the bottom for realism
   - Balanced spread for natural appearance
5. **Cross-Platform**: Uses Compose's built-in shadow API for Android, iOS, and Desktop compatibility
6. **Performance**: Optimized rendering with:
   - Conditional shadow application (0dp = no shadow)
   - Single composition pass using `composed {}`
   - Efficient theme value access

## Migration Guide

Migrating from standard shadow system:

1. **Update imports**:
```kotlin
// Old
import androidx.compose.ui.draw.shadow

// New
import com.flyfishxu.vetraui.core.theme.VetraTheme
import com.flyfishxu.vetraui.core.theme.vetraShadow
```

2. **Replace modifier**:
```kotlin
// Old
Modifier.shadow(elevation = 4.dp, shape = shape)

// New
Modifier.vetraShadow(elevation = VetraTheme.shadows.md, shape = shape)
```

3. **Map elevation values**:
- 0dp → shadows.none (0dp)
- 1dp → shadows.xs (1dp)
- 2-3dp → shadows.sm (2dp)
- 4-6dp → shadows.md (4dp)
- 8-12dp → shadows.lg (8dp)
- 16-20dp → shadows.xl (16dp)
- 24dp+ → shadows.xxl (24dp)

---

**Vetra UI** - Elegant by design, intuitive by nature.
