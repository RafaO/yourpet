import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
apply(from = "../tools/detekt.gradle")

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
    id("com.squareup.sqldelight")
    id("com.apollographql.apollo").version("2.5.9")
}

android {
    compileSdkVersion(29)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(29)
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
    android()
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

    val coroutinesVersion = "1.4.3-native-mt"
    val serializationVersion = "1.0.0-RC"
    val sqlVersion = "1.4.4"

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.apollographql.apollo:apollo-runtime-kotlin:2.5.9")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"){
                    version {
                        strictly(coroutinesVersion)
                    }
                }
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")

                api(Koin.core)

                implementation(Ktor.clientCore)
                implementation(Ktor.clientJson)
                implementation(Ktor.clientLogging)
                implementation(Ktor.clientSerialization)

                implementation(SqlDelight.runtime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Ktor.clientAndroid)
                implementation("com.google.android.material:material:1.2.1")
                implementation("com.squareup.sqldelight:android-driver:$sqlVersion")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("com.squareup.sqldelight:native-driver:$sqlVersion")
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
