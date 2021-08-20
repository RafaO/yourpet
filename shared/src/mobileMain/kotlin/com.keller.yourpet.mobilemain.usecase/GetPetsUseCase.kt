package com.keller.yourpet.mobilemain.usecase

import com.keller.yourpet.shared.model.Pet
import com.keller.yourpet.shared.repository.PetsRepository

class GetPetsUseCase(private val repository: PetsRepository) : FlowableUseCase<Unit, List<Pet>>() {
    override suspend fun performAction(param: Unit) = repository.getPets()
}