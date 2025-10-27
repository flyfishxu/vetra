import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("com.vanniktech.maven.publish")
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "VetraUICore"
            isStatic = true
        }
    }

    jvm()

    js {
        browser()
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.ui.tooling)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.flyfishxu.vetraui.core"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

mavenPublishing {
    publishToMavenCentral()
    signAllPublications()

    coordinates("com.flyfishxu.vetraui", "core", "1.0.0-alpha01")

    pom {
        name.set("Vetra UI")
        description.set("A modern, elegant, and minimalist Compose Multiplatform components library")
        inceptionYear.set("2025")
        url.set("https://github.com/flyfishxu/vetra-ui")
        licenses {
            license {
                name.set("MIT License")
                url.set("https://opensource.org/licenses/MIT")
                distribution.set("repo")
            }
        }
        developers {
            developer {
                id.set("flyfishxu")
                name.set("Flyfish Xu")
                url.set("https://github.com/flyfishxu")
                organization.set("Flyfish Studio")
                email.set("flyfishxu@outlook.com")
            }
        }
        scm {
            url.set("https://github.com/flyfishxu/vetra-ui")
            connection.set("scm:git:https://github.com/flyfishxu/vetra-ui.git")
            developerConnection.set("scm:git:ssh://git@github.com/flyfishxu/vetra-ui.git")
        }
    }
}