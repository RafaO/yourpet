package com.keller.yourpet.androidApp.petslist.view

import PetsListError
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.keller.yourpet.androidApp.petslist.viewmodel.PetsListViewModel
import com.keller.yourpet.androidApp.petslist.viewmodel.PetsListViewState
import com.keller.yourpet.androidApp.ui.UIGroups
import com.keller.yourpet.androidApp.ui.YourPetUITheme
import com.keller.yourpet.shared.mock.mockPetsList
import com.keller.yourpet.shared.model.Pet

@Composable
fun Content(state: PetsListViewState, onPetClicked: (Pet) -> Unit, onRetry: () -> Unit) {
    when (state) {
        is PetsListViewState.Content -> PetsList(pets = state.pets, onPetClicked)
        is PetsListViewState.Loading -> Text("Loading")
        is PetsListViewState.Error -> PetsListError(message = state.message, onRetry)
    }
}

@Composable
fun PetsListScreen(update: Boolean, updated: () -> Unit, onPetClicked: (Pet) -> Unit) {
    val viewModel: PetsListViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    if (update) {
        viewModel.onViewRefreshed()
        updated()
    }
    LaunchedEffect(key1 = null) { viewModel.onViewRefreshed() }
    Content(state, onPetClicked) { viewModel.onViewRefreshed() }
}

@Composable
private fun PetsList(pets: List<Pet>, onPetClicked: (Pet) -> Unit) =
    LazyColumn(modifier = Modifier.background(color = MaterialTheme.colorScheme.surface)) {
        items(
            count = pets.size,
            itemContent = { PetCard(pets[it], onPetClicked) },
        )
    }

@Preview(showBackground = true, name = "Pets list", group = UIGroups.Screens)
@Composable
fun PetsListPreview() {
    YourPetUITheme {
        PetsList(mockPetsList()) {}
    }
}
