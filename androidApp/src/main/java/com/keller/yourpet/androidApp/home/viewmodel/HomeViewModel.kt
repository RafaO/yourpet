package com.keller.yourpet.androidApp.home.viewmodel

import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.lifecycle.ViewModel
import com.keller.yourpet.androidApp.home.view.menu.MenuOption
import com.keller.yourpet.shared.model.Filter
import com.keller.yourpet.shared.model.Gender
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val filter: Filter) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(MenuOption.Settings(), false))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    suspend fun onDrawerIconClicked(drawerState: DrawerState) {
        if (drawerState.isClosed)
            drawerState.open()
        else {
            drawerState.close()
        }
        onDrawerStateChanged(drawerState.currentValue)
    }

    fun onDrawerStateChanged(newValue: DrawerValue): Boolean {
        if (newValue == DrawerValue.Closed) {
            _uiState.update { it.copy(filterUpdated = true) }
        }
        return true
    }

    fun onOptionSelected(option: MenuOption) {
        _uiState.update { it.copy(optionSelected = option) }
    }

    fun onGenderSelected(gender: Gender, selected: Boolean) {
        with(filter) {
            if (selected) {
                genders.add(gender)
            } else {
                genders.remove(gender)
            }
        }
    }

    fun petsListUpdated() {
        _uiState.update { it.copy(filterUpdated = false) }
    }
}
