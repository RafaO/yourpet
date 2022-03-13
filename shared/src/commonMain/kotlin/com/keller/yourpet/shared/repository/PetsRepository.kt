package com.keller.yourpet.shared.repository

import com.keller.yourpet.shared.model.Filter
import kotlinx.coroutines.flow.flow

class PetsRepository(private val cacheSource: IPetsSource, private val networkSource: IPetsSource) {
    suspend fun getPets(filter: Filter) = flow {
        emit(filter.applyTo(cacheSource.getPets(filter)))
        networkSource.getPets(filter).let {
            emit(it)
            cacheSource.saveOverride(it)
        }
    }
}
