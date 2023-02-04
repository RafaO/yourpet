package com.keller.yourpet.shared.repository

import com.keller.yourpet.shared.model.Filter
import com.keller.yourpet.shared.model.Pet
import kotlinx.coroutines.flow.Flow

interface IPetsRepository {
    fun getPetInMemory(petId: Long): Pet?
    fun setPetInMemory(pet: Pet)
    suspend fun getPets(filter: Filter): Flow<List<Pet>>
}
