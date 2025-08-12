plugins {
    alias(libs.plugins.convention.core)
    alias(libs.plugins.ksp)
}

dependencies {
    commonMainImplementation(libs.androidx.room.runtime)
    commonMainImplementation(libs.kotlinx.datetime)
    commonMainImplementation(libs.androidx.sqlite.bundled)

    androidMainImplementation(libs.koin.android)

    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
    add("kspIosX64", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
}
