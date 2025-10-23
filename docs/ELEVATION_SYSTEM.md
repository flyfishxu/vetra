# Vetra UI Elevation System

## Overview

Vetra UI features a refined soft shadow system designed for wider spread, enhanced visibility, and comfortable visual experience:

- **Wider Diffusion**: 1.5× spread radius for enhanced depth perception
- **Strong Visibility**: Optimized alpha values (15% ambient, 25% spot) for clear depth hierarchy
- **Clear Hierarchy**: Prominent shadows that create obvious visual separation
- **Reduced Eye Strain**: Soft diffusion eliminates harsh shadow edges

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

Apply soft shadows with the `vetraShadow` modifier:

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

### Advanced Configuration

Customize shadow appearance:

```kotlin
Modifier.vetraShadow(
    elevation = VetraTheme.shadows.lg,
    shape = VetraTheme.shapes.md,
    clip = false,
    tint = Color.Blue  // Optional colored shadow
)
```

**Parameters:**
- `elevation`: Shadow height (Dp value)
- `shape`: Shadow shape (should match element shape)
- `clip`: Clip content to shape (default: false)
- `tint`: Optional tint color for the shadow (default: black)

## Component Usage Examples

### Cards

```kotlin
VetraCard() {
    // Uses shadows.sm elevation by default
}

VetraElevatedCard() {
    // Uses shadows.md elevation for stronger emphasis
}
```

### Buttons

```kotlin
VetraButton() {
    // Uses shadows.sm elevation for primary actions
}

VetraSecondaryButton() {
    // Uses shadows.sm elevation for secondary actions
}
```

### Sliders

```kotlin
VetraSlider() {
    // Thumb uses shadows.md elevation for clear separation from track
}
```

### Navigation Bar

```kotlin
VetraNavigationBar() {
    // Uses elevated shadow to float above content
}
```

## Design Principles

1. **Consistency**: Predefined levels ensure uniform visual hierarchy
2. **Progressive Scale**: Gradual elevation growth from xs to xxl (1dp to 24dp)
3. **Visibility**: Enhanced alpha values ensure clear depth perception
4. **Elegance**: Soft shadows that are visible yet refined
5. **Comfort**: Diffused shadows reduce eye strain
6. **Clarity**: Clear depth hierarchy aids spatial understanding

## Technical Implementation

Soft shadows are implemented through:

1. **Diffusion Coefficient**: Actual spread = elevation × 1.5
2. **Enhanced Color Configuration**:
   - Ambient shadow: Black @ 15% alpha (enhanced base shadow for clear visibility)
   - Spot shadow: Black @ 25% alpha (strong directional shadow for depth hierarchy)
3. **Cross-Platform**: Uses Compose's built-in shadow API for Android, iOS, and Desktop compatibility
4. **Performance**: Optimized rendering with conditional shadow application (0dp = no shadow)

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
