import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    application
    id("au.com.dius.pact") version "4.3.10"
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}
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

                implementation(Ktor.serverContentNegotiation)
                implementation(Ktor.serializationGson)
                implementation(Ktor.serverCore)
                implementation(Ktor.serverNetty)
                implementation(Ktor.kGraphQLKtor)
                implementation(MongoDB.kmongo)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(Test.pact)
                implementation(Test.junit)
                implementation(Test.mockk)
                implementation(Test.coroutines)
            }
        }
    }
}

tasks {
    run.configure {
        args = (project.findProperty("testing") as? String)
            ?.let { listOf("-P:testing=${it}") }
            ?: emptyList()
    }
}

application {
    mainClass.set("ServerKt")
}

tasks.named("jvmJar", Jar::class) {
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
    from({
        configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
    })
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
