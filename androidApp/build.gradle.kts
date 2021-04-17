plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.2")

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")


    // compose
    val composeVersion = "1.0.0-beta02"
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.activity:activity-compose:1.3.0-alpha04")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha03")
    implementation("androidx.compose.runtime:runtime:$composeVersion")
    implementation("androidx.compose.runtime:runtime-livedata:$composeVersion")

    // navigation
    val navVersion = "2.3.3"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
    implementation("androidx.navigation:navigation-compose:1.0.0-alpha10")

    implementation("dev.chrisbanes.accompanist:accompanist-glide:0.6.1")

    val hiltVersion = "2.33-beta"
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.keller.yourpet.androidApp"
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        // Enables Jetpack Compose for this module
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-beta01"
    }
}
