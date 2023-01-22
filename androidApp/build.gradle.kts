apply(from = "../tools/detekt.gradle")

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("shot")
}

shot {
    applicationId = "com.keller.yourpet.androidApp"
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.1")

    // compose
    implementation("androidx.compose.ui:ui:${Versions.compose}")
    implementation("androidx.compose.ui:ui-tooling:${Versions.compose}")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    implementation("androidx.compose.runtime:runtime:${Versions.compose}")
    implementation("androidx.compose.runtime:runtime-livedata:${Versions.compose}")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha04")
    implementation(Compose.material)
    implementation(Compose.materialWindowSizeClass)

    // navigation
    val navVersion = "2.3.3"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha08")

    implementation("io.coil-kt:coil-compose:1.3.2")

    // Hilt
    implementation("com.google.dagger:hilt-android:${Versions.hilt}")
    kapt("com.google.dagger:hilt-compiler:${Versions.hilt}")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Showkase
    implementation(Showkase.base)
    kapt(Showkase.annotations)

    testImplementation(Test.junit)
    testImplementation(Test.mockk)
    testImplementation(Test.androidx)
    testImplementation(Test.coroutines)

    androidTestImplementation(AndroidTest.composeJunit)
    androidTestImplementation(AndroidTest.espresso)
    androidTestImplementation(AndroidTest.coreKtx)
    androidTestImplementation(AndroidTest.core)
    androidTestImplementation(AndroidTest.runner)
    androidTestImplementation(AndroidTest.extJunit)
    androidTestImplementation(AndroidTest.extJunitKtx)
    kaptAndroidTest("com.airbnb.android:showkase-processor:1.0.0-beta12")
    androidTestImplementation("com.airbnb.android:showkase-screenshot-testing:1.0.0-beta08")
}

android {
    compileSdk = AndroidSdk.compile
    packagingOptions {
        resources.excludes.add("META-INF/*")
    }
    defaultConfig {
        applicationId = "com.keller.yourpet.androidApp"
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target
        versionCode = 1
        versionName = "1.0"
        signingConfig = signingConfigs.getByName("debug")
        testInstrumentationRunner = "com.karumi.shot.ShotTestRunner"
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
        jvmTarget = JavaVersion.VERSION_1_8.toString()

        freeCompilerArgs = listOf(
            "-P",
            "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=true"
        )
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }
}
