package com.keller.yourpet.shared.usecase

import com.keller.yourpet.shared.model.Pet

class GetPetsUseCase {
    fun execute() = listOf(Pet("charlie"), Pet("Willy"))
}
