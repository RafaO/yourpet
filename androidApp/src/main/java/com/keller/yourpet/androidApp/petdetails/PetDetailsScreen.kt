package com.keller.yourpet.androidApp.petdetails

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.keller.yourpet.shared.repository.PetsRepository

@Composable
fun PetDetailsScreen(petsRepository: PetsRepository, petId: Long?) {
    petId?.let {
        petsRepository.getPetInMemory(petId)?.let {
            Text(it.name, style = MaterialTheme.typography.titleMedium)
        }
    } ?: run {
        Text("Pet not found")
    }
}
