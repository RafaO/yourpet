package com.keller.yourpet.androidApp.main.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.keller.yourpet.androidApp.Navigation.Companion.ARG_PET
import com.keller.yourpet.androidApp.Navigation.Companion.ROUTE_HOME
import com.keller.yourpet.androidApp.Navigation.Companion.ROUTE_PET_DETAILS
import com.keller.yourpet.androidApp.home.view.HomeScreen
import com.keller.yourpet.androidApp.main.viewmodel.MainViewModel
import com.keller.yourpet.androidApp.petdetails.PetDetailsScreen
import com.keller.yourpet.androidApp.main.viewmodel.isDark
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
        val viewModel: MainViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
        val state by viewModel.uiState.collectAsState()
        val navController = rememberNavController()

        YourPetUITheme(darkTheme = state.themeColor.isDark(isSystemInDarkTheme())) {
            NavHost(navController = navController, startDestination = ROUTE_HOME) {
                composable(ROUTE_HOME) {
                    HomeScreen(navController)
                }
                composable(ROUTE_PET_DETAILS) {
                    navController.previousBackStackEntry?.arguments?.getString(ARG_PET)?.let {
                        PetDetailsScreen(pet = Json.decodeFromString(it))
                    } ?: run { Text("Something went wrong") }
                }
            }
        }
    }
}
