import Versions.coroutinesVersion
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

apply(from = "../tools/detekt.gradle")

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
    id("com.squareup.sqldelight")
    id("com.apollographql.apollo3")
}

apollo {
    packageName.set("com.keller.yourpet")
}

android {
    compileSdk = AndroidSdk.compile
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target
    }

    // workaround needed: https://youtrack.jetbrains.com/issue/KT-43944
    configurations {
        create("androidTestApi")
        create("androidTestDebugApi")
        create("androidTestReleaseApi")
        create("testApi")
        create("testDebugApi")
        create("testReleaseApi")
    }
}

kotlin {
    jvm()
    android {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    ios {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }

    val sdkName = System.getenv("SDK_NAME")
    if (sdkName != null && sdkName.startsWith("iphoneos")) {
        iosArm64("ios")
    } else {
        iosX64("ios")
    }

    val serializationVersion = "1.0.0-RC"

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")

                api(Koin.core)

                implementation(Ktor.clientCore)
                implementation(Ktor.clientJson)
                implementation(Ktor.clientLogging)
                implementation(Ktor.clientSerialization)

                implementation(SqlDelight.runtime)

                implementation(GraphQL.apollo)
            }
        }

        val mobileMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation(GraphQL.apollo)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependsOn(mobileMain)
            dependencies {
                implementation("com.squareup.sqldelight:android-driver:${Versions.sqlDelight}")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13")
            }
        }
        val iosMain by getting {
            dependsOn(mobileMain)
            dependencies {
                implementation("com.squareup.sqldelight:native-driver:${Versions.sqlDelight}")
                implementation(Ktor.clientIos)
            }
        }
        val iosTest by getting

        val jvmMain by getting
    }
}

sqldelight {
    database("MyDatabase") {
        packageName = "com.keller.yourpet.shared.data"
    }
}

val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
    val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
    val framework =
        kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}

tasks.getByName("build").dependsOn(packForXcode)
