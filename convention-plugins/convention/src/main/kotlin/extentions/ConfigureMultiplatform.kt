package extentions

import helpers.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import kotlin.text.set
import kotlin.text.toInt

fun Project.configureMultiplatformFeature(
    extension: KotlinMultiplatformExtension
) = extension.apply {

    jvmToolchain(libs.versions.jvmVersion.get().toInt())

    applyDefaultHierarchyTemplate()

    androidTarget {
        compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        all {
            compilerOptions {
                freeCompilerArgs.apply {
                    add("-Xexpect-actual-classes")
                }
            }
            languageSettings {
                optIn("androidx.compose.material3.ExperimentalMaterial3Api")
            }
        }

        commonMain.dependencies {
            // navigation
            implementation(libs.decompose)

            // mvi
            implementation(libs.mvikotlin.core)
            implementation(libs.mvikotlin.main)
            implementation(libs.mvikotlin.coroutines)

            // coroutines
            implementation(libs.kotlinx.coroutines.core)

            // di
            implementation(libs.koin.core)

            // logs
            implementation(libs.napier)
        }
    }
}

fun Project.configureMultiplatformCore(
    extension: KotlinMultiplatformExtension
) = extension.apply {
    jvmToolchain(libs.versions.jvmVersion.get().toInt())

    applyDefaultHierarchyTemplate()

    androidTarget {
        compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        compilerOptions {
            freeCompilerArgs.apply {
                add("-Xexpect-actual-classes")
            }
        }
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.koin.core)
            implementation(libs.napier)
        }
    }
}

fun Project.configureMultiplatformApplication(
    extension: KotlinMultiplatformExtension
) = extension.apply {
    androidTarget {
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
            compilerOptions {
                freeCompilerArgs.apply {
                    add("-Xexpect-actual-classes")
                }
            }
            languageSettings {
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }
        commonMain.dependencies {
            api(libs.essenty.lifecycle)
            api(libs.decompose)

            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.koin.core)
            implementation(libs.napier)
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
