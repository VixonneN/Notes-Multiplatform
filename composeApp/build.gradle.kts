plugins {
    alias(libs.plugins.convention.application)
    alias(libs.plugins.buildConfig)
    alias(libs.plugins.convention.detekt)
}

dependencies {
    commonMainImplementation(libs.mvikotlin.core)
    commonMainImplementation(libs.mvikotlin.coroutines)
    commonMainImplementation(libs.mvikotlin.main)

    //core implementation
    commonMainImplementation(projects.core.preferences)
    commonMainImplementation(projects.core.network)
    commonMainImplementation(projects.core.ui)
    commonMainImplementation(projects.core.databaseRoom)

    //feature implementation
    commonMainImplementation(projects.feature.root)
    commonMainImplementation(projects.feature.auth)
    commonMainImplementation(projects.feature.registration)
    commonMainImplementation(projects.feature.main)
    commonMainImplementation(projects.feature.onboarding)
    commonMainImplementation(projects.feature.notes)
    commonMainImplementation(projects.feature.addNote)
    commonMainImplementation(projects.feature.editNote)
    commonMainImplementation(projects.feature.settings)
    commonMainImplementation(projects.feature.favorites)
    commonMainImplementation(projects.feature.profile)
}

buildConfig {
    // BuildConfig configuration here.
    // https://github.com/gmazzo/gradle-buildconfig-plugin#usage-in-kts
}
