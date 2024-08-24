import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.plugin)
    alias(libs.plugins.android.application)
    alias(libs.plugins.buildConfig)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.sqlDelight)
    alias(libs.plugins.moko.resources)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
            export(libs.essenty.lifecycle)
            export(libs.decompose)
        }
    }

    sourceSets {
        all {
            languageSettings {
//                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)

            //resources
            implementation(libs.moko.core)
            implementation(libs.moko.compose)

            implementation(libs.composeImageLoader)
            implementation(libs.kermit)

//            implementation(libs.kotlinx.coroutines.core)

            implementation(libs.koin.core)

            api(libs.decompose)
            implementation(libs.decompose.compose)
            implementation(libs.mvikotlin.core)
            implementation(libs.mvikotlin.coroutines)
            implementation(libs.mvikotlin.main)

            api(libs.essenty.lifecycle)
            api(libs.essenty.coroutines)
            
            //core implementation
            implementation(projects.core.preferences)
            implementation(projects.core.database)

            //feature implementation
            implementation(projects.feature.root)
            implementation(projects.feature.auth)
            implementation(projects.feature.registration)
            implementation(projects.feature.main)
            implementation(projects.feature.onboarding)
            implementation(projects.feature.notes)
            implementation(projects.feature.addNote)
            implementation(projects.feature.editNote)
            implementation(projects.feature.settings)
            implementation(projects.feature.favorites)

            //ui implementation
            implementation(projects.featureUi.auth)
            implementation(projects.featureUi.onboarding)
            implementation(projects.featureUi.main)
            implementation(projects.featureUi.registration)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }

        androidMain.dependencies {
            implementation(libs.androidx.appcompat)
            implementation(libs.androidx.activityCompose)

            implementation(libs.koin.android)
        }
    }
}

android {
    namespace = "com.khomichenko.notes"
    compileSdk = 34

    defaultConfig {
        minSdk = 26
        targetSdk = 34

        applicationId = "com.khomichenko.notes.androidApp"
        versionCode = 1
        versionName = "1.0.0"
    }
    sourceSets["main"].apply {
        manifest.srcFile("src/androidMain/AndroidManifest.xml")
        res.srcDirs("src/androidMain/resources")
        resources.srcDirs("src/commonMain/resources")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
}

buildConfig {
    // BuildConfig configuration here.
    // https://github.com/gmazzo/gradle-buildconfig-plugin#usage-in-kts
}


multiplatformResources {
    resourcesPackage.set("com.khomichenko.fitness")
}
