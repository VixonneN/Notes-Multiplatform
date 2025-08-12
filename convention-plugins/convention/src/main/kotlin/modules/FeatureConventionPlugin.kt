package modules

import com.android.build.gradle.LibraryExtension
import extentions.configureKotlinAndroidLibrary
import extentions.configureMultiplatformFeature
import helpers.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class FeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.plugins.multiplatform.get().pluginId)
        pluginManager.apply(libs.plugins.android.library.get().pluginId)
        pluginManager.apply(libs.plugins.kotlinx.serialization.get().pluginId)

        extensions.configure<KotlinMultiplatformExtension>(::configureMultiplatformFeature)
        extensions.configure<LibraryExtension>(::configureKotlinAndroidLibrary)
    }
}
