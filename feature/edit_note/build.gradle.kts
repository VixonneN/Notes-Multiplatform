plugins {
    alias(libs.plugins.convention.feature)
    alias(libs.plugins.convention.compose)
}

dependencies {
    commonMainImplementation(projects.core.databaseRoom)
}
