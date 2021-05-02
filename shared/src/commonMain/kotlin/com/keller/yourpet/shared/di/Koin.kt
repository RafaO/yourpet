package com.keller.yourpet.shared.di

import com.keller.yourpet.shared.api.PetsApiClient
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(commonModule(true))
}

// called by iOS etc
fun initKoin() = initKoin {}

fun commonModule(enableNetworkLogs: Boolean) = module {
    single { PetsApiClient() }
    single { createHttpClient(enableNetworkLogs) }
}

private fun createHttpClient(enableNetworkLogs: Boolean) = HttpClient {
    install(JsonFeature) {
        serializer = KotlinxSerializer(createJson())
    }
    if (enableNetworkLogs) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
    }
}

private fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }
