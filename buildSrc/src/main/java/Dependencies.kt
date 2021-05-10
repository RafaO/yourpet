object Versions {
    const val kotlin = "1.4.32"
    const val junit = "4.13"
    const val sqlDelight = "1.4.4"
    const val hilt = "2.35"
    const val ktor = "1.5.2"
    const val detekt = "1.17.0-RC2"
    const val mockk = "1.11.0"
}

object Ktor {
    const val clientCore = "io.ktor:ktor-client-core:${Versions.ktor}"
    const val clientJson = "io.ktor:ktor-client-json:${Versions.ktor}"
    const val clientLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
    const val clientSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"

    const val serialization = "io.ktor:ktor-serialization:${Versions.ktor}"
    const val serverCore = "io.ktor:ktor-server-core:${Versions.ktor}"
    const val serverNetty = "io.ktor:ktor-server-netty:${Versions.ktor}"

    const val clientAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"

    const val clientIos = "io.ktor:ktor-client-ios:${Versions.ktor}"
}

object SqlDelight {
    const val runtime = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
}

object Koin {
    const val core = "io.insert-koin:koin-core:3.0.1"
}

object Test {
    const val junit = "junit:junit:${Versions.junit}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val androidx = "androidx.arch.core:core-testing:2.1.0"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.0"
}
