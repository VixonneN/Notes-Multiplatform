package modules

import com.android.build.gradle.LibraryExtension
import extentions.configureCompose
import helpers.debugImplementation
import helpers.implementation
import helpers.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.plugins.composePlugin.get().pluginId)
        pluginManager.apply(libs.plugins.compose.get().pluginId)

        extensions.configure<LibraryExtension> {
            buildFeatures.compose = true
        }

        val composeDependencies = extensions.getByType<ComposeExtension>().dependencies

        extensions.configure<KotlinMultiplatformExtension> {
            configureCompose(this, composeDependencies)
        }
    }
}