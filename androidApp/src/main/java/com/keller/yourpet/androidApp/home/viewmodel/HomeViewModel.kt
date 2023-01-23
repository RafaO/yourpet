package com.keller.yourpet.androidApp.home.viewmodel

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keller.yourpet.androidApp.home.view.menu.MenuOption
import com.keller.yourpet.shared.model.Filter
import com.keller.yourpet.shared.model.Gender
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
@HiltViewModel
class HomeViewModel @Inject constructor(val filter: Filter) : ViewModel() {

    companion object {
        private val initialValue = HomeUiState(
            MenuOption.Settings(),
            filterUpdated = false,
            shouldNavigate = false
        )
    }

    private val _uiState = MutableStateFlow(initialValue)
    val uiState = _uiState.stateIn(
        viewModelScope,
        WhileSubscribed(5000),
        initialValue
    )

    fun navigated() {
        _uiState.update { it.copy(shouldNavigate = false) }
    }

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
        _uiState.update { it.copy(optionSelected = option, shouldNavigate = true) }
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
