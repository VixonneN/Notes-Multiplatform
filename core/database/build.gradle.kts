import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.sqlDelight)
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
            implementation(libs.sqlDelight.runtime)
            implementation(libs.sqlDelight.coroutine)
            implementation(libs.sqlDelight.primitive.adapters)

            implementation(libs.kotlinx.datetime)

            implementation(libs.koin.core)
        }
//        commonTest.dependencies {
//            implementation(libs.kotlin.test)
//        }
        androidMain.dependencies {
            //database
            implementation(libs.sqlDelight.driver.android)

            //di
            implementation(libs.koin.android)

        }
        iosMain.dependencies {
            implementation(libs.sqlDelight.driver.native)
        }
    }
}

android {
    namespace = "com.khomichenko.database"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

sqldelight {
    databases {
        create("notesCacheDatabase") {
            // Database configuration here.
            // https://cashapp.github.io/sqldelight
            packageName.set("com.khomichenko.database.db")
        }
    }
}
