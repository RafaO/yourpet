package com.keller.yourpet.shared.repository

import com.keller.yourpet.shared.model.Filter
import com.keller.yourpet.shared.model.Pet
import kotlinx.coroutines.flow.flow

class PetsRepository(private val cacheSource: IPetsSource, private val networkSource: IPetsSource) :
    IPetsRepository {
    private var memory: Pet? = null

    override fun getPetInMemory(petId: String): Pet? {
        memory?.id.let {
            if (it == petId)
                return memory
        }
        return null
    }

    override fun setPetInMemory(pet: Pet) {
        memory = pet
    }

    override suspend fun getPets(filter: Filter) = flow {
        emit(cacheSource.getPets(filter))
        networkSource.getPets(filter).let {
            emit(it)
            cacheSource.saveOverride(it)
        }
    }
}
