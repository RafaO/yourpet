pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    
}
rootProject.name = "YourPet"


include(":androidApp")
include(":shared")
include(":backend")
