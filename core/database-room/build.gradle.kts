import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
//            implementation(libs.sqlDelight.runtime)
//            implementation(libs.sqlDelight.coroutine)
//            implementation(libs.sqlDelight.primitive.adapters)
            implementation(libs.androidx.room.runtime)


            implementation(libs.kotlinx.datetime)

            implementation(libs.koin.core)
        }
//        commonTest.dependencies {
//            implementation(libs.kotlin.test)
//        }
        androidMain.dependencies {
            //database
//            implementation(libs.sqlDelight.driver.android)

            //di
            implementation(libs.koin.android)

        }
        iosMain.dependencies {
//            implementation(libs.sqlDelight.driver.native)
        }
    }
}

android {
    namespace = "com.khomichenko.database.room"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
    add("kspIosX64", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
}

//room {
//    schemaDirectory("$projectDir/schemas")
//}
