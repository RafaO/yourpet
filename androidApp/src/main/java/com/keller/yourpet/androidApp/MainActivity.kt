package com.keller.yourpet.androidApp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.keller.yourpet.androidApp.petslist.view.PetsListScreen
import com.keller.yourpet.androidApp.ui.YourPetUITheme
import com.keller.yourpet.androidApp.petslist.viewmodel.PetsListViewModel
import com.keller.yourpet.shared.model.Pet
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: PetsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            YourPetUITheme {
                AppsHome(viewModel)
            }
        }
    }
}

@Composable
fun AppsHome(viewModel: PetsListViewModel) {
    val petsList by viewModel.pets.observeAsState(emptyList())
    Surface(color = MaterialTheme.colors.background) {
        PetsListScreen(pets = petsList) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    YourPetUITheme {
        PetsListScreen(listOf(Pet("charlie"))) {}
    }
}
