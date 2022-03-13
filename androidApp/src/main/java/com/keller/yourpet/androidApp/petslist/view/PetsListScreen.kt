package com.keller.yourpet.androidApp.petslist.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.keller.yourpet.shared.model.Pet

@Composable
fun PetsListScreen(pets: List<Pet>, onPetClicked: (Pet) -> Unit) = PetsList(pets, onPetClicked)

@Composable
private fun PetsList(pets: List<Pet>, onPetClicked: (Pet) -> Unit) = LazyColumn {
    items(
        count = pets.size,
        itemContent = { PetCard(pets[it], onPetClicked) },
    )
}
