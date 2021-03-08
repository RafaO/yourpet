package com.keller.yourpet.shared.database

import com.keller.yourpet.shared.model.Pet
import com.keller.yourpet.shared.repository.IPetsSource

class PetsDataBase : IPetsSource {
    override suspend fun getPets() = listOf(Pet("Suso"))
}
