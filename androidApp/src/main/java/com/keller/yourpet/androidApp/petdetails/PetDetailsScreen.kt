package com.keller.yourpet.androidApp.petdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.keller.yourpet.androidApp.R
import com.keller.yourpet.androidApp.ui.UIGroups
import com.keller.yourpet.androidApp.ui.YourPetUITheme
import com.keller.yourpet.shared.mock.mockPet
import com.keller.yourpet.shared.model.Pet
import com.keller.yourpet.shared.repository.PetsRepository
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
fun PetDetailsScreen(petsRepository: PetsRepository, petId: String) {
    petsRepository.getPetInMemory(petId)?.let {
        PetDetailsContent(it)
    } ?: run {
        Text("Pet not found")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetDetailsContent(pet: Pet) {
    val state = rememberCollapsingToolbarScaffoldState()

    CollapsingToolbarScaffold(
        toolbar = {
            Image(
                painter = rememberImagePainter(data = pet.imageUrl, builder = {
                    placeholder(R.drawable.filter_list_white_24dp) // TODO
                    size(OriginalSize)
                }),
                contentDescription = "image for $pet.name",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .parallax(ratio = 0.2f),
                contentScale = ContentScale.FillWidth,
            )
            Text(
                text = pet.name, modifier = Modifier.road(
                    whenCollapsed = Alignment.CenterStart,
                    whenExpanded = Alignment.BottomEnd
                )
            )
        },
        state = state,
        modifier = Modifier.fillMaxSize(),
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Text(pet.name, style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Preview(showBackground = true, name = "Pet details", group = UIGroups.Screens)
@Composable
fun PetDetailsPreview() {
    YourPetUITheme {
        PetDetailsContent(mockPet())
    }
}
