package extentions

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.withType

internal fun Project.configureDetekt(extension: DetektExtension) = extension.apply {
    // Define the detekt configuration(s) you want to use.
    // Defaults to the default detekt configuration.
    config.setFrom("path/to/config.yml")

    // Applies the config files on top of detekt's default config file. `false` by default.
    buildUponDefaultConfig = true

//    // Kill switch to turn off the Compiler Plugin execution entirely.
//    enableCompilerPlugin.set(true)

    tasks.named<Detekt>("detekt") {
        reports {
            xml.required.set(true)
            html.required.set(true)
            txt.required.set(true)
            sarif.required.set(true)
            md.required.set(true)
        }
    }
    val detekt = tasks.register("detektWithoutTests") {
        dependsOn(tasks.withType<Detekt>().matching { !it.name.contains("Test") })
    }

    tasks.matching { it.name == "check" }.configureEach {
        dependsOn(detekt)
    }

    tasks.withType<Detekt>().configureEach {
        jvmTarget = "17"
        mustRunAfter(
            tasks.matching {
                it.name.lowercase().contains("generate")
            }
        )
    }
}
