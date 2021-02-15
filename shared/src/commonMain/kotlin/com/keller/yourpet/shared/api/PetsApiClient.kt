package com.keller.yourpet.shared.api

import com.keller.yourpet.shared.model.Pet
import com.keller.yourpet.shared.repository.IPetsSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class PetsApiClient : IPetsSource {
    override suspend fun getPets() = withContext(Dispatchers.Main) {
        delay(2000)
        listOf(Pet("Charlie"), Pet("Willy Fog"))
    }
}
