buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath("com.android.tools.build:gradle:7.1.0-alpha10")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}")

        classpath("com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}")
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt") version Versions.detekt
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    configurations {
        all {
            resolutionStrategy {
                force("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1-native-mt")
            }
        }
    }
}

tasks.register("detektAll") {
    dependsOn(":shared:detekt")
    dependsOn(":androidApp:detekt")
}
