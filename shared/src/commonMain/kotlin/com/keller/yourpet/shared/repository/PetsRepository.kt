package com.keller.yourpet.shared.repository

class PetsRepository(private val networkSource: IPetsSource) {
    suspend fun getPets() = networkSource.getPets()
}
