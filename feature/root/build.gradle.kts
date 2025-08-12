plugins {
    alias(libs.plugins.convention.feature)
    alias(libs.plugins.convention.compose)
}

dependencies {
    commonMainImplementation(projects.core.preferences)
    commonMainImplementation(projects.core.ui)

    commonMainImplementation(projects.feature.auth)
    commonMainImplementation(projects.feature.main)
    commonMainImplementation(projects.feature.onboarding)
    commonMainImplementation(projects.feature.registration)
}
