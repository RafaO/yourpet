package com.keller.yourpet.androidApp.petslist.viewmodel

import com.keller.yourpet.shared.model.Pet

sealed class PetsListViewState {
    class Content(val pets: List<Pet>) : PetsListViewState()
    object Loading : PetsListViewState()
    class Error(val message: String) : PetsListViewState()
}
