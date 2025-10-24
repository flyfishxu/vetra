# Vetra UI

<div align="center">

<img src="images/vetra.svg" alt="Vetra UI Logo" width="160"/>

**A Modern, Elegant UI Design System for Compose Multiplatform**

*Building delightful experiences with light, depth, and motion*

[![Kotlin](https://img.shields.io/badge/Kotlin-2.2.20-blue.svg)](https://kotlinlang.org)
[![Compose](https://img.shields.io/badge/Compose%20Multiplatform-1.9.1-brightgreen.svg)](https://www.jetbrains.com/compose-multiplatform/)
[![License](https://img.shields.io/badge/License-Apache%202.0-green.svg)](https://opensource.org/licenses/Apache2.0 )

</div>

<div align="center">
  <img src="images/showcase.png" alt="Vetra UI Components Showcase" width="100%"/>
</div>

---

## Philosophy

Vetra UI is a modern design system built from the ground up for Compose Multiplatform. We define our own visual language with careful attention to every interaction detail.

**Core Principles:**

- **Independent Design Language** - Semantic naming (`brand`, `accent`, `canvas`), spring-based motion curves, and harmonious proportions
- **Simplicity with Depth** - Clean interfaces with thoughtful details: soft shadows, subtle transitions, generous whitespace
- **Flowing Interactions** - Organic feedback with elastic animations, progressive states, and spring physics
- **Light & Depth** - Seven elevation levels create natural depth perception through carefully calibrated shadows

---

## Features

### Cross-Platform
- Support Android iOS and Desktop

### Dark Mode

- Cross-platform theme management
- Carefully calibrated dark color palette
- Smooth theme transition animations

### Developer Friendly

- API design similar to Material Design for low learning curve
- Clear naming conventions and comprehensive documentation
- Rich Preview examples for every component

### Lightful UI
- Elegance over Flash: No effects for effect's sake—every animation serves a purpose
- Unity over Variety: Consistent design language reduces cognitive load
- Natural over Mechanical: Motion follows physics, interactions follow intuition
- Space over Density: Give content room to breathe
- Clarity over Abstraction: Intuitive naming, predictable APIs

---

## Quick Start

### Installation (Still developing, not yet released)

Add dependency in `libs.versions.toml`:

```toml
[versions]
vetraui = "1.0.0"

[libraries]
vetraui-core = { module = "com.flyfishxu.vetraui:core", version.ref = "vetraui" }
```

In your `build.gradle.kts`:

```kotlin
commonMain.dependencies {
    implementation(libs.vetraui.core)
}
```

### Basic Usage

```kotlin
import com.flyfishxu.vetraui.core.theme.VetraTheme
import com.flyfishxu.vetraui.core.components.*

@Composable
fun App() {
    VetraTheme(darkTheme = false) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Primary Button
            VetraButton(onClick = { /* ... */ }) {
                Text("Get Started")
            }
            
            // Elegant Card
            VetraCard {
                Text("Beautiful content in a refined container")
            }
            
            // Basic TextField (for search, simple input)
            VetraTextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = "Search..."
            )
            
            // Floating Label TextField (for forms)
            VetraFloatingTextField(
                value = name,
                onValueChange = { name = it },
                label = "Full Name",
                placeholder = "Enter your name"
            )
        }
    }
}
```

---

## Contributing

Contributions are welcome! Before submitting:

- Follow the existing code style
- Add `@Preview` annotations for new components
- Write clear documentation
- Test cross-platform compatibility
- Keep components independent and reusable

---

<div align="center">

© 2025 flyfishxu and contributors.  
Maintained under the Apache License 2.0.

[Example App](composeApp/) · [Report Bug](issues)
</div>