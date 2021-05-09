package com.keller.yourpet.shared.usecase

import com.keller.yourpet.shared.common.usecase.FlowableUseCase
import com.keller.yourpet.shared.model.Pet
import com.keller.yourpet.shared.repository.PetsRepository

class GetPetsUseCase(private val repository: PetsRepository) : FlowableUseCase<Unit, List<Pet>>() {
    override suspend fun performAction(param: Unit) = repository.getPets()
}
