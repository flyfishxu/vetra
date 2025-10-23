# Vetra UI Design System

> A modern, elegant design system for Compose Multiplatform

## Philosophy

Vetra UI is built on the principle of **elegant simplicity**. We believe that beautiful interfaces should be intuitive, accessible, and a joy to use. Unlike Material Design, which follows Google's specific design language, Vetra UI creates a universal aesthetic that works across all platforms and contexts.

### Core Principles

1. **Clarity First** - Every element has a clear purpose and hierarchy
2. **Universal Appeal** - Design that resonates with all audiences
3. **Intuitive Semantics** - Color and component names that make sense
4. **Elegant Motion** - Smooth, purposeful animations
5. **Accessible by Default** - Everyone can use your app comfortably

---

## Color System

### Semantic Naming

Vetra uses intuitive, semantic color names instead of technical jargon:

```kotlin
// ❌ Material way
colorScheme.primary
colorScheme.onSecondaryContainer

// ✅ Vetra way
colors.brand
colors.textSecondary
```

### Color Roles

#### Brand Colors
- **brand** - Your primary brand color for main actions
- **onBrand** - Content on brand backgrounds
- **brandSubtle** - Subtle variant for less emphasis
- **onBrandSubtle** - Content on subtle brand backgrounds

#### Accent Colors
- **accent** - Secondary accent for highlights
- **onAccent** - Content on accent backgrounds
- **accentSubtle** - Subtle accent variant
- **onAccentSubtle** - Content on subtle accent

#### Canvas Colors
- **canvas** - Main background color
- **onCanvas** - Primary content on canvas
- **canvasElevated** - Elevated surfaces (cards, dialogs)
- **onCanvasElevated** - Content on elevated surfaces
- **canvasSubtle** - Subtle backgrounds
- **onCanvasSubtle** - Content on subtle backgrounds

#### Text Colors
- **textPrimary** - Main content text
- **textSecondary** - Supporting text
- **textTertiary** - Hints and placeholders
- **textDisabled** - Disabled text

#### Border Colors
- **border** - Standard borders
- **borderSubtle** - Subtle borders
- **borderFocus** - Focus rings for accessibility

#### Semantic Colors
- **success** / **onSuccess** - Success states
- **warning** / **onWarning** - Warning states
- **danger** / **onDanger** - Error/destructive states
- **info** / **onInfo** - Informational states

### Color Palette

