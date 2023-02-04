package com.keller.yourpet.shared.repository

import com.keller.yourpet.shared.model.Filter
import com.keller.yourpet.shared.model.Pet
import kotlinx.coroutines.flow.Flow

class FakePetsRepository: IPetsRepository {
    override fun getPetInMemory(petId: Long): Pet? {
        TODO("Not yet implemented")
    }

    override fun setPetInMemory(pet: Pet) {
        TODO("Not yet implemented")
    }

    override suspend fun getPets(filter: Filter): Flow<List<Pet>> {
        TODO("Not yet implemented")
    }
}
