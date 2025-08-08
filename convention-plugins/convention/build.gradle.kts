plugins {
    `kotlin-dsl`
}

kotlin {
//    compilerOptions {
//        jvmTarget = JvmTarget.JVM_17
//    }
}

repositories {
    mavenCentral()
    google()

    gradlePluginPortal()
}

dependencies {
    api(libs.android.gradlePlugin)
    api(libs.kotlin.gradlePlugin)
    api(libs.detekt.gradlePlugin)
    api(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
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
}
