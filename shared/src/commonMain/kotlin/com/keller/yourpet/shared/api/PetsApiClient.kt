package com.keller.yourpet.shared.api

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloNetworkException
import com.keller.yourpet.GetAllPetsQuery
import com.keller.yourpet.shared.model.Filter
import com.keller.yourpet.shared.model.Gender
import com.keller.yourpet.shared.model.Pet
import com.keller.yourpet.shared.repository.IPetsSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PetsApiClient : IPetsSource, KoinComponent {

    private val apolloClient: ApolloClient by inject()

    override suspend fun getPets(filter: Filter) = withContext(Dispatchers.Main) {
        apolloClient.query(GetAllPetsQuery(filter.toQueryParam())).dataOrThrow.pets.map {
            Pet(
                it.id,
                it.name,
                it.imageUrl,
                Gender.valueOf(it.gender.rawValue),
                "no description from api"
            )
        } ?: throw ApolloNetworkException("error retrieving pets from api")
    }

    override suspend fun getPet(petId: Long): Pet? {
        throw NotImplementedError("method not implemented in api")
    }

    override fun saveOverride(pets: List<Pet>) {
        throw NotImplementedError("api cannot save pets")
    }
}
