plugins {
    alias(libs.plugins.convention.feature)
    alias(libs.plugins.convention.compose)
}

dependencies {
    // idk
    commonMainImplementation(libs.decompose.compose)

    commonMainImplementation(projects.core.ui)

    commonMainImplementation(projects.feature.notes)
    commonMainImplementation(projects.feature.addNote)
    commonMainImplementation(projects.feature.editNote)
    commonMainImplementation(projects.feature.settings)
    commonMainImplementation(projects.feature.favorites)
    commonMainImplementation(projects.feature.profile)
}
