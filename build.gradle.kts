buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath("com.android.tools.build:gradle:7.2.1")
        classpath("com.apollographql.apollo3:apollo-gradle-plugin:${Versions.apollo}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}")

        classpath("com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}")

        classpath("com.karumi:shot:5.13.0")
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
                force("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}")
            }
        }
    }
}

tasks.register("detektAll") {
    dependsOn(":shared:detekt")
    dependsOn(":androidApp:detekt")
}
