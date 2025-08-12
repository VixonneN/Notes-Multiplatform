package extentions

import helpers.debugImplementation
import helpers.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun Project.configureCompose(
    extension: KotlinMultiplatformExtension,
    composeDependencies: ComposePlugin.Dependencies
) = extension.apply {

    sourceSets.commonMain.dependencies {
        implementation(libs.decompose.compose)
        implementation(composeDependencies.components.resources)

        implementation(composeDependencies.runtime)
        implementation(composeDependencies.ui)
        implementation(composeDependencies.material3)
        implementation(composeDependencies.materialIconsExtended)
    }
    dependencies {
        debugImplementation(composeDependencies.uiTooling)
    }
}
