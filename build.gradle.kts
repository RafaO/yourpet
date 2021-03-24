buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    val kotlinVersion = "1.4.30"
    val sqlDelightVersion = "1.4.4"

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.android.tools.build:gradle:7.0.0-alpha10")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")

        classpath("com.squareup.sqldelight:gradle-plugin:$sqlDelightVersion")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}
