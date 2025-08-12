plugins {
    alias(libs.plugins.convention.core)
    alias(libs.plugins.convention.compose)
}

dependencies {
    commonMainImplementation(libs.decompose)

    androidMainImplementation(libs.androidx.core.ktx)
    androidMainImplementation(libs.kotlinx.coroutines.android)
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.khomichenko.ui.resources"
    generateResClass = always
}
