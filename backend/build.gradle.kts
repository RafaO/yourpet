import org.jetbrains.kotlin.gradle.dsl.KotlinCommonOptions
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilationToRunnableFiles

plugins {
    kotlin("multiplatform")
    application
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
