plugins {
    alias(libs.plugins.convention.feature)
    alias(libs.plugins.convention.compose)
}

dependencies {
    commonMainImplementation(projects.core.network)
    commonMainImplementation(projects.core.preferences)
}
