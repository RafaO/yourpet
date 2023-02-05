package com.keller.yourpet.androidApp.home.view.menu

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.keller.yourpet.androidApp.petslist.view.PetsListScreen
import com.keller.yourpet.androidApp.settings.view.SettingsScreen
import com.keller.yourpet.shared.repository.IPetsRepository

sealed class MenuOption(val text: String, val screen: @Composable () -> Unit) {
    class Pets(
        petsRepository: IPetsRepository,
        navController: NavController,
        update: Boolean,
        updated: () -> Unit
    ) :
        MenuOption("Pets", screen = {
            PetsListScreen(update, updated) {
                petsRepository.setPetInMemory(it)
                navController.navigate("detail/${it.id}")
            }
        })

    class Settings : MenuOption("Settings", screen = { SettingsScreen() })
}
