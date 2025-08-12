plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()

    gradlePluginPortal()
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.detekt.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)

    compileOnly(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidDetekt") {
            id = "notes.detekt.plugin"
            implementationClass = "modules.DetektConventionPlugin"
        }
    }
    plugins {
        register("composeMultiplatform") {
            id = "notes.compose.plugin"
            implementationClass = "modules.ComposeConventionPlugin"
        }
    }
    plugins {
        register("featureMultiplatform") {
            id = "notes.feature.plugin"
            implementationClass = "modules.FeatureConventionPlugin"
        }
    }
    plugins {
        register("coreMultiplatform") {
            id = "notes.core.plugin"
            implementationClass = "modules.CoreConventionPlugin"
        }
    }
    plugins {
        register("applicationMultiplatform") {
            id = "notes.application.plugin"
            implementationClass = "modules.ApplicationConventionPlugin"
        }
    }
}
