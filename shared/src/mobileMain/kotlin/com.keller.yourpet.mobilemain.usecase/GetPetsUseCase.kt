package com.keller.yourpet.mobilemain.usecase

import com.keller.yourpet.shared.model.Filter
import com.keller.yourpet.shared.model.Pet
import com.keller.yourpet.shared.repository.PetsRepository

class GetPetsUseCase(private val repository: PetsRepository) :
    FlowableUseCase<Filter, List<Pet>>() {
    override suspend fun performAction(param: Filter) = repository.getPets()
}
