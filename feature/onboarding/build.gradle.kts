plugins {
    alias(libs.plugins.convention.feature)
    alias(libs.plugins.convention.compose)
}

dependencies {
    commonMainImplementation(compose.components.resources)

    commonMainImplementation(projects.core.preferences)
    commonMainApi(projects.core.ui)
}
