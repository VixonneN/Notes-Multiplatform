package modules

import com.android.build.api.dsl.ApplicationExtension
import extentions.configureCompose
import extentions.configureKotlinAndroidApplication
import extentions.configureMultiplatformApplication
import helpers.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.plugins.multiplatform.get().pluginId)
        pluginManager.apply(libs.plugins.android.application.get().pluginId)
        pluginManager.apply(libs.plugins.kotlinx.serialization.get().pluginId)
        pluginManager.apply(libs.plugins.composePlugin.get().pluginId)
        pluginManager.apply(libs.plugins.compose.get().pluginId)

        val composeDependencies = extensions.getByType<ComposeExtension>().dependencies

        extensions.configure<ApplicationExtension>(::configureKotlinAndroidApplication)
        extensions.configure<KotlinMultiplatformExtension>(::configureMultiplatformApplication)
        extensions.configure<KotlinMultiplatformExtension> {
            configureCompose(this, composeDependencies)
        }
    }
}
