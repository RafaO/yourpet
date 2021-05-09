package com.keller.yourpet.shared.di

import com.keller.yourpet.shared.api.PetsApiClient
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
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
