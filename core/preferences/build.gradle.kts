plugins {
    alias(libs.plugins.convention.core)
}

dependencies {
    commonMainImplementation(libs.multiplatform.settings.coroutines)
    commonMainImplementation(libs.multiplatform.settings)

    androidMainImplementation(libs.multiplatform.settings.datastore)
    androidMainImplementation(libs.androidx.datastore.core)
    androidMainImplementation(libs.androidx.datastore.preferences)
    androidMainImplementation(libs.koin.core)

    iosMainImplementation(libs.koin.core)
}
