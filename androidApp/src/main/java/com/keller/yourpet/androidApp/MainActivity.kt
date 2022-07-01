package com.keller.yourpet.androidApp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.keller.yourpet.androidApp.Navigation.Companion.ARG_PET
import com.keller.yourpet.androidApp.Navigation.Companion.ROUTE_HOME
import com.keller.yourpet.androidApp.Navigation.Companion.ROUTE_PET_DETAILS
import com.keller.yourpet.androidApp.home.view.HomeScreen
import com.keller.yourpet.androidApp.petdetails.PetDetailsScreen
import com.keller.yourpet.androidApp.ui.YourPetUITheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

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
        NavHost(navController = navController, startDestination = ROUTE_HOME) {
            composable(ROUTE_HOME) {
                HomeScreen(navController) // TODO replace with home vm
            }
            composable(ROUTE_PET_DETAILS) {
                navController.previousBackStackEntry?.arguments?.getString(ARG_PET)?.let {
                    PetDetailsScreen(pet = Json.decodeFromString(it))
                } ?: run { Text("Something went wrong") }
            }
        }
    }
}
