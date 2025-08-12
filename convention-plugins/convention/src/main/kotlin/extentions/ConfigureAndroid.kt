package extentions

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import helpers.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.get

internal fun Project.configureKotlinAndroidApplication(
    exception: ApplicationExtension
) = exception.apply {
    namespace = "com.khomichenko.composeApp"

    compileSdk = libs.versions.androidCompile.get().toInt()
    defaultConfig {
        minSdk = libs.versions.androidCompile.get().toInt()
        targetSdk = libs.versions.androidCompile.get().toInt()

        applicationId = "com.khomichenko.notes.androidApp"
        versionCode = 1
        versionName = "1.0.0"
    }
    sourceSets["main"].apply {
        manifest.srcFile("src/androidMain/AndroidManifest.xml")
        res.srcDirs("src/androidMain/resources")
        resources.srcDirs("src/commonMain/resources")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

internal fun Project.configureKotlinAndroidLibrary(
    extension: LibraryExtension
) = extension.apply {

    //get module name from module path
    val moduleName = path.split(":").drop(2).joinToString(".")
    namespace = if(moduleName.isNotEmpty()) "com.khomichenko.$moduleName" else "com.khomichenko.composeApp"

    compileSdk = libs.versions.androidCompile.get().toInt()
    defaultConfig {
        minSdk = libs.versions.androidMin.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
