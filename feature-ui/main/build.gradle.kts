import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.plugin)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(compose.ui)
            implementation(compose.runtime)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)

            implementation(libs.decompose)
            implementation(libs.decompose.compose)

            implementation(libs.mvikotlin.core)
            implementation(libs.mvikotlin.main)
            implementation(libs.mvikotlin.coroutines)

            implementation(libs.koin.core)

            implementation(libs.cupertino.decompose)
            implementation(libs.cupertino.adaptive)
            implementation(libs.cupertino.icons.extended)

            implementation(projects.feature.main)
            implementation(projects.feature.addNote)
            implementation(projects.feature.notes)
            implementation(projects.feature.editNote)
            implementation(projects.feature.settings)
            implementation(projects.feature.favorites)
            implementation(projects.feature.profile)

            implementation(projects.featureUi.addNote)
            implementation(projects.featureUi.note)
            implementation(projects.featureUi.editNote)
            implementation(projects.featureUi.settings)
            implementation(projects.featureUi.favorites)
            implementation(projects.featureUi.profile)
        }
    }
}

android {
    namespace = "com.khomichenko.ui_main"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
