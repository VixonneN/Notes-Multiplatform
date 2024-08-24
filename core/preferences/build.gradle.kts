import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
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
            //preferences
            implementation(libs.multiplatform.settings.coroutines)
            implementation(libs.multiplatform.settings)

            //di
            implementation(libs.koin.core)

            //coroutines
            implementation(libs.kotlinx.coroutines.core)
        }
        androidMain.dependencies {
            implementation(libs.multiplatform.settings.datastore)
            implementation(libs.androidx.datastore.core)
            implementation(libs.androidx.datastore.preferences)

            //di
            implementation(libs.koin.core)
        }
        iosMain.dependencies {
            //di
            implementation(libs.koin.core)
        }
    }
}

android {
    namespace = "com.khomichenko.preferences"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
