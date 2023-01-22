package com.keller.yourpet.androidApp.home.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.keller.yourpet.androidApp.R
import com.keller.yourpet.androidApp.home.view.menu.MenuOption
import com.keller.yourpet.androidApp.home.view.menu.SideMenu
import com.keller.yourpet.androidApp.home.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel: HomeViewModel = hiltViewModel()
    val drawerState = rememberDrawerState(DrawerValue.Closed, viewModel::onDrawerStateChanged)
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val options = listOf(
        MenuOption.Pets(navController, uiState.filterUpdated, viewModel::petsListUpdated),
        MenuOption.Settings()
    )
    val innerNavController = rememberNavController()

    // vm will notify the side menu so filters are available
    LaunchedEffect(key1 = Unit) {
        viewModel.onOptionSelected(options[0])
    }

    if (uiState.shouldNavigate) {
        innerNavController.navigate(uiState.optionSelected.text)
        viewModel.navigated()
    }

    Column {
        TopAppBar(
            title = { Text(text = uiState.optionSelected.text) },
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

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(
                    content = {
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
