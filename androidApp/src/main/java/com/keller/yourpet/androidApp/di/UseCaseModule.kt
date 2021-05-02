package com.keller.yourpet.androidApp.di

import android.content.Context
import com.keller.yourpet.shared.api.PetsApiClient
import com.keller.yourpet.shared.database.DatabaseDriverFactory
import com.keller.yourpet.shared.database.DatabaseModule
import com.keller.yourpet.shared.database.PetsDataBaseHelper
import com.keller.yourpet.shared.repository.PetsRepository
import com.keller.yourpet.shared.usecase.GetPetsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Module
@InstallIn(ActivityComponent::class)
object UseCaseModule: KoinComponent {
    private val api: PetsApiClient by inject()

    @Provides
    fun provideGetPetsUseCase(@ActivityContext context: Context): GetPetsUseCase {
        val databaseDriverFactory = DatabaseDriverFactory(context)
        val database = DatabaseModule().createDataBase(databaseDriverFactory.createDriver())

        return GetPetsUseCase(PetsRepository(PetsDataBaseHelper(database), api))
    }
}
