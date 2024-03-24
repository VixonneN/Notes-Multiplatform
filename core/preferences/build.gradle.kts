import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    val xcf = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "preferences"
            xcf.add(this)
            isStatic = true
        }
    }

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
}
