package com.keller.yourpet.androidApp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.keller.yourpet.shared.api.PetsApiClient
import com.keller.yourpet.shared.data.MyDatabase
import com.keller.yourpet.shared.database.PetsDataBaseHelper
import com.keller.yourpet.shared.repository.PetsRepository
import com.keller.yourpet.shared.usecase.GetPetsUseCase

class ViewModelFactory(private val database: MyDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PetsListViewModel::class.java)) {
            return PetsListViewModel(
                GetPetsUseCase(PetsRepository(PetsDataBaseHelper(database), PetsApiClient()))
            ) as T
        }
        throw IllegalArgumentException("Unknown viewmodel")
    }
}
