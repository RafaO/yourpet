package com.keller.yourpet.androidApp.petslist.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.keller.yourpet.androidApp.petslist.viewmodel.PetsListViewModel
import com.keller.yourpet.androidApp.petslist.viewmodel.PetsListViewState
import com.keller.yourpet.shared.model.Pet

@Composable
fun PetsHome(viewModel: PetsListViewModel, onPetClicked: (Pet) -> Unit) {
    val state by viewModel.state.observeAsState(PetsListViewState.Content(emptyList()))

    Content(state, onPetClicked)
}

@Composable
fun Content(state: PetsListViewState, onPetClicked: (Pet) -> Unit) {
    when (state) {
        is PetsListViewState.Content -> PetsListScreen(pets = state.pets, onPetClicked)
        is PetsListViewState.Loading -> Text("Loading")
        is PetsListViewState.Error -> Text(state.message)
    }
}
