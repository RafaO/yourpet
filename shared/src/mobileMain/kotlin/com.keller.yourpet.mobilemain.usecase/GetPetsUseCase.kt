package com.keller.yourpet.mobilemain.usecase

import com.keller.yourpet.shared.model.Filter
import com.keller.yourpet.shared.model.Pet
import com.keller.yourpet.shared.repository.IPetsRepository

interface GetPetsUseCaseInterface : FlowableUseCase<Filter, List<Pet>>

class GetPetsUseCase(private val repository: IPetsRepository) :
    BaseFlowableUseCase<Filter, List<Pet>>(), GetPetsUseCaseInterface {
    override suspend fun performAction(param: Filter) = repository.getPets(param)
}
