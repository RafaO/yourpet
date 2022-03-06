package com.keller.yourpet.shared.repository

import com.keller.yourpet.shared.model.Filter
import com.keller.yourpet.shared.model.Pet

interface IPetsSource {
    suspend fun getPets(filter: Filter): List<Pet>
    fun saveOverride(pets: List<Pet>)
}
