package com.keller.yourpet.androidApp.petslist.di

import com.keller.yourpet.shared.api.PetsApiClient
import com.keller.yourpet.shared.data.MyDatabase
import com.keller.yourpet.shared.database.PetsDataBaseHelper
import com.keller.yourpet.shared.repository.PetsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {
    @Provides
    fun providePetsRepository(database: MyDatabase, api: PetsApiClient) =
        PetsRepository(PetsDataBaseHelper(database), api)
}
