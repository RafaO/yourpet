package com.keller.yourpet.shared.database

import com.keller.yourpet.shared.data.MyDatabase
import com.keller.yourpet.shared.model.Filter
import com.keller.yourpet.shared.model.Pet
import com.keller.yourpet.shared.repository.IPetsSource
import kotlin.random.Random

class PetsDataBaseHelper(private val database: MyDatabase) : IPetsSource {
    override suspend fun getPets(filter: Filter) =
        database.petBDQueries.selectFilteredPets(
            filter.genders.map { it.toString() },
            PetMapper::from
        ).executeAsList()

    override fun saveOverride(pets: List<Pet>) {
        database.petBDQueries.deletePets()
        pets.forEach {
            database.petBDQueries.insertPet(
                Random.nextLong(),
                it.name,
                it.imageUrl,
                it.gender.toString(),
            )
        }
    }
}
