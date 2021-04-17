package com.keller.yourpet.androidApp.petdetails

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.keller.yourpet.shared.model.Pet

@Composable
fun PetDetailsScreen(pet: Pet) {
    Text(pet.name, style = MaterialTheme.typography.h1)
}
