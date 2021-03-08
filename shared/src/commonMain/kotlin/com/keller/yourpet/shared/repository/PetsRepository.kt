package com.keller.yourpet.shared.repository

import com.keller.yourpet.shared.wrap
import kotlinx.coroutines.flow.flow

class PetsRepository(private val cacheSource: IPetsSource, private val networkSource: IPetsSource) {
    suspend fun getPets() = flow {
        emit(cacheSource.getPets())
        emit(networkSource.getPets())
    }.wrap()
}
