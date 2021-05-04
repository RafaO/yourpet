package com.keller.yourpet.shared.repository

import kotlinx.coroutines.flow.flow

class PetsRepository(private val cacheSource: IPetsSource, private val networkSource: IPetsSource) {
    suspend fun getPets() = flow {
        emit(cacheSource.getPets())
        networkSource.getPets().let {
            emit(it)
            cacheSource.saveOverride(it)
        }
    }
}
