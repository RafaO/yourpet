package com.keller.yourpet.shared.di

import com.apollographql.apollo3.ApolloClient
import com.keller.yourpet.shared.api.PetsApiClient
import com.keller.yourpet.shared.localhost
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(commonModule(true))
}

// called by iOS etc
fun initKoin() = initKoin {}

fun commonModule(enableNetworkLogs: Boolean) = module {
    single { PetsApiClient() }
    single { ApolloClient(serverUrl = "$localhost:8080/graphql") }
}
