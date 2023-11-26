package com.keller.yourpet.androidApp.petdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
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

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PetDetailsContent(pet: Pet) {
    val state = rememberCollapsingToolbarScaffoldState()
    val barHeight = 200.dp

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
                    .height(barHeight)
                    .parallax(ratio = 0.2f),
                contentScale = ContentScale.FillWidth,
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(barHeight)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 1F)
                            ),
                            startY = barHeight.value * 0.8F,
                            endY = 500F
                        )
                    )
            )
            Text(
                text = pet.name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.text_padding))
                    .road(
                        whenCollapsed = Alignment.CenterStart,
                        whenExpanded = Alignment.BottomEnd
                    )
            )
        },
        state = state,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surfaceVariant),
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(dimensionResource(R.dimen.text_padding))
        ) {
            Text(pet.name, style = MaterialTheme.typography.titleMedium)
            Text(pet.description, style = MaterialTheme.typography.bodyLarge)
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
