package com.keller.yourpet.shared.api

import com.keller.yourpet.GetAllPetsQuery
import com.apollographql.apollo3.ApolloClient
import com.keller.yourpet.shared.model.Pet
import com.keller.yourpet.shared.repository.IPetsSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PetsApiClient : IPetsSource, KoinComponent {

    private val apolloClient: ApolloClient by inject()

    override suspend fun getPets() = withContext(Dispatchers.Main) {
        apolloClient.query(GetAllPetsQuery()).dataOrThrow.pets.map { Pet(it.name, it.imageUrl) }
    }

    override fun saveOverride(pets: List<Pet>) {
        throw NotImplementedError("api cannot save pets")
    }
}
