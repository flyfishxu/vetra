# Vetra UI Elevation System

## Overview

Vetra UI features a refined soft shadow system designed for wider spread, softer gradients, and comfortable visual experience:

- **Wider Diffusion**: 1.5× spread radius for enhanced depth perception
- **Softer Gradients**: Lower alpha values for natural transitions
- **Reduced Eye Strain**: Eliminates harsh shadow edges

## Elevation Levels

Six standard elevation levels:

```kotlin
VetraElevation.Level0 = 0.dp   // No elevation - flat elements
VetraElevation.Level1 = 3.dp   // Subtle - cards, containers
VetraElevation.Level2 = 6.dp   // Light - raised buttons, floating elements
VetraElevation.Level3 = 10.dp  // Medium - dialogs, emphasis components
VetraElevation.Level4 = 16.dp  // High - navigation drawers, major overlays
VetraElevation.Level5 = 24.dp  // Maximum - modal dialogs, critical overlays
```

## Usage

### Basic Usage

Apply soft shadows with the `softShadow` modifier:

```kotlin
Box(
    modifier = Modifier
        .softShadow(
            elevation = VetraElevation.Level2,
            shape = RoundedCornerShape(16.dp)
        )
) {
    // Your content
}
```

### Advanced Configuration

Customize shadow appearance:

```kotlin
Modifier.softShadow(
    elevation = VetraElevation.Level3,
    shape = MaterialTheme.shapes.large,
    clip = false,
    ambientColor = Color.Black.copy(alpha = 0.08f)
)
```

**Parameters:**
- `elevation`: Shadow height
- `shape`: Shadow shape (should match element shape)
- `clip`: Clip content to shape (default: false)
- `ambientColor`: Ambient shadow color (default: Black @ 8% alpha)

## Component Usage Examples

### Cards

```kotlin
VetraCard() {
    // Uses Level1 elevation by default
}

VetraElevatedCard() {
    // Uses Level2 elevation for stronger emphasis
}
```

### Buttons

```kotlin
VetraPrimaryButton() {
    // Uses Level2 elevation for prominent primary actions
}

VetraFilledTonalButton() {
    // Uses Level1 elevation for secondary actions
}

VetraElevatedButton() {
    // Uses Level3 elevation for maximum prominence
}
```

### Navigation Bar

```kotlin
VetraNavigationBar() {
    // Uses Level3 elevation to float above content
}
```

## Design Principles

1. **Consistency**: Predefined levels ensure uniform visual hierarchy
2. **Progressive Scale**: Gradual elevation growth from Level1 to Level5
3. **Comfort**: Soft shadows reduce eye strain
4. **Clarity**: Clear depth hierarchy aids spatial understanding

## Technical Implementation

Soft shadows are implemented through:

1. **Diffusion Coefficient**: Actual spread = elevation × 1.5
2. **Color Configuration**:
   - Ambient shadow: Black @ 8% alpha
   - Spot shadow: Black @ 12% alpha
3. **Cross-Platform**: Uses Compose's built-in shadow API for Android, iOS, and Desktop compatibility

## Migration Guide

Migrating from standard shadow system:

1. **Update imports**:
```kotlin
// Old
import androidx.compose.ui.draw.shadow

// New
import com.flyfishxu.vetraui.core.theme.VetraElevation
import com.flyfishxu.vetraui.core.theme.softShadow
```

2. **Replace modifier**:
```kotlin
// Old
Modifier.shadow(elevation = 4.dp, shape = shape)

// New
Modifier.softShadow(elevation = VetraElevation.Level2, shape = shape)
```

3. **Map elevation values**:
- 1dp → Level1 (3dp)
- 2-4dp → Level2 (6dp)
- 6-8dp → Level3 (10dp)
- 12dp → Level4 (16dp)
- 16dp+ → Level5 (24dp)

---

**Vetra UI** - Elegant by design, intuitive by nature.
