package com.keller.yourpet.shared.api

import com.keller.yourpet.shared.model.Pet
import com.keller.yourpet.shared.repository.IPetsSource
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

// TODO inject dependencies
class PetsApiClient : IPetsSource {

    private fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }

    private fun createHttpClient(json: Json, enableNetworkLogs: Boolean) = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }
        if (enableNetworkLogs) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.INFO
            }
        }
    }

    private val client = createHttpClient(createJson(), true)

    override suspend fun getPets() = withContext(Dispatchers.Main) {
        client.get<List<Pet>>("http://192.168.0.107:9090/pets")
    }

    override fun saveOverride(pets: List<Pet>) {
        throw NotImplementedError("api cannot save pets")
    }
}
