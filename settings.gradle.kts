rootProject.name = "Multiplatform-App"

pluginManagement {
    includeBuild("convention-plugins")
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

include(":core:ui")
include(":core:database-room")
