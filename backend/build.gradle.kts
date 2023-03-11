import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    application
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

kotlin {
    jvm {
        withJava()
    }

    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(":shared"))

                implementation(GraphQL.kGraphQL)

                implementation(Ktor.serverCore)
                implementation(Ktor.serverNetty)
                implementation(Ktor.kGraphQLKtor)
                implementation(MongoDB.kmongo)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(Test.junit)
                implementation(Test.mockk)
                implementation(Test.coroutines)
            }
        }
    }
}

application {
    mainClass.set("ServerKt")
}
