package com.keller.yourpet.androidApp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.keller.yourpet.androidApp.Navigation.Companion.ARG_PET
import com.keller.yourpet.androidApp.Navigation.Companion.ROUTE_PETS_LIST
import com.keller.yourpet.androidApp.Navigation.Companion.ROUTE_PET_DETAILS
import com.keller.yourpet.androidApp.petdetails.PetDetailsScreen
import com.keller.yourpet.androidApp.petslist.view.PetsHome
import com.keller.yourpet.androidApp.petslist.view.PetsListScreen
import com.keller.yourpet.androidApp.petslist.viewmodel.PetsListViewModel
import com.keller.yourpet.androidApp.ui.UIGroups
import com.keller.yourpet.androidApp.ui.YourPetUITheme
import com.keller.yourpet.shared.model.Gender
import com.keller.yourpet.shared.model.Pet
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: PetsListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            YourPetUITheme {
                ComposeNavigation()
            }
        }
    }

    @Composable
    fun ComposeNavigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = ROUTE_PETS_LIST) {
            composable(ROUTE_PETS_LIST) {
                PetsHome(viewModel) {
                    navController.currentBackStackEntry?.arguments?.putString(
                        ARG_PET,
                        Json.encodeToString(it)
                    )
                    navController.navigate(ROUTE_PET_DETAILS)
                }
            }
            composable(ROUTE_PET_DETAILS) {
                navController.previousBackStackEntry?.arguments?.getString(ARG_PET)?.let {
                    PetDetailsScreen(pet = Json.decodeFromString(it))
                } ?: run { Text("Something went wrong") }
            }
        }
    }
}

@Preview(showBackground = true, name = "Pets list", group = UIGroups.Screens)
@Composable
fun DefaultPreview() {
    YourPetUITheme {
        PetsListScreen(
            listOf(
                Pet(
                    "charlie",
                    "https://picsum.photos/id/237/200/150",
                    Gender.Female
                )
            )
        ) {}
    }
}
