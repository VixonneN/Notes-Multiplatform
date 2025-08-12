plugins {
    alias(libs.plugins.convention.feature)
    alias(libs.plugins.convention.compose)
}

dependencies {
    commonMainImplementation(projects.core.preferences)
    commonMainImplementation(projects.core.databaseRoom)

    commonMainImplementation(projects.feature.auth)
    commonMainImplementation(projects.feature.registration)
}
