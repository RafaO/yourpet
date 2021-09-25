package com.keller.yourpet.androidApp.petslist.view

import PetsListError
import androidx.compose.foundation.layout.Column
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.keller.yourpet.androidApp.R
import com.keller.yourpet.androidApp.petslist.view.menu.SideMenu
import com.keller.yourpet.androidApp.petslist.viewmodel.PetsListViewModel
import com.keller.yourpet.androidApp.petslist.viewmodel.PetsListViewState
import com.keller.yourpet.shared.model.Pet
import kotlinx.coroutines.launch

@Composable
fun PetsHome(viewModel: PetsListViewModel, onPetClicked: (Pet) -> Unit) {
    val state by viewModel.state.observeAsState(PetsListViewState.Content(emptyList()))
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = null) { viewModel.onViewRefreshed() }

    Column {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.title_pets_list)) },
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        viewModel.onFiltersClicked(drawerState)
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
            drawerContent = { SideMenu() }) {
            Content(state, onPetClicked) { viewModel.onViewRefreshed() }
        }
    }
}

@Composable
fun Content(state: PetsListViewState, onPetClicked: (Pet) -> Unit, onRetry: () -> Unit) {
    when (state) {
        is PetsListViewState.Content -> PetsListScreen(pets = state.pets, onPetClicked)
        is PetsListViewState.Loading -> Text("Loading")
        is PetsListViewState.Error -> PetsListError(message = state.message, onRetry)
    }
}
