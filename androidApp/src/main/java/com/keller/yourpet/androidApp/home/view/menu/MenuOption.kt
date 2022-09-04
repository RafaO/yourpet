package com.keller.yourpet.androidApp.home.view.menu

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.keller.yourpet.androidApp.Navigation.Companion.ARG_PET
import com.keller.yourpet.androidApp.Navigation.Companion.ROUTE_PET_DETAILS
import com.keller.yourpet.androidApp.petslist.view.PetsListScreen
import com.keller.yourpet.androidApp.settings.view.SettingsScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

sealed class MenuOption(val text: String, val screen: @Composable () -> Unit) {
    class Pets(navController: NavController, update: Boolean, updated: () -> Unit) :
        MenuOption("Pets", screen = {
            PetsListScreen(update, updated) {
                navController.currentBackStackEntry?.arguments?.putString(
                    ARG_PET,
                    Json.encodeToString(it)
                )
                navController.navigate(ROUTE_PET_DETAILS)
            }
        })

    class Settings : MenuOption("Settings", screen = { SettingsScreen() })
}
