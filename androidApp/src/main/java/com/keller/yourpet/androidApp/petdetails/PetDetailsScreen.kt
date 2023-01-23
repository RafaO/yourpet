package com.keller.yourpet.androidApp.petdetails

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.keller.yourpet.shared.model.Pet

@Composable
fun PetDetailsScreen(pet: Pet) {
    Text(pet.name, style = MaterialTheme.typography.titleMedium)
}
