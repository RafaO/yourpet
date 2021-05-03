package com.keller.yourpet.androidApp.di

import android.content.Context
import com.keller.yourpet.shared.api.PetsApiClient
import com.keller.yourpet.shared.data.MyDatabase
import com.keller.yourpet.shared.database.DatabaseDriverFactory
import com.keller.yourpet.shared.database.DatabaseModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Module
@InstallIn(SingletonComponent::class)
object SingletonsModule : KoinComponent {
    private val api: PetsApiClient by inject()

    @Provides
    fun provideApiClient() = api

    @Provides
    fun provideDataBase(@ApplicationContext context: Context): MyDatabase {
        val databaseDriverFactory = DatabaseDriverFactory(context)
        return DatabaseModule().createDataBase(databaseDriverFactory.createDriver())
    }
}
