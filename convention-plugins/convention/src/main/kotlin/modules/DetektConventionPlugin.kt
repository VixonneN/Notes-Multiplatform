package modules

import extentions.configureDetekt
import helpers.libs
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class DetektConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.plugins.detekt.get().pluginId)

        val detectExtension = extensions.getByType<DetektExtension>()

        extensions.configure<KotlinMultiplatformExtension> {
            configureDetekt(detectExtension)
        }
    }
}