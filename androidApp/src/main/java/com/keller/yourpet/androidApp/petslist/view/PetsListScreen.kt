package com.keller.yourpet.androidApp.petslist.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.keller.yourpet.androidApp.R
import com.keller.yourpet.shared.model.Pet

@Composable
fun PetsListScreen(pets: List<Pet>, onPetClicked: (Pet) -> Unit) = Column {
    Text(
        stringResource(id = R.string.title_pets_list),
        modifier = Modifier.padding(16.dp),
        style = MaterialTheme.typography.h4
    )
    PetsList(pets, onPetClicked)
}

@Composable
private fun PetsList(pets: List<Pet>, onPetClicked: (Pet) -> Unit) = LazyColumn {
    items(
        count = pets.size,
        itemContent = { PetCard(pets[it], onPetClicked) },
    )
}
