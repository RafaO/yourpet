package com.keller.yourpet.shared.api

import com.keller.yourpet.shared.model.Pet
import com.keller.yourpet.shared.repository.IPetsSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PetsApiClient : IPetsSource, KoinComponent {
    private val client: HttpClient by inject()

    override suspend fun getPets() = withContext(Dispatchers.Main) {
        client.get<List<Pet>>("http://192.168.0.107:9090/pets")
    }

    override fun saveOverride(pets: List<Pet>) {
        throw NotImplementedError("api cannot save pets")
    }
}
