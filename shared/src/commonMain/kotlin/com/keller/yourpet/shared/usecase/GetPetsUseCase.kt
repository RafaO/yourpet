package com.keller.yourpet.shared.usecase

import com.keller.yourpet.shared.repository.PetsRepository

class GetPetsUseCase(private val repository: PetsRepository) {
    suspend fun execute() = repository.getPets()
}
