package com.keller.yourpet.androidApp.petslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.keller.yourpet.shared.model.Pet
import com.keller.yourpet.shared.usecase.GetPetsUseCase
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class PetsListViewModel @Inject constructor(getPetsUseCase: GetPetsUseCase) : ViewModel() {

    // Observables

    val pets: LiveData<List<Pet>> = liveData {
        getPetsUseCase.execute().collect { emit(it) }
    }
}