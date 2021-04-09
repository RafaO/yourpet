package com.keller.yourpet.shared.api

import com.keller.yourpet.shared.model.Pet
import com.keller.yourpet.shared.repository.IPetsSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class PetsApiClient : IPetsSource {
    override suspend fun getPets() = withContext(Dispatchers.Main) {
        delay(2000)
        listOf(
            Pet("Charlie", "https://picsum.photos/id/237/200/150"),
            Pet("Willy Fog", "https://picsum.photos/id/237/200/150")
        )
    }

    override fun saveOverride(pets: List<Pet>) {
        throw NotImplementedError("api cannot save pets")
    }
}
