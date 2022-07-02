package com.keller.yourpet.androidApp.petslist.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.keller.yourpet.androidApp.R
import com.keller.yourpet.androidApp.ui.UIGroups
import com.keller.yourpet.androidApp.ui.YourPetUITheme
import com.keller.yourpet.shared.model.Gender
import com.keller.yourpet.shared.model.Pet

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PetCard(pet: Pet, onPetClicked: (Pet) -> Unit) = Column(
    modifier = Modifier
        .padding(16.dp)
        .clickable { onPetClicked(pet) }) {
    Image(
        painter = rememberImagePainter(
            data = pet.imageUrl,
            builder = {
                placeholder(R.drawable.filter_list_white_24dp) // TODO
                size(OriginalSize)
            }
        ),
        contentDescription = "image for $pet.name",
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(12.dp)),
        contentScale = ContentScale.FillWidth,
    )
    Text(pet.name, style = MaterialTheme.typography.h6)
}

@Preview(showBackground = true, name = "Pet card", group = UIGroups.PetsElements)
@Composable
fun PetCardPreview() {
    YourPetUITheme {
        PetCard(
            pet = Pet("Charlie", "https://picsum.photos/id/237/200/150", Gender.Female),
            onPetClicked = {})
    }
}
