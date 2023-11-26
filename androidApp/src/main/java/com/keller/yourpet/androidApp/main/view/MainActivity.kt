package com.keller.yourpet.androidApp.main.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.keller.yourpet.androidApp.NavigationConstants.Companion.ARG_PET
import com.keller.yourpet.androidApp.NavigationConstants.Companion.ROUTE_HOME
import com.keller.yourpet.androidApp.NavigationConstants.Companion.ROUTE_PET_DETAILS
import com.keller.yourpet.androidApp.home.view.HomeScreen
import com.keller.yourpet.androidApp.main.viewmodel.MainViewModel
import com.keller.yourpet.androidApp.main.viewmodel.isDark
import com.keller.yourpet.androidApp.petdetails.PetDetailsScreen
import com.keller.yourpet.androidApp.ui.YourPetUITheme
import com.keller.yourpet.shared.repository.PetsRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var petsRepository: PetsRepository

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
        val state by viewModel.uiState.collectAsStateWithLifecycle()
        val navController = rememberNavController()

        YourPetUITheme(darkTheme = state.themeColor.isDark(isSystemInDarkTheme())) {
            NavHost(navController = navController, startDestination = ROUTE_HOME) {
                composable(ROUTE_HOME) {
                    HomeScreen(navController, petsRepository)
                }
                composable(
                    ROUTE_PET_DETAILS,
                    enterTransition = {
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                            animationSpec = tween(700)
                        )
                    },
                ) {
                    it.arguments?.getString(ARG_PET)?.let { petId ->
                        PetDetailsScreen(petsRepository, petId)
                    }
                }
            }
        }
    }
}
