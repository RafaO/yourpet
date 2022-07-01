package com.keller.yourpet.androidApp.home.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.keller.yourpet.androidApp.R
import com.keller.yourpet.androidApp.home.view.menu.MenuOption
import com.keller.yourpet.androidApp.home.view.menu.SideMenu
import com.keller.yourpet.androidApp.home.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel: HomeViewModel = hiltViewModel()
    val drawerState = rememberDrawerState(DrawerValue.Closed, viewModel::onDrawerStateChanged)
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsState()
    val options = listOf(
        MenuOption.Pets(navController, uiState.filterUpdated, viewModel::petsListUpdated),
        MenuOption.Settings()
    )
    val innerNavController = rememberNavController()

    Column {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.title_pets_list)) },
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        viewModel.onDrawerIconClicked(drawerState)
                    }
                }) {
                    Icon(
                        ImageVector.vectorResource(id = R.drawable.filter_list_white_24dp),
                        "Filter",
                    )
                }
            },
        )

        ModalDrawer(
            drawerState = drawerState,
            drawerContent = {
                SideMenu(
                    viewModel.filter,
                    options,
                    uiState.optionSelected,
                    onGenderSelected = viewModel::onGenderSelected,
                    onOptionSelected = {
                        viewModel.onOptionSelected(it)
                        scope.launch {
                            drawerState.close()
                        }
                    }
                )
            }) {
            NavHost(
                navController = innerNavController,
                startDestination = options[0].text
            ) {
                options.map { option -> composable(option.text) { option.screen() } }
            }
        }

    }
}
