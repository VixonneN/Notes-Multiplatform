rootProject.name = "Multiplatform-App"

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":composeApp")

include(":core:network")
include(":core:preferences")
include(":core:database")

include(":feature:onboarding")
include(":feature:root")
include(":feature:auth")
include(":feature:main")
include(":feature:registration")
include(":feature:notes")
include(":feature:add-note")
include(":feature:profile")
include(":feature:edit-note")
include(":feature:settings")
include(":feature:favorites")

include(":feature-ui:onboarding")
include(":feature-ui:auth")
include(":feature-ui:main")
include(":feature-ui:registration")
include(":feature-ui:note")
include(":feature-ui:add-note")
include(":feature-ui:edit-note")
include(":feature-ui:settings")
include(":feature-ui:favorites")
include(":feature-ui:profile")
