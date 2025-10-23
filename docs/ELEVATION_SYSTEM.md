# Vetra UI Elevation System

## Overview

Vetra UI features a refined soft shadow system designed for natural appearance, elegant subtlety, and comfortable visual experience:

- **Balanced Diffusion**: 1.2× spread radius for natural depth without excessive blur
- **Subtle Elegance**: Refined alpha values (6% ambient, 10% spot) for gentle, unobtrusive shadows
- **Natural Hierarchy**: Visible shadows that create clear separation without being heavy
- **Comfortable Viewing**: Soft, transparent shadows that feel lightweight and elegant

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
3. **Subtlety**: Refined alpha values create gentle, unobtrusive shadows
4. **Elegance**: Shadows are visible yet lightweight, never heavy or harsh
5. **Comfort**: Soft diffusion and low opacity reduce eye strain
6. **Natural Feel**: Mimics real-world lighting with heavier shadows below
7. **Balance**: Clear depth perception without overwhelming the interface

## Technical Implementation

Soft shadows are implemented through:

1. **Diffusion Coefficient**: Actual spread = elevation × 1.2
   - Provides soft edges without extending too far
   - Creates natural-looking shadows that don't overwhelm content
2. **Refined Color Configuration**:
   - Ambient shadow: Black @ 6% alpha (soft base shadow for subtle depth)
   - Spot shadow: Black @ 10% alpha (directional shadow for clear separation)
   - These values create visible yet elegant shadows
3. **Natural Lighting Model**:
   - Mimics light source from above
   - Heavier shadows at the bottom for realism
   - Soft diffusion for comfortable viewing
4. **Cross-Platform**: Uses Compose's built-in shadow API for Android, iOS, and Desktop compatibility
5. **Performance**: Optimized rendering with conditional shadow application (0dp = no shadow)

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
