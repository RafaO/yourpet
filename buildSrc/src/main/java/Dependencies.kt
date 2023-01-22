object Versions {
    const val kotlin = "1.7.0"
    const val junit = "4.13"
    const val sqlDelight = "1.5.2"
    const val hilt = "2.38.1"
    const val ktor = "1.6.0"
    const val detekt = "1.17.0-RC2"
    const val mockk = "1.13.2"
    const val kGraphQL = "0.17.9"
    const val apollo = "3.0.0-alpha03"
    const val showkase = "1.0.0-beta17"
    const val compose = "1.2.0-rc03"
    const val androidTestJunit = "1.4.0"
    const val androidTestExtJunit = "1.1.3"
    const val coroutinesVersion = "1.6.1-native-mt"
    const val material = "1.0.1"
}

object Compose {
    const val material = "androidx.compose.material3:material3:${Versions.material}"
    const val materialWindowSizeClass =
        "androidx.compose.material3:material3-window-size-class:${Versions.material}"
}

object AndroidSdk {
    const val min = 24
    const val compile = 33
    const val target = compile
}

object MongoDB {
    const val kmongo = "org.litote.kmongo:kmongo-coroutine:4.5.0"
}

object Showkase {
    const val base = "com.airbnb.android:showkase:${Versions.showkase}"
    const val annotations = "com.airbnb.android:showkase-processor:${Versions.showkase}"
    const val screenshotTesting =
        "com.airbnb.android:showkase-screenshot-testing:${Versions.showkase}"
}

object GraphQL {
    const val kGraphQL = "com.apurebase:kgraphql:${Versions.kGraphQL}"
    const val apollo = "com.apollographql.apollo3:apollo-runtime:${Versions.apollo}"
}

object Ktor {
    const val clientCore = "io.ktor:ktor-client-core:${Versions.ktor}"
    const val clientJson = "io.ktor:ktor-client-json:${Versions.ktor}"
    const val clientLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
    const val clientSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"

    const val kGraphQLKtor = "com.apurebase:kgraphql-ktor:${Versions.kGraphQL}"

    const val serverCore = "io.ktor:ktor-server-core:${Versions.ktor}"
    const val serverNetty = "io.ktor:ktor-server-netty:${Versions.ktor}"

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

object AndroidTest {
    const val composeJunit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
    const val coreKtx = "androidx.test:core-ktx:${Versions.androidTestJunit}"
    const val core = "androidx.test:core:${Versions.androidTestJunit}"
    const val runner = "androidx.test:runner:${Versions.androidTestJunit}"
    const val extJunit = "androidx.test.ext:junit:${Versions.androidTestExtJunit}"
    const val extJunitKtx = "androidx.test.ext:junit-ktx:${Versions.androidTestExtJunit}"
}
