package com.keller.yourpet.shared.usecase

import com.keller.yourpet.shared.model.Pet
import kotlinx.coroutines.*

class GetPetsUseCase {
    suspend fun execute(): List<Pet> = withContext(Dispatchers.Main) {
        delay(2000)
        return@withContext listOf(Pet("Charlie"), Pet("Willy"))
    }
}
