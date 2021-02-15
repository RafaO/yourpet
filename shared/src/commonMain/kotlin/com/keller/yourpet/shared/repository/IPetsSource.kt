package com.keller.yourpet.shared.repository

import com.keller.yourpet.shared.model.Pet

interface IPetsSource {
    suspend fun getPets(): List<Pet>
}
