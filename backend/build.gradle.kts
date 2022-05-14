import org.jetbrains.kotlin.gradle.dsl.KotlinCommonOptions
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilationToRunnableFiles
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    application
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

kotlin {
    jvm()

    sourceSets {
        val jvmMain by getting {
            kotlin.srcDir("src")
            resources.srcDir("resources")

            dependencies {
                implementation(project(":shared"))

                implementation(GraphQL.kGraphQL)

                implementation(Ktor.serverCore)
                implementation(Ktor.serverNetty)
                implementation(Ktor.kGraphQLKtor)
                implementation(MongoDB.kmongo)
            }
        }
        val jvmTest by getting { }
    }
}

application {
    mainClass.set("ServerKt")
}

task<JavaExec>("runServer") {
    main = "ServerKt"
    val jvm by kotlin.targets.getting
    val main: KotlinCompilation<KotlinCommonOptions> by jvm.compilations

    val runtimeDependencies =
        (main as KotlinCompilationToRunnableFiles<KotlinCommonOptions>).runtimeDependencyFiles
    classpath = files(main.output.allOutputs, runtimeDependencies)
}
