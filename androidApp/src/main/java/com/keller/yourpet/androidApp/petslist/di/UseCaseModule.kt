package com.keller.yourpet.androidApp.petslist.di

import com.keller.yourpet.shared.api.PetsApiClient
import com.keller.yourpet.shared.data.MyDatabase
import com.keller.yourpet.shared.database.PetsDataBaseHelper
import com.keller.yourpet.shared.repository.PetsRepository
import com.keller.yourpet.mobilemain.usecase.GetPetsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetPetsUseCase(database: MyDatabase, api: PetsApiClient) =
        GetPetsUseCase(PetsRepository(PetsDataBaseHelper(database), api))
}