#### Light Mode
- Clean, bright aesthetic with subtle grays
- Brand: Modern blue (#2563EB)
- Accent: Elegant purple (#7C3AED)
- Canvas: Soft off-white (#FAFAFA)

#### Dark Mode
- Rich blacks with comfortable contrast
- Brand: Brighter blue (#3B82F6) for visibility
- Accent: Vivid purple (#8B5CF6)
- Canvas: Deep black (#0A0A0A)

---

## Typography

### Scale System

Based on a harmonious progression (major second - 1.125×):
- 11sp, 12sp, 14sp, 16sp, 18sp, 20sp, 24sp, 28sp, 32sp, 40sp, 48sp

### Type Styles

#### Display Styles (Bold, tight spacing)
- **displayXl** - 48sp - Hero sections
- **displayLg** - 40sp - Page titles
- **displayMd** - 32sp - Prominent headings
- **displaySm** - 28sp - Section headings

#### Heading Styles (SemiBold, balanced)
- **headingXl** - 24sp - Card titles
- **headingLg** - 20sp - Subsections
- **headingMd** - 18sp - List headers
- **headingSm** - 16sp - Small headings

#### Body Styles (Regular, generous line height)
- **bodyLg** - 16sp - Main content
- **bodyMd** - 14sp - Secondary content
- **bodySm** - 12sp - Captions

#### Label Styles (SemiBold, uppercase-ready)
- **labelLg** - 14sp - Buttons
- **labelMd** - 12sp - Form labels
- **labelSm** - 11sp - Badges

---

## Shape System

### Corner Radius Scale

- **none** - 0dp - Sharp corners
- **xs** - 4dp - Badges, chips
- **sm** - 8dp - Buttons, inputs
- **md** - 12dp - Cards
- **lg** - 16dp - Large cards
- **xl** - 24dp - Dialogs
- **xxl** - 32dp - Full-screen modals
- **full** - 9999dp - Circular/pills

---

## Shadow System

### Elevation Levels

Soft, natural shadows that create comfortable visual hierarchy:

- **none** - 0dp - Flat surfaces
- **xs** - 1dp - Subtle separation
- **sm** - 2dp - Cards, buttons
- **md** - 4dp - Raised buttons
- **lg** - 8dp - Dropdowns, tooltips
- **xl** - 16dp - Dialogs, sheets
- **xxl** - 24dp - Critical modals

### Shadow Philosophy

Unlike Material's heavy shadows, Vetra uses:
- Balanced blur radius (1.2× elevation) for natural appearance
- Subtle transparency for elegant, lightweight feel
- Natural lighting model mimicking light from above
- Refined alpha values: ambient 6%, spot 10%
- Shadows that are visible yet never overwhelming

---

## Components

### Button Variants

#### VetraButton (Primary)
- Solid brand color
- For main actions
- Limit to one per section

#### VetraSecondaryButton
- Solid accent color
- For secondary actions
- Multiple per screen OK

#### VetraOutlinedButton
- Border only
- For tertiary actions
- Subtle emphasis

#### VetraGhostButton
- No background
- Minimal emphasis
- Dense UIs

#### VetraDangerButton
- Destructive actions
- Red color signals caution
- Use sparingly

### Card Variants

#### VetraCard (Standard)
- Soft shadow elevation
- Default card style

#### VetraFlatCard
- No elevation
- Subtle grouping

#### VetraElevatedCard
- Higher elevation
- Emphasized content

#### VetraOutlinedCard
- Border only
- Clean boundaries

#### VetraBrandCard
- Subtle brand color
- Special content

### Input Components

#### VetraTextField
- Floating label animation
- Animated underline (expands from center)
- Smooth placeholder transitions
- Support for icons, error states

#### VetraSwitch
- Smooth thumb transition
- Color transitions for states
- Subtle shadow on active

### Icon Buttons

#### VetraIconButton (Standard)
- No background, minimal

#### VetraFilledIconButton
- Solid background for emphasis

#### VetraOutlinedIconButton
- Border only

### Navigation

#### VetraTopAppBar
- Clean and minimal
- Translucent background
- Subtle border on scroll

#### VetraLargeTopAppBar
- Collapsible large title
- Smooth scroll transitions

#### VetraCenterTopAppBar
- Symmetrical layout
- Centered title

#### VetraNavigationBar
- Bottom navigation with pill indicator
- Smooth icon scale animations
- Label fade effects

---

## Usage

### Basic Setup

```kotlin
@Composable
fun App() {
    VetraTheme(darkMode = false) {
        // Your app content
    }
}
```

### Custom Colors

```kotlin
VetraTheme(
    colors = VetraLightColorScheme.copy(
        brand = Color(0xFF1E40AF)
    )
) {
    // Your app content
}
```

### Accessing Theme

```kotlin
@Composable
fun MyComponent() {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography
    val shapes = VetraTheme.shapes
    val shadows = VetraTheme.shadows
    
    Box(
        modifier = Modifier
            .background(colors.brand)
            .vetraShadow(elevation = shadows.md, shape = shapes.md)
    ) {
        Text(
            text = "Hello Vetra",
            style = typography.headingMd.copy(color = colors.onBrand)
        )
    }
}
```

---

## Design Principles

### 1. Semantic Over Technical

```kotlin
// ❌ Too technical
MaterialTheme.colorScheme.tertiaryContainer

// ✅ Semantic and clear
VetraTheme.colors.accentSubtle
```

### 2. Intuitive Hierarchy

- Use display styles for major titles
- Heading styles for section headers
- Body styles for content
- Label styles for interactive elements

### 3. Consistent Spacing

Use a harmonious spacing scale:
- 4dp, 8dp, 12dp, 16dp, 20dp, 24dp, 32dp, 40dp, 48dp

### 4. Motion with Purpose

Every animation should:
- Have a clear reason
- Use appropriate easing (EaseInOutCubic for most cases)
- Duration: 200-400ms for UI transitions
- Spring physics for playful interactions

### 5. Accessibility

- Minimum contrast ratios: 4.5:1 for normal text, 3:1 for large text
- Focus indicators on all interactive elements
- Touch targets: minimum 44dp × 44dp
- Clear disabled states

---

## Differences from Material Design

| Aspect | Material Design | Vetra UI |
|--------|----------------|----------|
| **Naming** | Technical (primary, onSecondaryContainer) | Semantic (brand, textSecondary) |
| **Shadows** | Heavy, prominent | Soft, elegant |
| **Philosophy** | Google's design language | Universal appeal |
| **Flexibility** | Opinionated structure | Flexible semantics |
| **Learning Curve** | Complex color roles | Intuitive names |
| **Animations** | Standard durations | Refined, purposeful |

---

## Best Practices

### Do ✅

- Use semantic color names
- Follow the type scale
- Apply consistent spacing
- Use elevation purposefully
- Animate state changes
- Test in both light and dark modes
- Consider accessibility

### Don't ❌

- Mix Material and Vetra components
- Use arbitrary colors outside the system
- Apply heavy shadows
- Create custom color names
- Skip animation specs
- Ignore disabled states
- Forget about keyboard navigation

---

## Migration from Material

### Color Mapping

```kotlin
// Material → Vetra
primary → brand
onPrimary → onBrand
primaryContainer → brandSubtle
secondary → accent
surface → canvasElevated
background → canvas
onSurface → textPrimary
onSurfaceVariant → textSecondary
outline → border
error → danger
```

### Component Mapping

```kotlin
// Material → Vetra
Button → VetraButton
OutlinedButton → VetraOutlinedButton
TextButton → VetraGhostButton
Card → VetraCard
TextField → VetraTextField
Switch → VetraSwitch
IconButton → VetraIconButton
TopAppBar → VetraTopAppBar
NavigationBar → VetraNavigationBar
```

---

**Vetra UI** - Elegant by design, intuitive by nature.
