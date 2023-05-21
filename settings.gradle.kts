pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    plugins {
        id("multiplatform") version "1.8.21"
    }
    
}
rootProject.name = "YourPet"


include(":androidApp")
include(":shared")
include(":backend")
