package com.keller.yourpet.shared.database

import com.keller.yourpet.shared.data.MyDatabase
import com.keller.yourpet.shared.repository.IPetsSource

class PetsDataBaseHelper(private val database: MyDatabase) : IPetsSource {
    override suspend fun getPets() =
        database.petBDQueries.selectAllPets().executeAsList().map(PetMapper::from)
}
